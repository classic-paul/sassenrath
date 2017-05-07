package io.github.pcp1976.sassenrath.api.extend;

import java.util.List;

public interface Source extends Plugin{
	public List<Sink> getIsSourceFor();
	public void setIsSourceFor(List<Sink> isSourceFor);
	public void addSink(Sink sink);
	public boolean isInSinks(Sink sink);
}
