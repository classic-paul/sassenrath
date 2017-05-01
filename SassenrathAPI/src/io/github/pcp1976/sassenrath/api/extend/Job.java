package io.github.pcp1976.sassenrath.api.extend;

import java.nio.file.Path;

public interface Job {
	public Path getInputFilePath();

	public void setInputFilePath(Path path);

	public Path getOutputFilePath();

	public void setOutputFilePath(Path path);

	public Plugin getPlugin();

	public void setPlugin(Plugin plugin);

	public String getCommandLine();

	public void setCommandLine(String commandLine);
}
