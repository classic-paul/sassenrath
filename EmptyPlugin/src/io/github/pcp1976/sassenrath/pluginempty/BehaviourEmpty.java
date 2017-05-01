package io.github.pcp1976.sassenrath.pluginempty;

import io.github.pcp1976.sassenrath.api.extend.Job;
import io.github.pcp1976.sassenrath.api.extend.PluginBehaviour;

public class BehaviourEmpty implements PluginBehaviour{

	GUIEmpty myGUI;
	@Override
	public void createCommandLine(Job job) {
		/*
		 * here we would insert the generated command line
		 * - except the empty plugin has no effect!
		 */
		job.setCommandLine("echo 'Empty job!'");
	}

	@Override
	public void createOutputFilePath(Job job) {
		// TODO Auto-generated method stub
		/*
		 * this is an "empty" job, it does nothing - the output is no different to the input!
		 */
		job.setOutputFilePath(job.getInputFilePath());
	}

	@Override
	public void configure() {
		myGUI.main();
	}

}
