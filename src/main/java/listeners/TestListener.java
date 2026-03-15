package listeners;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	public void onStart(ITestContext context) {
	  System.out.println("Test Execution started..");
	  }
	public void onTestStart(ITestResult result) {
		 System.out.println("Test started..");
	  
	  }
	public void onTestSuccess(ITestResult result) {
		 System.out.println("Test passed..");
	  }

	public void onTestSkipped(ITestResult result) {
		 System.out.println("Test skipped..");
	  
	  } 
	  public void onFinish(ITestContext context) {
		  System.out.println("Test Execution finished..");
	  
	  }
	  
	  @Override
	    public void onTestFailure(ITestResult result) {

	        Throwable exception = result.getThrowable();
	        Object testClass = result.getInstance();

	        WebDriver driver = null;

	        try {
	            driver = (WebDriver) testClass
	                    .getClass()
	                    .getDeclaredField("driver")
	                    .get(testClass);
	        } catch (Exception e) {
	            System.out.println("⚠ Unable to get WebDriver instance");
	        }

	        if (exception instanceof TimeoutException) {
	            System.out.println("❌ TimeoutException occurred");
	        }
	        else if (exception instanceof NoSuchElementException) {
	            System.out.println("❌ NoSuchElementException occurred");
	        }
	        else if (exception instanceof StaleElementReferenceException) {
	            System.out.println("❌ StaleElementReferenceException occurred");
	        }
	        else if (exception instanceof ElementNotInteractableException) {
	            System.out.println("❌ ElementNotInteractableException occurred");
	        }
	        else if (exception instanceof ElementClickInterceptedException) {
	            System.out.println("❌ ElementClickInterceptedException occurred");
	        }
	        else if (exception instanceof InvalidSelectorException) {
	            System.out.println("❌ InvalidSelectorException occurred");
	        }
	        else if (exception instanceof JavascriptException) {
	            System.out.println("❌ JavascriptException occurred");
	        }
	        else if (exception instanceof SessionNotCreatedException) {
	            System.out.println("❌ SessionNotCreatedException occurred");
	        }
	        else if (exception instanceof WebDriverException) {
	            System.out.println("❌ WebDriverException occurred");
	        }
	        else {
	            System.out.println("❌ Unknown Exception: " + exception.getMessage());
	        }
	    }
}
