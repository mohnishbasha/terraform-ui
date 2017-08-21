package com.glitterlabs.terraformui.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.glitterlabs.terraformui.model.Resource;

public class DirectoryUtil {

	public static List<Resource> getResources(final Path directoryPath) throws IOException {
		final List<Resource> names = new ArrayList<>();
		if (directoryPath.toFile().isDirectory()) {
			Files.list(directoryPath).forEach(path -> {
				if (!(StringUtils.endsWith(path.toFile().getName(), "exe")) && !(StringUtils.endsWith(path.toFile().getName(), "backup"))
						&& !(StringUtils.endsWith(path.toFile().getName(), "tfstate"))) {
					names.add(new Resource(path.toFile().getName(), path.toFile().isFile()));
				}

			});
		}
		return names;
	}
}
