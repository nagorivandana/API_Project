package com.cfs.myrunner;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This class is used to configure feature, step definitions and act as an entry point for the automation suite
 * @author
 *
 */
@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "src/test/java/com/cfs/features/cfsapi.feature",
			glue={"com.cfs.stepdefinitions"},
			plugin = {"pretty","html:test-output.html"}, monochrome = true,
			dryRun = false		
			)
	 
	public class TestRunner {
	 
	}
	
