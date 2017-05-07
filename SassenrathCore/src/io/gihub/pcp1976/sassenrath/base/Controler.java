package io.gihub.pcp1976.sassenrath.base;

import java.util.List;

import io.github.pcp1976.sassenrath.api.extend.*;
public class Controler {
	public List<Plugin> pluginGraph;
	
	public void linkSource(Sink sink, Source source){
		sink.addSource(source);
	}
	public void linkSink(Source source, Sink sink){
		source.addSink(sink);
	}
}
