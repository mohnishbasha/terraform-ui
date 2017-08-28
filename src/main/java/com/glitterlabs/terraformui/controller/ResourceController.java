package com.glitterlabs.terraformui.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glitterlabs.terraformui.model.Resource;
import com.glitterlabs.terraformui.service.ResourceService;

@RestController
public class ResourceController {

	private ResourceService resourceService;

	@Autowired
	public void setProjectService(final ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@GetMapping("/project/{projectId}/resources/**")
	public ResponseEntity<List<Resource>> resources(@PathVariable final String projectId, final HttpServletRequest request) {
		final String urlTail = new AntPathMatcher().extractPathWithinPattern("/project/{projectId}/resources/**", request.getRequestURI());
		String decodedValue = "";
		try {
			decodedValue = URLDecoder.decode(urlTail, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
		}
		final List<Resource> result = this.resourceService.findAllResources(projectId, decodedValue);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/project/{projectId}/resource/**")
	public ResponseEntity<String> getResource(@PathVariable final String projectId, final HttpServletRequest request) {
		final String urlTail = new AntPathMatcher().extractPathWithinPattern("/project/{projectId}/resource/**", request.getRequestURI());
		String resourcePath = "";
		try {
			resourcePath = URLDecoder.decode(urlTail, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
		}
		String result = "";
		try {
			result = this.resourceService.getResourceContent(projectId, resourcePath);
		} catch (final IOException e) {
			return new ResponseEntity<>("Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/project/{projectId}/**")
	public ResponseEntity<?> addResource(@PathVariable final String projectId, @RequestBody final Map<String, Object> payload, final HttpServletRequest request) {
		final String urlTail = new AntPathMatcher().extractPathWithinPattern("/project/{projectId}/resources/**", request.getRequestURI());
		String decodedValue = "";
		try {
			decodedValue = URLDecoder.decode(urlTail, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
		}
		final String resourceName = (String) payload.get("resource_name");
		final String fileContent = (String) payload.get("file_content");
		final String resourceType = (String) payload.get("resource_type");
		try {
			this.resourceService.create(projectId, decodedValue + File.separator + resourceName, fileContent, resourceType);
		} catch (final IOException e) {
			return new ResponseEntity<>("Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Success.");
	}
}
