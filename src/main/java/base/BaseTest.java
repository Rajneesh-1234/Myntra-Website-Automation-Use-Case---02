package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import config.ConfigReader;
@Listeners(reports.ExtentManager.class)
public class BaseTest {
	protected WebDriver driver;
	@BeforeSuite
	public void setup() throws Exception {
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
		DriverFactory.getDriver().get(ConfigReader.get("url"));
	}

	@AfterSuite
	public void tearDown() throws Exception {
		DriverFactory.quitDriver();
	}
}