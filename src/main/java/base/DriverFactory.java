package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.WaitUtils;
public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	static WaitUtils wait;
	public static void initDriver() {
		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		driver.set(webDriver);
		wait = new WaitUtils(webDriver);
	}
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}