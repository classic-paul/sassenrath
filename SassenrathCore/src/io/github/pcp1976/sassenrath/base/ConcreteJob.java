package io.github.pcp1976.sassenrath.base;

import java.nio.file.Path;

import io.github.pcp1976.sassenrath.api.extend.Job;
import io.github.pcp1976.sassenrath.api.extend.Plugin;

public class ConcreteJob implements Job {
	Path inputFilePath;
	Path outPutFilePath;
	String commandLine;
	Plugin plugin;

	@Override
	public Path getInputFilePath() {
		return this.inputFilePath;
	}

	@Override
	public void setInputFilePath(Path path) {
		this.inputFilePath = path;
	}

	@Override
	public Path getOutputFilePath() {
		return this.outPutFilePath;
	}

	@Override
	public void setOutputFilePath(Path path) {
		this.outPutFilePath = path;
	}

	@Override
	public Plugin getPlugin() {
		return this.plugin;
	}

	@Override
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getCommandLine() {
		return this.commandLine;
	}

	@Override
	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

}
