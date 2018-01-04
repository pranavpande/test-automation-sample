package bugTests;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonComponents.BaseTest;
import driverManager.DriverManager;

import objectRepository.AddEditUsers;
import objectRepository.AddUser;

public class TestUsernameLimit extends BaseTest{
	
	AddEditUsers addEditUsers = new AddEditUsers();
	AddUser addUser = new AddUser();
	
	@Test(priority=0)
	public void testMaximumUsernameLength() throws FileNotFoundException, InterruptedException {
		addEditUsers.goTo();
		addEditUsers.newButton.click();
		addUser.username.fill("abcdefghijklmnopqrstuvwxyz12345678910123123456789010");
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("1formPassword!");
		addUser.initialPasswordConfirm.fill("1formPassword!");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Username must be between 4-50 characters in length");
	}
	
	@Test(priority=1)
	public void testMinimumUsernameLength() {
		addUser.username.fill("1");
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("1formPassword!");
		addUser.initialPasswordConfirm.fill("1formPassword!");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Username must be between 4-50 characters in length");
	}

}
