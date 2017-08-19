package com.glitterlabs.project.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class GlobalProperties {

	@Value("${directory.root}")
	private String directoryPath;

	public String getDirectoryPath() {
		return this.directoryPath;
	}

	public void setDirectoryPath(final String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getTerraformPath() throws IOException {
		return new ClassPathResource("terraform.exe").getFile().getAbsolutePath(); // TODO
	}
}
