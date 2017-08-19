package com.glitterlabs.project.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.glitterlabs.project.model.Cloud;

public interface CloudRepository extends CrudRepository<Cloud, Long> {
	@Cacheable("clouds")
	Cloud findByName(String name);
}