package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="featurefiles",
				glue="stepDefination",
				plugin= {"de.monochromata.cucumber.report.PrettyReports:target/cucumber"})
public class CucumberRunner extends AbstractTestNGCucumberTests {

	
	
}
