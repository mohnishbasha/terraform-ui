package com.glitterlabs.terraformui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@GetMapping("/")
	public String dashboard() {
		return "dashboard";
	}
}