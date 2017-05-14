package io.github.pcp1976.sassenrath.api.core;

import io.github.pcp1976.sassenrath.api.extend.Plugin;

public interface Controller {
	public boolean addSource(Plugin sink, Plugin source);
	public boolean addSink(Plugin source, Plugin sink);
	public boolean linkSourceAndSink(Plugin source, Plugin sink);
}
