package com.pmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pmis.service.CmmnService;

@Controller
public class ProjectController {


	@GetMapping("projects")
	public String projects(Model model) {

		return "projects";
	}
	
	@GetMapping("settings")
	public String settings(Model model) {
		
		return "settings";
	}
	
	@GetMapping("project")
	public String selectProject(Model model) {
		
		return  "project";
	}
	
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
	
	@GetMapping("dashboard")
	public String dashboard(Model model) {
		
		return "dashboard";
	}

}
