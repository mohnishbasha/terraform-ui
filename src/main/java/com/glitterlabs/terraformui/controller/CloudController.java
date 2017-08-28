package com.glitterlabs.terraformui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glitterlabs.terraformui.model.Cloud;
import com.glitterlabs.terraformui.service.CloudService;

@RestController
public class CloudController {

	private CloudService cloudService;

	@Autowired
	public void setCloudService(final CloudService cloudService) {
		this.cloudService = cloudService;
	}

	@GetMapping("/clouds")
	public ResponseEntity<List<Cloud>> dashboard() {
		final List<Cloud> result = this.cloudService.findAllClouds();
		return ResponseEntity.ok(result);
	}

}
