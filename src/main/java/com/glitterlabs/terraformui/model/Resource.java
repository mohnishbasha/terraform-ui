package com.glitterlabs.terraformui.model;

public class Resource {

	private String name;

	private boolean isFile;

	public Resource(final String name, final boolean isFile) {
		this.name = name;
		this.isFile = isFile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isFile() {
		return this.isFile;
	}

	public void setFile(final boolean isFile) {
		this.isFile = isFile;
	}
}
