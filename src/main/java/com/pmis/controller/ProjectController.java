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

import com.pmis.model.ProjectBoardDTO;
import com.pmis.model.ProjectDTO;
import com.pmis.model.ProjectJoinDTO;
import com.pmis.model.ProjectRuleDTO;
import com.pmis.model.ProjectStatusDTO;
import com.pmis.model.UserDTO;
import com.pmis.service.ProjectService;

@Controller
public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	HttpSession session;

	
	// 유저정보 가져와서 (session에서 받기) 검색해서 프로젝트 리스트 가져오기 
	@GetMapping("projects")
	public String projects(Model model) {
		return "projects";
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
	
	@GetMapping("community")
	public String comunity(Model model, HttpServletRequest req, HttpServletResponse res) {
		ArrayList<ProjectDTO> projects = projectService.selectProjects();
		
		model.addAttribute("projects", projects);
		return "community";
	}
	
	// 그룹 초대 요청 
	@PostMapping("inviteGroup")
	public void insertGroup(Model model, ProjectDTO project, ProjectJoinDTO join, HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
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
