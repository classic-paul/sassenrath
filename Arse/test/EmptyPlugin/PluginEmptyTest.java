package EmptyPlugin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.github.pcp1976.sassenrath.api.extend.Plugin;
import io.github.pcp1976.sassenrath.api.extend.PluginFactory;
import io.github.pcp1976.sassenrath.pluginempty.PluginEmpty;

public class PluginEmptyTest {

	static PluginFactory emptyPlugin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emptyPlugin = new PluginEmpty();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emptyPlugin = null;

	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildPlugin() {
		Plugin newP = emptyPlugin.buildPlugin();
		assertTrue("newP not a Plugin", newP instanceof Plugin);
		assertTrue("newP not a PluginEmpty", newP.getName() == "Empty");
	}
	
// TODO: Empty plugin tests - how to handle EmptyPlugin not using jobs/queues/clients/servers
/*
	@Test
	public void testGetJobList() {

		fail("Not yet implemented");
	}

	@Test
	public void testSetJobList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsServerFor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIsServerFor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsClientOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIsClientOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInClients() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInServers() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateJobs() {
		fail("Not yet implemented");
	}
*/
}
