package bugTests;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonComponents.BaseTest;
import commonComponents.RandomStringGenerator;
import driverManager.DriverManager;
import objectRepository.AddEditUsers;
import objectRepository.AddUser;
import supportLibraries.PageNavigation;

public class TestPasswordStrength extends BaseTest{
	
	String username = RandomStringGenerator.generateRandomString(10);
	AddEditUsers addEditUsers = new AddEditUsers();
	AddUser addUser = new AddUser();
	
	@Test(priority=0)
	public void testValidateLength() throws FileNotFoundException, InterruptedException {
		
		addEditUsers.goTo();
		addEditUsers.newButton.click();
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("123123");
		addUser.initialPasswordConfirm.fill("123123");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Password must be at least 8 characters in length");
	}
	
	@Test(priority=1)
	public void testValidateLowercaseLetter() throws FileNotFoundException {
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("123123123");
		addUser.initialPasswordConfirm.fill("123123123");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Password must contain at least one lowercase letter");
	}
	
	@Test(priority=2)
	public void testValidateNumber() throws FileNotFoundException {
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("AbcDef!!");
		addUser.initialPasswordConfirm.fill("AbcDef!!");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Password must contain at least one number");
	}
	
	@Test(priority=3)
	public void testValidateSpecialCharacters() throws FileNotFoundException {
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("123123eE");
		addUser.initialPasswordConfirm.fill("123123eE");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Password must contain one of the following special characters !@#$%^&*()-_=+~`,.");
	}
	
	@Test(priority=4)
	public void testValidateMatch() throws FileNotFoundException {
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("123123eE");
		addUser.initialPasswordConfirm.fill("123123eE!");
		addUser.createUser.click();
		String errorMessage = addUser.getStatus();
		Assert.assertEquals(errorMessage, "ERROR: Password and confirmation do not match.");
	}
	
	@Test(priority=5)
	public void testCreateNewUser() throws FileNotFoundException {
		addUser.username.fill(username.concat("test"));
		addUser.email.fill("usertest@test.com");
		addUser.initialPassword.fill("123123tT!");
		addUser.initialPasswordConfirm.fill("123123tT!");
		addUser.createUser.click();
		String creationStatus=DriverManager.getDriver(null).getCurrentUrl();
		Assert.assertTrue(creationStatus.contains("/adminUsers.php"));
	}
	
	@Test(priority=6)
	public void testDeleteUser() throws InterruptedException, FileNotFoundException {
		PageNavigation navigation = new PageNavigation();
		
		addEditUsers.search(username.concat("test"));
		addEditUsers.selectUser(username.concat("test"));
		addEditUsers.deleteButton.click();
		navigation.loadPage("true");
		addEditUsers.search(username.concat("test"));
		String pageStatus = addEditUsers.pageStatus.getText();
		Assert.assertEquals(pageStatus, "No items");
		
	}
}
