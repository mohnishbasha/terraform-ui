package com.glitterlabs.terraformui.util;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class GlobalProperties {

	@Value("${directory.root}")
	private String directoryPath;

	@Value("${terraform.exe.path}")
	private String terraformExePath;

	public String getTerraformExePath() {
		return Paths.get(this.terraformExePath).toString();
	}

	public void setTerraformExePath(final String terraformExePath) {
		this.terraformExePath = terraformExePath;
	}

	public String getDirectoryPath() {
		return this.directoryPath;
	}

	public void setDirectoryPath(final String directoryPath) {
		this.directoryPath = directoryPath;
	}

}
