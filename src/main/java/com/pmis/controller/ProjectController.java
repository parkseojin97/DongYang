package com.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmis.model.ProjectBoardDTO;
import com.pmis.model.ProjectDTO;
import com.pmis.model.ProjectJoinDTO;
import com.pmis.model.ProjectRuleDTO;
import com.pmis.model.ProjectStatusDTO;
import com.pmis.model.UserDTO;
import com.pmis.module.Pagination;
import com.pmis.service.ProjectService;

@Controller
public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	HttpSession session;

	
	// 유저정보 가져와서 (session에서 받기) 검색해서 프로젝트 리스트 가져오기 
	@GetMapping("projects")
	public String projects(Model model, HttpServletRequest req, HttpServletResponse res) {
		session = req.getSession();
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");	
		ArrayList<ProjectDTO> projectList = projectService.selectProjects(userInfo);
		model.addAttribute("projectList", projectList);
		
		return "projects";
	}
	
	// 프로젝트 상세 페이지
	@GetMapping("project")
	public String selectProject(Model model, ProjectDTO project, HttpServletRequest req, HttpServletResponse res) {
		// tasks : 칸반 종류, boards : 칸반안의 게시글 목록, rules : 프로젝트 룰 목록, joins : 프로젝트 참여인원, project : 받아온 프로젝트 
		ArrayList<ProjectStatusDTO> tasks = projectService.selectBoardStatus(project);
		ArrayList<ProjectBoardDTO> boards = projectService.selectBoards(project);
		ArrayList<ProjectRuleDTO> rules = projectService.selectRule(project);
		ArrayList<ProjectJoinDTO> joins = projectService.selctGroup(project);
		
		model.addAttribute("tasks", tasks);
		model.addAttribute("boards", boards);
		model.addAttribute("rules", rules);
		model.addAttribute("joins", joins);		
		model.addAttribute("project", project);
		
		return "project";
	}
	
	//프로젝트 생성
	@PostMapping("createproject")
	public void createProject(Model model, ProjectDTO project, HttpServletRequest req, HttpServletResponse res)
			throws IOException{
		session = req.getSession();
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");
		res.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = res.getWriter();
    	
    	ProjectJoinDTO join = new ProjectJoinDTO();    	
    	ProjectStatusDTO projectstatus = new ProjectStatusDTO();
    	
    	
    	if(projectService.createProject(project)) {

    		project.setProject_id(projectService.selectLatestProject().getProject_id());
    		join.setUser_email(userInfo.getUser_email());
        	join.setProject_id(project.getProject_id());
        	join.setRole("관리자");
        	join.setJoin_status("admin");        	
    		projectService.insertGroup(join);
    		
    		projectService.createDefaultKanban(project);
    		
			out.println("<script>");
			out.println("alert('프로젝트 생성완료');");
			out.println("location.href='projects';");
			out.println("</script>");

    	} else {
    		out.println("<script>");
			out.println("alert('프로젝트 생성실패');");
			out.println("location.href='projects';");
			out.println("</script>");
    	}
	}
	//프로젝트 삭제
	@PostMapping("deleteproject")
	public void deleteProject(Model model, ProjectDTO project, HttpServletRequest req, HttpServletResponse res)
			throws IOException{
		
		session = req.getSession();
		UserDTO userInfo = (UserDTO) session.getAttribute("mem");
		res.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = res.getWriter();
    	ArrayList<ProjectJoinDTO> joins = projectService.selctGroup(project);
    	if(userInfo.getUser_email().equals(joins)) {
    	
		if(projectService.deleteProject(project)) {
			out.println("<script>");
			out.println("alert('프로젝트 삭제완료');");
			out.println("location.href='projects';");
			out.println("</script>");
    	} else {
    		out.println("<script>");
			out.println("alert('프로젝트 삭제실패');");
			out.println("location.href='projects';");
			out.println("</script>");
    	}
    	}
	}

	// 프로젝트 세팅 화면 이동
	@GetMapping("settings")
	public String settings(Model model, ProjectDTO project, HttpServletRequest req, HttpServletResponse res) {		
		ArrayList<ProjectJoinDTO> group =  projectService.selctGroup(project);
//		session = req.getSession();
		model.addAttribute("group", group);
		model.addAttribute("project", project);		
		return "settings";
	}
	
	//공개범위가 public인 프로젝트 리스트 페이징 처리해서 이동
	@GetMapping("community")
	public String comunity(Model model, @RequestParam(defaultValue = "1") int page, HttpServletRequest req, HttpServletResponse res) {		
		
		// 총 게시물 수
		int totalListCnt = projectService.selectPublicProjectCnt();
		
		// 생성인자로 총 게시물 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		int startIndex = pagination.getStartIndex();
		int pageSize = pagination.getPageSize();
		ArrayList<ProjectDTO> projectList = projectService.selectPagingProjects(startIndex, pageSize);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("projectList", projectList);
		return "community";
	}
	

	// 프로젝트 보드 생성
	@PostMapping("createboard")
	public void createboard(Model model, ProjectStatusDTO projectboard, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		res.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = res.getWriter();
    	
    	if(projectService.createBoardStatus(projectboard)) {
			out.println("<script>");
			out.println("alert('프로젝트 보드 생성완료');");
			out.println("location.href='project?project_id="+ projectboard.getProject_id() +"';");
			out.println("</script>");
    	}else {
			out.println("<script>");
			out.println("alert('프로젝트 보드 생성실패');");
			out.println("location.href='projects;");
			out.println("</script>");
    	}
    	
	}
	// 그룹 초대 요청 
	@PostMapping("inviteGroup")
	public void insertGroup(Model model, ProjectDTO project, ProjectJoinDTO join, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		join.setJoin_status("invite");
		join.setRole("초대 요청");
		if(projectService.insertGroup(join)) {
			model.addAttribute("joins", projectService.selctGroup(project));
			out.println("<script>");
			out.println("alert('초대 요청 성공');");
			out.println("location.href='/settings/ "+ project +"';");	
			out.println("</script>");			
		} else {
			out.println("<script>");
			out.println("alert('그룹원 추가 요청 실패');");
			out.println("location.href='/settings';");
			out.println("</script>");
		}
	}
	
	// 그룹 참가 요청
	@PostMapping("requestGroup")
	public void requestGroup(Model model, ProjectJoinDTO join, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		join.setJoin_status("request");
		join.setRole("참가 요청");
		if(projectService.insertGroup(join)) {
			out.println("<script>");
			out.println("alert('그룹 참가 요청 성공');");
			out.println("location.href='/community';");	
			out.println("</script>");			
		} else {
			out.println("<script>");
			out.println("alert('그룹 참가 요청 실패');");
			out.println("location.href='/community';");
			out.println("</script>");
		}
	}
	
	// 그룹 참가 요청 처리
	@PostMapping("updateGroup")
	public void updateGroup(Model model, ProjectJoinDTO join, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		if(projectService.updateGroup(join)) {
			out.println("<script>");
			out.println("alert('그룹원 요청 처리 성공');");
			out.println("location.href='/settings';");	
			out.println("</script>");			
		} else {
			out.println("<script>");
			out.println("alert('그룹원 요청 처리 실패');");
			out.println("location.href='/settings';");
			out.println("</script>");
			
		}
		
	}
	
	// 참가요청자 혹은 멤버의 정보 삭제
	@PostMapping("deleteGroup")
	public void deleteGroup(Model model, ProjectJoinDTO join, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		if(projectService.deleteProjectUser(join)) {
			out.println("<script>");
			out.println("alert('그룹원 삭제 성공');");
			out.println("location.href='/settings';");	
			out.println("</script>");			
		} else {
			out.println("<script>");
			out.println("alert('그룹원 삭제 실패');");
			out.println("location.href='/settings';");
			out.println("</script>");
			
		}
	}

			
	@SuppressWarnings("null")
	@GetMapping("dashboard")
	public String dashboard(Model model, UserDTO user, HttpServletRequest req, HttpServletResponse res) {
		ArrayList<ProjectDTO> projects = projectService.selectProjects(user);
		ArrayList<ProjectBoardDTO> boards = null; 
		for (ProjectDTO p : projects) {			
			ArrayList<ProjectBoardDTO> board = projectService.selectBoards(p);
			
			boards.addAll(board);
		}
		model.addAttribute("projects", projects);
		model.addAttribute("boards", boards);
		
		return "dashboard";
	}	
	
	
	
	
	// 당장 안할 것
	
	@GetMapping("meeting")
	public String meeting(Model model) {
		return "meeting";
	}
	
	@GetMapping("meetingroom")
	public String meetingRoom(Model model) {

		return "meetingroom";
	}

	@GetMapping("meetinglog")
	public String meetingLog(Model model) {

		return "meetinglog";
	}	
	

	@GetMapping("schedule")
	public String schedule(Model model) {

		return "schedule";
	}

	

}
