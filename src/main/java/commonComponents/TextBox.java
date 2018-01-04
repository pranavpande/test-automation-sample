package commonComponents;

public class TextBox extends Element{

	public TextBox(String typeOfID, String identifyingString) {
		super(typeOfID, identifyingString);
	}

	public void fill(String text) {
		getElement().clear();
		getElement().sendKeys(text);
	}
	
	public String getText() {
		return getElement().getAttribute("value");
	}
}
