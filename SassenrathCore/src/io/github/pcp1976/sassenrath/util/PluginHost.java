package io.github.pcp1976.sassenrath.util;

import java.util.ArrayList;
import java.util.List;

import io.github.pcp1976.sassenrath.api.extend.*;
import io.github.pcp1976.sassenrath.core.ConcreteJob;

public class PluginHost implements Pipe {
	private Behaviour behaviour;
	private List<Job> jobList = new ArrayList<>();
	private List<Source> isSinkOf = new ArrayList<>();
	private List<Sink> isSourceFor = new ArrayList<>();
	String name;
	
	public PluginHost() {
	}

	public PluginHost(String name, Behaviour behaviour) {
		this.setBehaviour(behaviour);
	}

	private void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	private Behaviour getBehaviour() {
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
		this.setJobList(null); //perhaps don't need to clear completely? Need to consider this carefully
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
	public boolean addSource(Source source) {
		return this.getIsSinkOf().add(source);
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
	public boolean addSink(Sink sink) {
		return this.getIsSourceFor().add(sink);
	}

	@Override
	public boolean isInSinks(Sink sink) {
		return this.getIsSourceFor().contains(sink);
	}

	@Override
	public boolean cycleCheck(Pipe pipe) {
		if(isInSources(pipe)){
			return false; //fail early, we're attempting to add source to its own sources!
		}
		for(Source s : this.getIsSinkOf()){
				if(s instanceof Pipe){ //if the source has sources of its own
					if(!((Pipe) s).cycleCheck(pipe)){
						return false; // fail if we find a match in amongst our source's sources
					}
				}
			}
		
		return true; // if we made it here, no match was found
	}

}
