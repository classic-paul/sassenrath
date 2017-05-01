package io.gihub.pcp1976.sassenrath.base;

import java.util.List;

import io.github.pcp1976.sassenrath.api.extend.Plugin;
public class Controler {
	public List<Plugin> pluginGraph;
	
	public void linkServer(Plugin plugin, Plugin server){
		plugin.addServer(server);
	}
	public void linkClient(Plugin plugin, Plugin client){
		plugin.addClient(client);
	}
}
