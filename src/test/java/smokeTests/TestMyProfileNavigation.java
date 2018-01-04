package smokeTests;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonComponents.BaseTest;
import objectRepository.EditProfile;
import objectRepository.MyProfile;

public class TestMyProfileNavigation extends BaseTest {
	MyProfile myProfile = new MyProfile();
	EditProfile editProfile = new EditProfile();
	
	@Test(priority=0)
	public void testEditProfileButton() throws FileNotFoundException {
		myProfile.goTo();
		myProfile.editProfileButton.click();
		editProfile.firstName.fill("Zhuoyi");
		String firstName = editProfile.firstName.getText();
		Assert.assertEquals(firstName, "Zhuoyi");
		editProfile.save.click();
	}
	
}
