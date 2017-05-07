package io.github.pcp1976.sassenrath.pluginempty;


import org.osgi.service.component.annotations.Component;

import io.github.pcp1976.sassenrath.api.extend.*;
import io.github.pcp1976.sassenrath.util.*;
@Component
public class PluginEmpty extends PluginHost implements Factory {
	private final String name = "Empty"; 

	@Override
	public Plugin buildPlugin() {
		Plugin p = null;
		try {
			p = ConcretePluginFactory.createFactory(BehaviourEmpty.class, this.getName()).create();
		} catch (Exception e) {
			//TODO Do something meaningful with the exception
		}
		return p;
	}
	
	public void updateJobs(){
		throw new UnsupportedOperationException("Empty plugin has no jobs");
	}
	
	@Override
	public String getName(){
		return this.name;
	}
}
