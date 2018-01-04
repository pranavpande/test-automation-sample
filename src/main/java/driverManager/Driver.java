package driverManager;

import org.openqa.selenium.WebDriver;

public abstract class Driver {
	
	private static ThreadLocal<WebDriver> masterDriver = new ThreadLocal<WebDriver>();
	protected abstract void createDriver();
	
	protected WebDriver getDriver() {
		if(masterDriver.get() == null)
			createDriver();
		
		return masterDriver.get();
	}
	
	protected void setDriver(WebDriver driver) {
		masterDriver.set(driver);
	}
	
	protected void quitDriver() {
		if(masterDriver.get() != null) {
			masterDriver.get().quit();
			masterDriver.remove();
		}
	}
	
}
