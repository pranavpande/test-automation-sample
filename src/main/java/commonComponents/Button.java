package commonComponents;

public class Button extends Element{

	public Button(String typeOfID, String identifyingString) {
		super(typeOfID, identifyingString);
	}

	public void click() {
		getElement().click();
	}
}
