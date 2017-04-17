package io.github.pcp1976.sassenrath.directorysourceplugin;

import java.nio.file.Path;
/*
 * Unlikely that such a plugin would be required to persist its state?
 * However, for practice and completeness...
 */
public class Model {
	private Path directory;

	public Path getDirectory() {
		return directory;
	}

	public void setDirectory(Path directory) {
		this.directory = directory;
	}
}
