package io.github.pcp1976.sassenrath.pluginempty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Component;
import io.github.pcp1976.sassenrath.api.extend.*;
import io.github.pcp1976.sassenrath.util.ConcretePluginFactory;
import io.github.pcp1976.sassenrath.util.PluginHost;

@Component
public class PluginEmpty extends PluginHost implements Factory {
	private static final Logger logger = LoggerFactory.getLogger(PluginEmpty.class);
	private final String name = "Empty"; 

	@Override
	public Plugin buildPlugin() {
		logger.debug("buildPlugin() - start");
		Plugin p = null;
		logger.info("buildPlugin() - Plugin p={}", p);
		try {
			p = ConcretePluginFactory.createFactory(BehaviourEmpty.class, this.getName()).create();
			logger.info("buildPlugin() - Plugin p={}", p);	
		} catch (Exception e) {
			logger.error("buildPlugin() - exception ignored", e);
		}
		logger.info("buildPlugin() - Plugin p={}", p);
		logger.debug("buildPlugin() - end");
		return p;
	}
	
	public void updateJobs(){
		logger.debug("updateJobs() - start");
		logger.warn("updateJobs() - {} has no jobs", getName());
		logger.debug("updateJobs() - end");	
	}
	
	@Override
	public String getName(){
		logger.debug("getName() - start");
		logger.info("getName() - name={}", this.name);		
		logger.debug("getName() - end");
		return this.name;
	}
}
