package com.glitterlabs.terraformui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glitterlabs.terraformui.dao.CloudRepository;
import com.glitterlabs.terraformui.model.Cloud;

@Service
public class CloudService {

	@Autowired
	private CloudRepository dao;

	public List<Cloud> findAllClouds() {
		final List<Cloud> result = new ArrayList<>();
		this.dao.findAll().iterator().forEachRemaining(cloud -> result.add(cloud));
		return result;
	}

}
