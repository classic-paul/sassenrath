package io.github.pcp1976.sassenrath.pluginempty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.pcp1976.sassenrath.api.extend.Job;
import io.github.pcp1976.sassenrath.api.extend.Behaviour;

public class BehaviourEmpty implements Behaviour{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BehaviourEmpty.class);

	GUIEmpty myGUI;
	@Override
	public void createCommandLine(Job job) {
		if (logger.isDebugEnabled()) {
			logger.debug("createCommandLine(Job) - start"); //$NON-NLS-1$
		}

		/*
		 * here we would insert the generated command line
		 * - except the empty plugin has no effect!
		 */
		job.setCommandLine("echo 'Empty job!'");

		if (logger.isDebugEnabled()) {
			logger.debug("createCommandLine(Job) - end"); //$NON-NLS-1$
		}
	}

	@Override
	public void createOutputFilePath(Job job) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOutputFilePath(Job) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		/*
		 * this is an "empty" job, it does nothing - the output is no different to the input!
		 */
		job.setOutputFilePath(job.getInputFilePath());

		if (logger.isDebugEnabled()) {
			logger.debug("createOutputFilePath(Job) - end"); //$NON-NLS-1$
		}
	}

	@Override
	public void configure() {
		if (logger.isDebugEnabled()) {
			logger.debug("configure() - start"); //$NON-NLS-1$
		}

		myGUI.main();

		if (logger.isDebugEnabled()) {
			logger.debug("configure() - end"); //$NON-NLS-1$
		}
	}

}
