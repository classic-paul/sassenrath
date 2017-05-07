package io.github.pcp1976.sassenrath.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.github.pcp1976.sassenrath.api.extend.*;
public class Controler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(Controler.class);

	public List<Plugin> pluginGraph;
	
	public void addSource(Sink sink, Source source){
		if (logger.isDebugEnabled()) {
			logger.debug("addSource(Sink, Source) - start"); //$NON-NLS-1$
		}

		sink.addSource(source);

		if (logger.isDebugEnabled()) {
			logger.debug("addSource(Sink, Source) - end"); //$NON-NLS-1$
		}
	}
	public void addSink(Source source, Sink sink){
		if (logger.isDebugEnabled()) {
			logger.debug("addSink(Source, Sink) - start"); //$NON-NLS-1$
		}

		source.addSink(sink);

		if (logger.isDebugEnabled()) {
			logger.debug("addSink(Source, Sink) - end"); //$NON-NLS-1$
		}
	}
}
