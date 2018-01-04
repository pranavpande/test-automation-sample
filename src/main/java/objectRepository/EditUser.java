package objectRepository;

import commonComponents.Button;
import commonComponents.CheckBox;
import commonComponents.Label;
import commonComponents.TextBox;

public class EditUser {

	public Label username = new Label("Xpath", "//div[contains(text(), 'Username')]/../../td[2]/div");
	public TextBox firstName = new TextBox("Name", "updateFirstname");
	public TextBox lastName = new TextBox("Name", "updateLastname");
	public TextBox email = new TextBox("Name", "updateEmail");
	public TextBox initialPassword = new TextBox("Name", "updatePassword");
	public TextBox initialPasswordConfirm = new TextBox("Name", "updatePasswordAgain");
	public CheckBox formRights = new CheckBox("Name", "updateCreateRight");
	public CheckBox adminRights = new CheckBox("Name", "updateAdminRight");
	public CheckBox syncRights = new CheckBox("Name", "updateSyncRight");
	
	public Button update = new Button("Link Text", "Update");
	public Button cancel = new Button("Link Text", "Cancel");
	
}
