package com.pmis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pmis.model.CmmnDTO;
import com.pmis.service.CmmnService;

@Controller
public class CmmnController {
	@Autowired
	private CmmnService cmmnService;

	@GetMapping("/")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		String userName = "홍길동";
		//CmmnDTO dto = cmmnService.findUser(userName);
		//model.addAttribute(dto);
		return "index";
	}

}
