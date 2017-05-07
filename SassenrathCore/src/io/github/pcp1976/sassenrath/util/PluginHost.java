package io.github.pcp1976.sassenrath.util;

import java.util.ArrayList;
import java.util.List;

import io.gihub.pcp1976.sassenrath.base.ConcreteJob;
import io.github.pcp1976.sassenrath.api.extend.*;

public class PluginHost implements Pipe {
	private PluginBehaviour behaviour;
	private List<Job> jobList = new ArrayList<>();
	private List<Source> isSinkOf = new ArrayList<>();
	private List<Sink> isSourceFor = new ArrayList<>();
	String name;
	
	public PluginHost() {
	}

	public PluginHost(String name, PluginBehaviour behaviour) {
		this.setBehaviour(behaviour);
	}

	private void setBehaviour(PluginBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	private PluginBehaviour getBehaviour() {
		return this.behaviour;
	}

	@Override
	public List<Job> getJobList() {
		return this.jobList;
	}

	@Override
	public void setJobList(List<Job> jobList) {

		this.jobList = jobList;

	}

	@Override
	public void updateJobs() {

		this.setJobList(null);
		for (Source server : this.getIsSinkOf()) {
			for (Job serverJob : server.getJobList()) {
				Job newJob = new ConcreteJob();
				newJob.setInputFilePath(serverJob.getOutputFilePath());

				newJob.setPlugin(this);
				this.getBehaviour().createCommandLine(newJob);
				this.getBehaviour().createOutputFilePath(newJob);
				this.getJobList().add(newJob);
			}

		}
		for (Plugin client : this.getIsSourceFor()) {
			client.updateJobs();

		}

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void configure() {
		this.getBehaviour().configure();
	}

	@Override
	public List<Source> getIsSinkOf() {
		return this.isSinkOf;
	}

	@Override
	public void setIsSinkOf(List<Source> isSinkOf) {
		this.isSinkOf = isSinkOf;
	}

	@Override
	public void addSource(Source source) {
		this.getIsSinkOf().add(source);
	}

	@Override
	public boolean isInSources(Source source) {
		return this.getIsSinkOf().contains(source);
	}

	@Override
	public List<Sink> getIsSourceFor() {
		return this.isSourceFor;
	}

	@Override
	public void setIsSourceFor(List<Sink> isSourceFor) {
		this.isSourceFor = isSourceFor;
	}

	@Override
	public void addSink(Sink sink) {
		this.getIsSourceFor().add(sink);
	}

	@Override
	public boolean isInSinks(Sink sink) {
		return this.getIsSourceFor().contains(sink);
	}

}
