package commonComponents;

import org.testng.annotations.BeforeClass;

import supportLibraries.PageNavigation;

import java.io.FileNotFoundException;

import org.testng.annotations.AfterClass;

public class BaseTest {

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
		PageNavigation navigation = new PageNavigation();
		navigation.login();
	}

	@AfterClass
	public void afterClass() throws FileNotFoundException {
		PageNavigation navigation = new PageNavigation();
		navigation.logout();
	}

}
