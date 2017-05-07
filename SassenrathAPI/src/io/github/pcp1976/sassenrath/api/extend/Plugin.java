package io.github.pcp1976.sassenrath.api.extend;

import java.util.List;

public interface Plugin {
	public List<Job> getJobList();
	public void setJobList(List<Job> jobList);
	public void updateJobs();
	public String getName();
	public void configure();
	public void setName(String name);
	}
