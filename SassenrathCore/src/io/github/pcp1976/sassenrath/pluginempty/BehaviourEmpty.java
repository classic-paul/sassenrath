package io.github.pcp1976.sassenrath.pluginempty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.pcp1976.sassenrath.api.extend.Job;
import io.github.pcp1976.sassenrath.api.extend.Behaviour;

public class BehaviourEmpty implements Behaviour {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BehaviourEmpty.class);

	GUIEmpty myGUI;

	@Override
	public void createCommandLine(Job job) {
		logger.debug("createCommandLine(Job) - start");

		/*
		 * here we would insert the generated command line - except the empty
		 * plugin has no effect!
		 */
		job.setCommandLine("echo 'Empty job!'");

		logger.debug("createCommandLine(Job) - end");
	}

	@Override
	public void createOutputFilePath(Job job) {
		logger.debug("createOutputFilePath(Job) - start");

		/*
		 * this is an "empty" job, it does nothing - the output is no different
		 * to the input!
		 */
		job.setOutputFilePath(job.getInputFilePath());

		logger.debug("createOutputFilePath(Job) - end");
	}

	@Override
	public void configure() {
		logger.debug("configure() - start");

		myGUI.main();

		logger.debug("configure() - end");
	}

}
