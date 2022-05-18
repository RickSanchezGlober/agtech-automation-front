package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "classpath:features",
    glue = {"classpath:steps"},
    tags = "@regression")
public class TestRunner extends AbstractTestNGCucumberTests {}
