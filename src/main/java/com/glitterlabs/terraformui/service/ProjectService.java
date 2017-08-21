package com.glitterlabs.terraformui.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glitterlabs.terraformui.dao.CloudRepository;
import com.glitterlabs.terraformui.dao.ProjectRepository;
import com.glitterlabs.terraformui.model.Cloud;
import com.glitterlabs.terraformui.model.Project;
import com.glitterlabs.terraformui.model.Project.ProjectStatus;
import com.glitterlabs.terraformui.util.GlobalProperties;
import com.glitterlabs.terraformui.util.ResourceNameGenerator;

/**
 * The Class CloudProjectService.
 */
@Service
public class ProjectService {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectService.class);

	/** The project dao. */
	@Autowired
	private ProjectRepository projectdao;

	@Autowired
	private CloudRepository cloudDao;

	@Autowired
	private GlobalProperties prop;

	/**
	 * Find all projects by cloud.
	 *
	 * @param cloudType the cloud type
	 * @return the list
	 */
	public List<Project> findAllProjectsByCloud(@NotNull final String cloudType) {
		final List<Project> result = new ArrayList<>();
		final Cloud cloud = this.cloudDao.findByName(cloudType.toUpperCase());
		this.projectdao.findByCloudType(cloud).iterator().forEachRemaining(project -> result.add(project));
		return result;
	}

	/**
	 * Find by id.
	 *
	 * @param projectId the project id
	 * @return the project
	 */
	public Project findById(final Long projectId) {
		return this.projectdao.findOne(projectId);
	}

	/**
	 * Create new project.
	 *
	 * @param project the project
	 * @throws IOException
	 */
	public void create(final Project project) throws IOException {
		final String generateDirectoryName = ResourceNameGenerator.generateName();
		Files.createDirectory(Paths.get(this.prop.getDirectoryPath(), generateDirectoryName));
		LOG.debug("Directory {} created.", generateDirectoryName);
		final Date date = new Date();
		project.setDate(date);
		final Cloud dbCloud = this.cloudDao.findByName(project.getCloudType().getName().toUpperCase());
		project.setCloudType(dbCloud);
		project.setPath(generateDirectoryName);
		this.projectdao.save(project);
	}

	public void updateStatus(final Long projectId, final ProjectStatus status) {
		final Project project = this.projectdao.findOne(projectId);
		project.setStatus(status);
		this.projectdao.save(project);
	}

}
