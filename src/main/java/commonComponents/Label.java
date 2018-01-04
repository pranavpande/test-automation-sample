package commonComponents;

public class Label extends Element{

	public Label(String typeOfID, String identifyingString) {
		super(typeOfID, identifyingString);
	}
	
	public String getText() {
		return getElement().getText();
	}

}
