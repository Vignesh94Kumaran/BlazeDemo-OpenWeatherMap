package cucumber.Options;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/blazeDemoUIAutomation/features","src/test/java/openWeatherMapApiAutomation/features"},
glue = {"blazeDemoUIAutomation/hooks","blazeDemoUIAutomation/stepDefinitions","openWeatherMapApiAutomation.stepDefinitions"}, 
tags= "@Automated",
plugin="json:target/jsonReports/CucumberReport.json")
public class TestRunner {

}
