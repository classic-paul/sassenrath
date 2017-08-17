package io.github.pcp1976.sassenrath.core;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.pcp1976.sassenrath.api.Controller;
import io.github.pcp1976.sassenrath.api.extend.*;
import io.github.pcp1976.sassenrath.pluginempty.PluginEmpty;

import java.util.List;

@Component
public class ControllerImpl implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ControllerImpl.class);
	private List<Plugin> pluginGraph;
	private PluginFactory defaultPluginFactory;
	
	public ControllerImpl(){
			this(new PluginEmpty());
	}
	
	public ControllerImpl(PluginFactory defaultPlugin){
		this.setDefaultPlugin(defaultPlugin);
	}	
	
	@Override
	public void setDefaultPlugin(PluginFactory pluginFactory) {
		this.defaultPluginFactory = pluginFactory;
		
	}

	//TODO refactor addSource() and addSink()
	private boolean addSource(Plugin sink, Plugin source) {
		logger.debug("addSource(Plugin, Plugin) - start");
		logger.info("addSource(Plugin, Plugin) - Plugin source={}, Plugin sink={}", source, sink);
		boolean cycleCheck = !this.checkForCycles(source, sink);
		logger.info("addSource(Plugin, Plugin) - cycleCheck={}", cycleCheck);
		if (cycleCheck) {
			logger.debug("addSource(Plugin, Plugin) found a cycle - end");
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			Source so = (Source) source;
			logger.info("addSource(Plugin, Plugin) - Source so={}", so);
			Sink si = (Sink) sink;
			logger.info("addSource(Plugin, Plugin) - Sink si={}", si);
			boolean returnboolean = so.addSink(si) && si.addSource(so);
			logger.info("addSource(Plugin, Plugin) - returnboolean={}", returnboolean);
			logger.debug("addSource(Plugin, Plugin) - end");
			return returnboolean;
		}
		//output to log to decipher why the if block returned false
		logger.info("addSource(Plugin, Plugin) - sink instanceof Pipe={}", sink instanceof Pipe);
		logger.info("addSource(Plugin, Plugin) - source instanceof Pipe={}", source instanceof Pipe);
		logger.debug("addSource(Plugin, Plugin) - end");
		return false;
	}

	private boolean addSink(Plugin source, Plugin sink) {
		logger.debug("addSink(Plugin, Plugin) - start");
		logger.info("addSink(Plugin, Plugin) - Plugin source={}, Plugin sink={}", source, sink);		
		boolean cycleCheck = !this.checkForCycles(source, sink);
		logger.info("addSink(Plugin, Plugin) - cycleCheck={}", cycleCheck);
		if (cycleCheck) {
			logger.debug("addSource(Plugin, Plugin) found a cycle - end");
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			Source so = (Source) source;
			logger.info("addSink(Plugin, Plugin) - Source so={}", so);
			Sink si = (Sink) sink;
			logger.info("addSink(Plugin, Plugin) - Sink si={}", si);
			boolean returnboolean = so.addSink(si) && si.addSource(so);
			logger.info("addSink(Plugin, Plugin) - returnboolean={}", returnboolean);
			logger.debug("addSink(Plugin, Plugin) - end");
			return returnboolean;
		}
		//output to log to decipher why the if block returned false
		logger.info("addSource(Plugin, Plugin) - sink instanceof Pipe={}", sink instanceof Pipe);
		logger.info("addSource(Plugin, Plugin) - source instanceof Pipe={}", source instanceof Pipe);		
		logger.debug("addSink(Plugin, Plugin) - end");
		return false;
	}

	private boolean checkForCycles(Plugin source, Plugin sink) {
		logger.debug("checkForCycles(Plugin, Plugin) - start");
		if (sink instanceof Pipe && source instanceof Pipe) {
			Pipe so = (Pipe) source;
			logger.info("checkForCycles(Plugin, Plugin) - Pipe so={}", so);
			Pipe si = (Pipe) sink;
			logger.info("checkForCycles(Plugin, Plugin) - Pipe si={}", si);
			boolean returnboolean = so.cycleCheck(si);
			logger.info("checkForCycles(Plugin, Plugin) - returnboolean={}", returnboolean);
			logger.debug("checkForCycles(Plugin, Plugin) - end");
			return returnboolean;
		}
		logger.debug("checkForCycles(Plugin, Plugin) - end");
		return false;
	}

	@Override
	public boolean linkSourceAndSink(Plugin source, Plugin sink) {
		logger.debug("linkSourceAndSink(Plugin, Plugin) - start");
		logger.info("linkSourceAndSink(Plugin, Plugin) - Plugin source={}, Plugin sink={}", source, sink);
		boolean returnboolean = this.addSink(source, sink) && this.addSource(sink, source);
		logger.info("linkSourceAndSink(Plugin, Plugin) - returnboolean={}", returnboolean);
		logger.debug("linkSourceAndSink(Plugin, Plugin) - end");
		return returnboolean;
	}

	@Override
	public boolean addPlugin(Plugin plugin) {
		logger.debug("addPlugin(Plugin) - start");
		logger.info("addPlugin(Plugin) - Plugin plugin={}", plugin);
		boolean returnboolean = this.getPluginGraph().add(plugin);
		logger.info("addPlugin(Plugin) - returnboolean={}", returnboolean);
		logger.debug("addPlugin(Plugin) - end");
		return returnboolean;
	}

	@Override
	public boolean removePlugin(Plugin plugin) {
		logger.debug("removePlugin(Plugin) - start");
		logger.info("removePlugin(Plugin) - Plugin plugin={}", plugin);
		boolean returnboolean = this.getPluginGraph().remove(plugin);
		logger.info("removePlugin(Plugin) - returnboolean={}", returnboolean);
		logger.debug("removePlugin(Plugin) - end");
		return returnboolean;
	}

	@Override
	public List<Plugin> getPluginGraph() {
		logger.debug("getPluginGraph() - start");
		logger.info("getPluginGraph() - List<Plugin> pluginGraph={}", pluginGraph);
		logger.debug("getPluginGraph() - end");
		return pluginGraph;
	}
	
	@Override
	public void setPluginGraph(List<Plugin> pluginGraph) {
		logger.debug("setPluginGraph(List<Plugin>) - start");
		this.pluginGraph = pluginGraph;
		logger.debug("setPluginGraph(List<Plugin>) - end");
	}

	@Override
	public void beginWork() {
		logger.error("beginWork() called: this method is not yet implemented");
	}

	@Override
	public void configurePlugin(Plugin plugin) {
		logger.error("configurePlugin(Plugin plugin) called: this method is not yet implemented");
	}

	@Override
	public Plugin getNewPlugin() {
		logger.debug("getNewPlugin() - start");
		Plugin n = defaultPluginFactory.buildPlugin();
		logger.debug("getNewPlugin() - returning {}", n);	
		logger.debug("getNewPlugin() - end");
		return n;
	}

	public Plugin replacePlugin(Swappable oldPlugin, PluginFactory newPluginFactory){
		return oldPlugin.swap(newPluginFactory);
	}
}
