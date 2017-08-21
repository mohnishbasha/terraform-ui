package com.glitterlabs.terraformui.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glitterlabs.terraformui.dao.ProjectRepository;
import com.glitterlabs.terraformui.model.Project;
import com.glitterlabs.terraformui.model.Resource;
import com.glitterlabs.terraformui.util.DirectoryUtil;
import com.glitterlabs.terraformui.util.GlobalProperties;

/**
 * The Class CloudProjectService.
 */
@Service
public class ResourceService {
	private static final Logger LOG = LoggerFactory.getLogger(ResourceService.class);

	/** The dao. */
	@Autowired
	private ProjectRepository dao;

	@Autowired
	private GlobalProperties prop;

	/**
	 * Creates the resource.
	 *
	 * @param projectId the project id
	 * @param name the name
	 * @param content the content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void create(final String projectId, final String name, final String content, final String resourceType) throws IOException {
		final Project project = this.dao.findOne(Long.valueOf(projectId));
		final Path path = Paths.get(this.prop.getDirectoryPath(), project.getPath(), name);
		if (StringUtils.equalsIgnoreCase(resourceType, "file")) {
			Files.createFile(path);
			final FileWriter fileWriter = new FileWriter(path.toFile());
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();
		} else if (StringUtils.equalsIgnoreCase(resourceType, "directory")) {
			Files.createDirectory(path);
		}
		LOG.debug("Create {}.", path);
	}

	public List<Resource> findAllResources(final String projectId, final String subpath) {
		List<Resource> result = new ArrayList<>();
		final Project project = this.dao.findOne(Long.valueOf(projectId));
		if (project != null) {
			try {
				result = DirectoryUtil.getResources(Paths.get(this.prop.getDirectoryPath(), project.getPath(), subpath));
			} catch (final IOException e) {
				LOG.error("Unable to get resources.", e);
			}
		}
		return result;
	}

	public String getResourceContent(final String projectId, final String resourcePath) throws IOException {
		final Project project = this.dao.findOne(Long.valueOf(projectId));
		final Path path = Paths.get(this.prop.getDirectoryPath(), project.getPath(), resourcePath);
		final byte[] bytes = Files.readAllBytes(path);
		return new String(bytes);
	}

}
