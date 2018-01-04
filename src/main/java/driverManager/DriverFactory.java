package driverManager;

public class DriverFactory {
	
	protected static Driver getDriver(String browserType) {
		
		Driver driver = null;
		browserType.toLowerCase();
		
		switch (browserType) {
			case "chrome":
				driver = new ChromeDriver();
			break;
			
			case "firefox":
				driver = new FirefoxDriver();
			break;
			
			default:
				throw new IllegalArgumentException("Browser type not defined");
		}
		return driver;
	}
}
