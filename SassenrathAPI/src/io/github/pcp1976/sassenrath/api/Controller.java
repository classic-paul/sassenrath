package io.github.pcp1976.sassenrath.api;

import io.github.pcp1976.sassenrath.api.extend.Plugin;

public interface Controller {
	public boolean linkSourceAndSink(Plugin source, Plugin sink);
	public void beginWork();
	public boolean addPlugin(Plugin plugin);
}
