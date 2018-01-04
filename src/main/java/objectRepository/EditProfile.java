package objectRepository;

import commonComponents.Button;
import commonComponents.Label;
import commonComponents.TextBox;

public class EditProfile {

	public Label username = new Label("xpath", "//div[contains(text(), 'Username')]/../../td[2]/div");

	public TextBox firstName = new TextBox("name", "updateFirstname");
	public TextBox lastName = new TextBox("name", "updateLastname");
	public TextBox email = new TextBox("name", "updateEmail");
	public TextBox password = new TextBox("name", "updatePassword");
	public TextBox passwordConfirm = new TextBox("name", "updatePasswordAgain");

	public Label formRights = new Label("xpath", "//div[contains(text(), 'Right to create forms')]/../../td[2]/div");
	public Label adminRights = new Label("xpath", "//div[contains(text(), 'Admin')]/../../td[2]/div");

	public Button save = new Button("linktext", "Save");
	public Button cancel = new Button("linktext", "Cancel");

}
