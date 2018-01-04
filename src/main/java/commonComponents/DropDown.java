package commonComponents;

import org.openqa.selenium.support.ui.Select;

public class DropDown extends Element{

	public DropDown(String typeOfID, String identifyingString) {
		super(typeOfID, identifyingString);
	}

	public Select getDropDownList() {
		return new Select(this.getElement());
	}
	
	public void selectByIndex(int index) {
		Select select = getDropDownList();
		select.selectByIndex(index);
	}
	
	public void selectByValue(String value) {
		Select select = getDropDownList();
		select.selectByValue(value);
	}
	
	public void selectByText(String text) {
		Select select = getDropDownList();
		select.selectByVisibleText(text);
	}
}
