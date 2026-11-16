package com.nys.dcss.dcss_case_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DcssController {
	@GetMapping("/")
	public String home() {
	    return "Welcome to NYS DCSS Child Support Portal!";
	}

	@GetMapping("/api/v1/health")
	public String health() {
	    return "NYS DCSS Application is running successfully!";
	}
}
