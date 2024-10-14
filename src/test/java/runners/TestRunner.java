package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@Test
@CucumberOptions(
    features = "src/test/resources/features/login.feature",
    glue = "stepdefinitions",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
