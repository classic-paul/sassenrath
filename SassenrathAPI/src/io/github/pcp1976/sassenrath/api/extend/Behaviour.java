package io.github.pcp1976.sassenrath.api.extend;

public interface Behaviour {

	public void createCommandLine(Job job);
	public void createOutputFilePath(Job job);
	public void configure();
}
