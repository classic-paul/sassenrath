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

	public boolean addSource(Plugin sink, Plugin source) {
		log("addSource(Sink, Source) - start");
		if (source instanceof Source && sink instanceof Sink) {
			//we should perform a check for cycles here
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			si.addSource(so);
			log("addSource(Plugin, Plugin) - end; source added to sink");
			return true;
		}

		log("addSource(Plugin, Plugin) - end; source not added to sink");
		return false;
	}

	public boolean addSink(Plugin source, Plugin sink) {
		log("addSink(Source, Sink) - start");
		if (source instanceof Source && sink instanceof Sink) {
			// need a cycle check
			log("addSink(Source, Sink) - argument type guard passed");
			Source so = (Source) source;
			Sink si = (Sink) sink;
			so.addSink(si);
			log("addSink(Plugin, Plugin) - end; sink added to source");
			return true;
		}

		log("addSink(Source, Sink) - end; sink not added to source");

		return false;
	}
	
	
/*	
	public boolean linkSourceAndSink(Plugin source, Plugin sink){
		if (source instanceof Source && sink instanceof Sink) {
			
		}
	}
*/
	private void log(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
}
