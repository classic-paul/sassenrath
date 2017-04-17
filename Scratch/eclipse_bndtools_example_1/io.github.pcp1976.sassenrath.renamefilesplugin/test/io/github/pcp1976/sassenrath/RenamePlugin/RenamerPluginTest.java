package io.github.pcp1976.sassenrath.RenamePlugin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.github.pcp1976.sassenrath.renamerplugin.RenamerPlugin;

public class RenamerPluginTest {
	
	RenamerPlugin plugin;

    @BeforeClass
    public static void runOnceBeforeClass() {
        //System.out.println("@BeforeClass - runOnceBeforeClass");
        //System.out.println("@BeforeClass empty - moving on");
    }

    @AfterClass
    public static void runOnceAfterClass() {
        //System.out.println("@AfterClass - runOnceAfterClass");
        //System.out.println("@AfterClass empty - moving on");
    }

    @Before
    public void runBeforeTestMethod() {
        //System.out.println("@Before - runBeforeTestMethod");
        //System.out.println("@Before - creating new RenamerPlugin plugin");  
        plugin = new RenamerPlugin();
    }

    @After
    public void runAfterTestMethod() {
        //System.out.println("@After - runAfterTestMethod");
        //System.out.println("@After - nulling plugin");
        plugin = null;
    }

    @Test
    public void test_getName() {
    	assertTrue("getName() returns an incorrect string", plugin.getName().equals("File renamer"));
    }

}
