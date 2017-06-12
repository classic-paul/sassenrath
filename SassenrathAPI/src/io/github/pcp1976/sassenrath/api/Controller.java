package io.github.pcp1976.sassenrath.api;

import java.util.List;

import io.github.pcp1976.sassenrath.api.extend.Plugin;

public interface Controller {
	public boolean linkSourceAndSink(Plugin source, Plugin sink);
	public void beginWork();
	public boolean addPlugin(Plugin plugin);
	public boolean removePlugin(Plugin plugin);
	public void configurePlugin(Plugin plugin);
	public List<Plugin> getPluginGraph();
	public void setPluginGraph(List<Plugin> pluginGraph);
}
