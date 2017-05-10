package io.github.pcp1976.sassenrath.api.extend;

public interface Pipe extends Sink, Source{
	/**
	 * This method is designed to check that a Pipe we are adding as a Sink to a Pipe does not exist in amongst this Pipe's Sources
	 * @param plugin reference to a Plugin we expect will not exist amongst this Plugin's sources
	 * @return true if the check for cycles in the graph is passed (the Plugin referenced passed is not amongst this plugin's sources)
	 */
	public boolean cycleCheck(Pipe plugin);
}
