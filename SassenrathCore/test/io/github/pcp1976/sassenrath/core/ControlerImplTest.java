package io.github.pcp1976.sassenrath.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.github.pcp1976.sassenrath.api.Controller;
import io.github.pcp1976.sassenrath.api.extend.*;
import io.github.pcp1976.sassenrath.pluginempty.PluginEmpty;

public class ControlerImplTest {
	static Controller controller;
	static Plugin p1;
	static Plugin p2;
	static Plugin p3;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
		controller = new ControllerImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		controller = null;
	}

	@Before
	public void setUp() throws Exception {
		PluginEmpty p = new PluginEmpty();
		p1 = p.buildPlugin();
		p2 = p.buildPlugin();
		p3 = p.buildPlugin();
		assertTrue(p1 instanceof Pipe && p2 instanceof Pipe && p3 instanceof Pipe);
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
		p2 = null;
		p3 = null;
	}

	@Test
	public void testAddCycle(){
		// here we want to check that we can't add cyclic dependencies
		assertTrue(controller.linkSourceAndSink(p1, p2)); // this should be fine
		assertTrue(controller.linkSourceAndSink(p2, p3)); // this should also be fine
		assertFalse(controller.linkSourceAndSink(p3, p1)); // here there is a cycle - not fine
	} 

}
