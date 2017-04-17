package io.github.pcp1976.sassenrath.api;

import java.io.File;

/*
 *  Represents a plugin component consumed by the sassenrath core application.
 */
public interface Plugin {
	/*
	 * Represents the human-readable name of the plugin
	 * @return	A string representing the name of the plugin.
	 */
	public String getName();
	
	/*
	 * Configure the plugin
	 * Typically this would involve the plugin displaying a configuration UI to the user
	 */
	public void configure();
	
	/*
	 * Add a file to the plugin's input queue.
	 * 
	 * @param	file	The input file to process
	 * @param	plugin	Represents the originator of the file
	 */
	public void enqueue(File file, Plugin plugin);
	
	/*
	 * Commence producing output
	 */
	public void start();
	
	/*
	 * Stop producing output
	 */
	public void stop();
	
	/*
	 * Empty queues
	 */
	public void clear();
	
	/*
	 * Clear all configuration and empty queues, do not unlink registered clients
	 */
	public void reset();
	
	/*
	 * Query a plugin's state
	 * @return	The plugin's current state
	 */
	public PluginState getState();
	
	/*
	 * Register a plugin as a client interested in this plugin's output
	 */
	public void registerClient(Plugin plugin);
}
