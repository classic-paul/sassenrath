package io.github.pcp1976.sassenrath.api.extend;

import java.util.List;

public interface Sink extends Plugin {
	public List<Source> getIsSinkOf();
	public void setIsSinkOf(List<Source> isSourceOf);
	public void addSource(Source source);
	public boolean isInSources(Source source);
}
