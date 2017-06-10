package io.github.pcp1976.sassenrath.core;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.pcp1976.sassenrath.api.Controller;
import io.github.pcp1976.sassenrath.api.extend.*;

import java.util.List;

@Component
public class ControllerImpl implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ControllerImpl.class);
	private List<Plugin> pluginGraph;

	private boolean addSource(Plugin sink, Plugin source) {
		log("addSource(Sink, Source) - start");
		if (!this.checkForCycles(source, sink)) {
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			log("addSource(Plugin, Plugin) - end; source added to sink");
			return so.addSink(si) && si.addSource(so);
		}

		log("addSource(Plugin, Plugin) - end; source not added to sink");
		return false;
	}

	private boolean addSink(Plugin source, Plugin sink) {
		log("addSink(Source, Sink) - start");
		if (!this.checkForCycles(source, sink)) {
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			log("addSink(Plugin, Plugin) - end; sink added to source");
			return so.addSink(si) && si.addSource(so);
		}

		log("addSink(Source, Sink) - end; sink not added to source");

		return false;
	}

	private boolean checkForCycles(Plugin source, Plugin sink) {
		if (sink instanceof Pipe && source instanceof Pipe) {
			Pipe so = (Pipe) source;
			Pipe si = (Pipe) sink;
			return so.cycleCheck(si);
		}
		return false;
	}

	@Override
	public boolean linkSourceAndSink(Plugin source, Plugin sink) {
		return this.addSink(source, sink) && this.addSource(sink, source);
	}

	private void log(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	@Override
	public void beginWork() {
		if (logger.isErrorEnabled()) {
			logger.debug("beginWork() called: this method is not yet implemented");
		}
	}

	@Override
	public boolean addPlugin(Plugin plugin) {
		return this.getPluginGraph().add(plugin);
	}

	public List<Plugin> getPluginGraph() {
		return pluginGraph;
	}

	public void setPluginGraph(List<Plugin> pluginGraph) {
		this.pluginGraph = pluginGraph;
	}
}
