package com.pmis.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmis.model.CmmnDTO;
import com.pmis.service.CmmnService;

@RestController
@RequestMapping("/cmmn")
public class CmmnRestController {
	@Autowired
	private CmmnService cmmnService;

	@GetMapping("/foo")
	public ResponseEntity<CmmnDTO> index(@RequestParam("userName") String userName) {

		return ResponseEntity.ok(cmmnService.findUser(userName));

	}

}
