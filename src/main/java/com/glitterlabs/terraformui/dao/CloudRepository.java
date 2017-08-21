package com.glitterlabs.terraformui.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.glitterlabs.terraformui.model.Cloud;

public interface CloudRepository extends CrudRepository<Cloud, Long> {
	@Cacheable("clouds")
	Cloud findByName(String name);
}