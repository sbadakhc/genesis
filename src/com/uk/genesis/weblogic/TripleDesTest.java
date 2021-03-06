package com.uk.genesis.weblogic;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FilterSet;

import com.uk.genesis.weblogic.TripleDes;

import junit.framework.TestCase;

public class TripleDesTest extends TestCase {

        public void setUp() {

        }

	public void testValidatesFilternameSet() {
		TripleDes task = new TripleDes();
		task.setProject(new Project());
		task.setEncryptionservice("./etc/testdata/encservice");
		
		try {
			task.execute();
			fail("Should have checked filtername was set");
		} catch (BuildException ex) {
			assertTrue(ex.getMessage().contains("filtername"));
		}
	}

	public void testValidatesEncryptionServiceSet() {
		TripleDes task = new TripleDes();
		task.setProject(new Project());
		task.setFiltername("test.filter");
		
		try {
			task.execute();
			fail("Should have checked encryptionservice dir was set");
		} catch (BuildException ex) {
			assertTrue(ex.getMessage().contains("encryptionservice"));
		}
	}
	
	public void testHandlesInvalidEncServiceDirectory() {
		TripleDes task = new TripleDes();
		task.setProject(new Project());
		task.setFiltername("test.filter");
		task.setEncryptionservice("./etc/testdata/encservice-invalid");
		
		try {
			task.execute();
			fail("Should have checked encservice directory exists");
		} catch (BuildException ex) {
			assertTrue(ex.getMessage().contains("encryptionservice") && ex.getMessage().contains("exist"));
		}
	}
	
	public void testHandlesNoPatternsSpecified() {
		Project project = new Project();
		project.addReference("test.filter", new FilterSet());
		
		TripleDes task = new TripleDes();
		task.setProject(project);
		task.setFiltername("test.filter");
		task.setEncryptionservice("./etc/testdata/encservice");
		task.execute();	
	}
	
}
