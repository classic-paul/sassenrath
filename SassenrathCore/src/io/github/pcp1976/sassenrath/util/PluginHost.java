package io.github.pcp1976.sassenrath.util;

import java.util.ArrayList;
import java.util.List;

import io.gihub.pcp1976.sassenrath.base.ConcreteJob;
import io.github.pcp1976.sassenrath.api.extend.Job;
import io.github.pcp1976.sassenrath.api.extend.Plugin;
import io.github.pcp1976.sassenrath.api.extend.PluginBehaviour;

public class PluginHost implements Plugin {
	private PluginBehaviour behaviour;
	private List<Job> jobList = new ArrayList<>();
	private List<Plugin> isServerFor = new ArrayList<>();
	private List<Plugin> isClientOf = new ArrayList<>();
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
	public List<Plugin> getIsServerFor() {
		return this.isServerFor;
	}

	@Override
	public void setIsServerFor(List<Plugin> isServerFor) {

		this.isServerFor = isServerFor;

	}

	@Override
	public List<Plugin> getIsClientOf() {
		return this.isClientOf;
	}

	@Override
	public void setIsClientOf(List<Plugin> isClientOf) {

		this.isClientOf = isClientOf;

	}

	@Override
	public void addClient(Plugin client) {

		getIsServerFor().add(client);

	}

	@Override
	public void addServer(Plugin server) {

		this.getIsClientOf().add(server);

	}

	@Override
	public boolean isInClients(Plugin client) {

		boolean returnboolean = this.getIsServerFor().contains(client);

		return returnboolean;
	}

	@Override
	public boolean isInServers(Plugin server) {

		boolean returnboolean = this.isInServers(server);

		return returnboolean;
	}

	@Override
	public void updateJobs() {

		this.setJobList(null);
		for (Plugin server : this.getIsClientOf()) {
			for (Job serverJob : server.getJobList()) {
				Job newJob = new ConcreteJob();
				newJob.setInputFilePath(serverJob.getOutputFilePath());

				newJob.setPlugin(this);
				this.getBehaviour().createCommandLine(newJob);
				this.getBehaviour().createOutputFilePath(newJob);
				this.getJobList().add(newJob);
			}

		}
		for (Plugin client : this.getIsServerFor()) {
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

}
