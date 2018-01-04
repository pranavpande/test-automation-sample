package commonComponents;

import org.openqa.selenium.WebElement;

public class CheckBox extends Element{

	public CheckBox(String typeOfID, String identifyingString) {
		super(typeOfID, identifyingString);
	}

	public void check() {
		WebElement element = this.getElement();
		if(!element.isSelected()) element.click();
	}
}
