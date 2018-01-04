package objectRepository;

import java.io.FileNotFoundException;

import commonComponents.Button;
import commonComponents.Label;
import supportLibraries.PageNavigation;

public class MyProfile{
	
	public Label username = new Label("xpath", "//div[contains(text(), 'Username')]/../../td[2]/div");
	public Label firstName = new Label("xpath", "//div[contains(text(), 'First Name')]/../../td[2]/div");
	public Label lastName = new Label("xpath", "//div[contains(text(), 'Last Name')]/../../td[2]/div");
	public Label email = new Label("xpath", "//div[contains(text(), 'Email')]/../../td[2]/div");
	public Label formRights = new Label("xpath", "//div[contains(text(), 'Right to create forms')]/../../td[2]/div/img");
	public Label adminRights = new Label("xpath", "//div[contains(text(), 'Admin')]/../../td[2]/div/img");

	public Button editProfileButton = new Button("linktext", "Edit Profile");
	
	public void goTo() throws FileNotFoundException {
		PageNavigation navigation = new PageNavigation();
		navigation.goTo("My Profile");
	}

}