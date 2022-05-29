package com.pmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pmis.service.CmmnService;

@Controller
public class CmmnController {

	@Autowired
	private CmmnService cmmnService;

	@GetMapping("/")
	public String index(Model model) {

		return "index";
	}

	@GetMapping("errorHandler")
	public String errorHandler(Model model) {
		return "errorHandler";
	}

	@GetMapping("introduce")
	public String introduce(Model model) {
		return "introduce";
	}

}
