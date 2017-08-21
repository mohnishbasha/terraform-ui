package com.glitterlabs.terraformui.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.glitterlabs.terraformui.model.Cloud;
import com.glitterlabs.terraformui.model.Project;

/**
 * The Interface CloudProjectRepository.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list of projects
	 */
	List<Project> findByName(String name);

	/**
	 * Find by cloud type.
	 *
	 * @param cloud
	 * @return the list of projects
	 */
	List<Project> findByCloudType(Cloud cloud);
}