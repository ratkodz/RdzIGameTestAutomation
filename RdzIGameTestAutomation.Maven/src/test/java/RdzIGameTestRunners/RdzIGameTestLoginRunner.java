package RdzIGameTestRunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "TestFeatures", 
		glue = {"RdzIGameTestSteps"},
		plugin = {"html:target/Reports/TestLoginReport", "pretty"}
)
public class RdzIGameTestLoginRunner  {

}
