package io.github.pcp1976.sassenrath.core;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.github.pcp1976.sassenrath.api.core.Controller;
import io.github.pcp1976.sassenrath.api.extend.*;

@Component
public class ControlerImpl implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ControlerImpl.class);
	public List<Plugin> pluginGraph;

	@Override
	public boolean addSource(Plugin sink, Plugin source) {
		log("addSource(Sink, Source) - start");
		if(!this.checkForCycles(source, sink)){
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			log("addSource(Plugin, Plugin) - end; source added to sink");
			return si.addSource(so);
		}

		log("addSource(Plugin, Plugin) - end; source not added to sink");
		return false;
	}

	@Override
	public boolean addSink(Plugin source, Plugin sink) {
		log("addSink(Source, Sink) - start");
		if(!this.checkForCycles(source, sink)){
			return false;
		}
		if (source instanceof Source && sink instanceof Sink) {
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			log("addSink(Plugin, Plugin) - end; sink added to source");
			return so.addSink(si);
		}

		log("addSink(Source, Sink) - end; sink not added to source");

		return false;
	}
	
	private boolean checkForCycles(Plugin source, Plugin sink){
		if (sink instanceof Pipe && source instanceof Pipe){
			Pipe so = (Pipe) source;
			Pipe si = (Pipe) sink;
			return so.cycleCheck(si);
		}
		return false;
	}

	@Override
	public boolean linkSourceAndSink(Plugin source, Plugin sink){
		return this.addSink(source, sink) && this.addSource(sink, source);
	}
	
	private void log(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
}
