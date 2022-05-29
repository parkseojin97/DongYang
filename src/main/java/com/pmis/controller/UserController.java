package com.pmis.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.pmis.model.UserDTO;
import com.pmis.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	HttpSession session;

	// 회원 로그인
	@GetMapping("login")
	public String login(Model model) {
		return "login";
	}

	// 회원 로그인 전송
	@PostMapping("loginPOST")
	public void loginPOST(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		session = req.getSession();
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		UserDTO userInfo = userService.getUser(user);
		if (userInfo == null) {
			out.println("<script>");
			out.println("alert('없는 회원입니다. 회원가입 후 로그인 진행하시길 바랍니다.');");
			out.println("location.href='register';");
			out.println("</script>");
		} else {
			if (user.getUser_pw().equals(userInfo.getUser_pw())) {
				session.setAttribute("mem", userInfo);
				out.println("<script>");
				out.println("location.href='projects';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다. 다시 확인해주세요');");
				out.println("location.href='login';");
				out.println("</script>");
			}
		}
	}

	// 회원가입
	@GetMapping("register")
	public String register(Model model, HttpServletRequest req, HttpServletResponse res) {
		// 약관 필요시 파일 불러와서 사용
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream("static/agreement.txt");

		InputStreamReader inputStreamReader = new InputStreamReader(in);
		Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
		String termsOfAgreement = streamOfString.collect(Collectors.joining());
		model.addAttribute("termsOfAgreement", termsOfAgreement);

		return "register";

	}

	// 회원가입 전송
	@PostMapping("registerPOST")
	public void registerPOST(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		if (userService.insert(user)) {
			out.println("<script>");
			out.println("alert('회원가입 성공');");
			out.println("location.href='login';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원가입 실패');");
			out.println("location.href='register';");
			out.println("</script>");
		}
	}

	// 이메일 중복 확인
	public String registerCheck(UserDTO user, HttpServletRequest req, HttpServletResponse res) {
		int result = userService.emailCheck(user);
		if (result != 0) {
			return "fail"; // 중복 아이디가 존재

		} else {
			return "success"; // 중복 아이디 x
		}
	}

	// 비밀번호 변경
	@PostMapping("changepwdConfirm")
	public void pwchangeConfirm(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html; charset=utf-8");
		session = req.getSession();
		PrintWriter out = res.getWriter();
		String pass = req.getParameter("pw-new");
		String newPwConfirm = req.getParameter("pw-confirm");

		if (pass.equals(newPwConfirm)) {

			userService.modifyPassword(user);
			session.invalidate();
			out.println("<script>");
			out.println("alert('비밀번호가 변경되었습니다');");
			out.println("location.href='/';");
			out.println("</script>");

		} else {
			out.println("<script>");
			out.println("alert('입력한 비밀번호가 일치하지 않습니다');");
			out.println("location.href='/changepwd';");
			out.println("</script>");
		}
	}

	// -----------------------------------------------------//
	// 마이페이지
	@GetMapping("mypage")
	public String mypage(Model model, HttpServletRequest req, HttpServletResponse res) {
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");
		return "mypage";
	}

	// 마이페이지 프로필 업데이트
	@PostMapping("mypageUpdateProfileImg")
	public String mypageUpdateProfileImg(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res,
			@RequestParam("upload-img") MultipartFile profile_img) throws IOException {
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");

		// CDN 연결
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("", "", "", "", "", ""));

		// 최적의 프로필 이미지를 위해 이미지 편집 후 업로드
		@SuppressWarnings("rawtypes")
		Map result = cloudinary.uploader().upload(convert(profile_img), ObjectUtils.asMap("folder", "userprofile",
				"transformation", new Transformation().gravity("auto:classic").width(400).height(400).crop("thumb")));
		String profile_img_url = (String) result.get("secure_url");

		// 세션을 갱신하여 프로필 변경사항을 웹 뷰에서 즉시 적용
		userService.updateProfileImg(user);
		// userInfo.setProfile_image(profile_img_url);
		session.removeAttribute("mem");
		session.setAttribute("mem", userInfo);

		return "redirect:mypage";
	}

	// multipart -> 파일 변환(stream 사용. heroku에서 파일제어에 제약이 있기 때문)
	public File convert(MultipartFile file) throws IOException {
		// 파일명 충돌방지
		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + file.getOriginalFilename();
		File convFile = new File(uuidFilename);
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// 마이페이지 계정 수정
	@PostMapping("mypageModifyAccount")
	public void mypageModifyAccount(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		UserDTO user_no = (UserDTO) session.getAttribute("mem");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		user.setUser_id(user_no.getUser_id());

		if (userService.modifyAccount(user)) {
			UserDTO userInfo = (UserDTO) userService.getUser(user);
			session.setAttribute("mem", userInfo);
			System.out.println(userInfo);
			out.println("<script>");
			out.println("alert('회원 정보가 변경되었습니다.');");
			out.println("location.href='mypage';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 정보 변경이 실패했습니다.');");
			out.println("location.href='/changepwd';");
			out.println("</script>");
		}
	}

	// 마이페이지 계정 삭제
	@GetMapping("mypageDeleteAccount")
	public void mypageDeleteAccount(Model model, HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		if (userService.deleteAccount(userInfo)) {
			out.println("<script>");
			out.println("alert('회원 탈퇴되었습니다.');");
			out.println("location.href='logout';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 탈퇴 실패했습니다. 다시 시도해주시기바랍니다.');");
			out.println("location.href='mypage';");
			out.println("</script>");
		}
	}

	// 비밀번호 중복 확인
	@PostMapping("newPassCheck")
	public String newPassCheck(String newPass, String passCheck) {
		boolean result = newPass.equals(passCheck);
		if (result != true) {
			return "fail"; // 비밀번호가 같지 않음

		} else {
			return "success"; // 비밀번호 동일
		}
	}

	// 회원 비밀번호 변경
	@PostMapping("modifyPassword")
	public void modifyPassword(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");
		String beforePass = req.getParameter("beforePass");
		String newPass = req.getParameter("newPass");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		if (beforePass.equals(userService.userLogin(userInfo))) {
			userService.modifyPassword(user);
			session.invalidate();
			out.println("<script>");
			out.println("alert('변경된 비밀번호로 다시 로그인바랍니다.');");
			out.println("location.href='login';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('이전 비밀번호가 틀렸습니다. 다시 확인바랍니다.');");
			out.println("location.href='mypage';");
			out.println("</script>");
		}
	}

	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	// 구글로그인

	@GetMapping("googleLogin")
	public String googleLogin(Model model) {
		return "googleLogin";
	}

}
