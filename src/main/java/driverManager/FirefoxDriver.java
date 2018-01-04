package driverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriver extends Driver {

	@Override
	protected void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setPlatform(Platform.LINUX);
		try {
			setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
