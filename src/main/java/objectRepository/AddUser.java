package objectRepository;

import org.openqa.selenium.By;

import commonComponents.Button;
import commonComponents.CheckBox;
import commonComponents.TextBox;
import driverManager.DriverManager;

public class AddUser {

	public String status; 
	public TextBox username = new TextBox("Name", "newUsername");
	public TextBox firstName = new TextBox("Name", "newFirstname");
	public TextBox lastName = new TextBox("Name", "newLastname");
	public TextBox email = new TextBox("Name", "newEmail");
	public TextBox initialPassword = new TextBox("Name", "newPassword");
	public TextBox initialPasswordConfirm = new TextBox("Name", "newPasswordAgain");
	public CheckBox formRights = new CheckBox("Name", "newCreateRight");
	public CheckBox adminRights = new CheckBox("Name", "newAdminRight");
	public CheckBox syncRights = new CheckBox("Name", "newSyncRight");
	
	public Button createUser = new Button("Link Text", "Create User");
	public Button cancel = new Button("Link Text", "Cancel");
	
	
	public String getStatus(){
		
		return DriverManager.getDriver(null).findElement(By.id("error")).getText();
	}
}
