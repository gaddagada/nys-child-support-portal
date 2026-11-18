package com.nys.dcss.casemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcssController {
	@GetMapping("/")
	public String dcssPortal() {
		return "Welcome to NYS DCSS Child Support Portal!";
	}
}
