package io.github.pcp1976.sassenrath.api.extend;

import java.util.List;

public interface Plugin {
	public List<Job> getJobList();
	public void setJobList(List<Job> jobList);
	public List<Plugin> getIsServerFor();
	public void setIsServerFor(List<Plugin> isServerFor);
	public List<Plugin> getIsClientOf();
	public void setIsClientOf(List<Plugin> isClientOf);
	public void addClient(Plugin client);
	public void addServer(Plugin server);
	public boolean isInClients(Plugin client);
	public boolean isInServers(Plugin server);
	public void updateJobs();
	public String getName();
	public void setName(String name);
	public void configure();
}
