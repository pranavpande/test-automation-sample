package driverManager;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<Driver> driverManager = new ThreadLocal<Driver>();

	private static synchronized Driver getDriverManager(String browser) {
		if(driverManager.get() == null) {
			if(browser == null) throw new NullPointerException();
			Driver manager = DriverFactory.getDriver(browser);
			driverManager.set(manager);
		}
		return driverManager.get();
	}
	
	private static synchronized void removeDriverManager() {
		driverManager.remove();
	}
	
	public static synchronized WebDriver getDriver(String browser) {
		return getDriverManager(browser).getDriver();
	}
	
	public static synchronized void quitDriver() {
		getDriverManager(null).quitDriver();
		removeDriverManager();
	}
}
