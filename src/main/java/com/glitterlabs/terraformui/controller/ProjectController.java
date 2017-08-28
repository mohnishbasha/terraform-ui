package com.glitterlabs.terraformui.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glitterlabs.terraformui.model.Project;
import com.glitterlabs.terraformui.model.Project.ProjectStatus;
import com.glitterlabs.terraformui.service.ProjectService;
import com.glitterlabs.terraformui.util.GlobalProperties;
import com.glitterlabs.terraformui.util.PollingMessage;
import com.google.common.util.concurrent.Uninterruptibles;

@RestController
public class ProjectController {
	@Autowired
	private GlobalProperties prop;

	private ProjectService projectService;

	@Autowired
	public void setProjectService(final ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping("/cloud/{cloudType}/projects")
	public ResponseEntity<List<Project>> cloudProjects(@PathVariable final String cloudType) {
		final List<Project> result = this.projectService.findAllProjectsByCloud(cloudType);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/cloud/{cloudType}/projects")
	public ResponseEntity<?> addCloudProjects(@Valid @RequestBody final Project project) {
		project.setStatus(Project.ProjectStatus.CREATED);
		try {
			this.projectService.create(project);
		} catch (final IOException e) {
			return new ResponseEntity<>("Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(project);
	}

	@PostMapping("/project/{projectId}/apply")
	public ResponseEntity<String> applyProject(@PathVariable final Long projectId) {
		final String pollId = String.valueOf(projectId);
		new Thread(new Runnable() {

			@Override
			public void run() {
				final Project project = ProjectController.this.projectService.findById(projectId);
				try {
					final String cmd = ProjectController.this.prop.getTerraformExePath();
					final ProcessBuilder builder = new ProcessBuilder(cmd, "apply");
					builder.directory(Paths.get(ProjectController.this.prop.getDirectoryPath(), project.getPath()).toFile());
					final Process process = builder.start();
					final BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("UTF-8").newDecoder()));
					final BufferedReader errorInput = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					String s = null;
					while ((s = stdInput.readLine()) != null) {
						System.out.println(s);
						PollingMessage.setNextMessage(pollId, s);
					}
					while ((s = errorInput.readLine()) != null) {
						System.out.println(s);
						PollingMessage.setNextMessage(pollId, s);
					}
					PollingMessage.setNextMessage(pollId, "Complete");
					ProjectController.this.projectService.updateStatus(projectId, ProjectStatus.ACTIVE);
				} catch (final IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		return ResponseEntity.ok("{pollId:" + pollId + "}");
	}

	@GetMapping("/project/{projectId}/poll")
	public ResponseEntity<String> poll(@PathVariable final String projectId) {
		return ResponseEntity.ok(PollingMessage.getNextMessage(projectId));
	}
}
