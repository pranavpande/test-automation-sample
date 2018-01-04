package commonComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driverManager.DriverManager;

public class Element {

	private String identifyingType;
	private String identifyingString;
	
	public String getIdentifyingType() {
		return identifyingType;
	}

	public String getIdentifyingString() {
		return identifyingString;
	}
			
	public Element(String typeOfID, String identifyingString){
		this.identifyingString = identifyingString;
		this.identifyingType = typeOfID.replaceAll(" ", "").toLowerCase();
	}
	
	protected WebElement getElement() throws NoSuchElementException{
		WebDriver driver = DriverManager.getDriver(null);
		if(identifyingType.equals("name")){
			return driver.findElement(By.name(identifyingString));
		}else if(identifyingType.equals("classname")){
			return driver.findElement(By.className(identifyingString));
		}else if(identifyingType.equals("cssselector")){
			return driver.findElement(By.cssSelector(identifyingString));
		}else if(identifyingType.equals("xpath")){
			return driver.findElement(By.xpath(identifyingString));
		}else if(identifyingType.equals("id")){
			return driver.findElement(By.id(identifyingString));
		}else if(identifyingType.equals("tagname")){
			return driver.findElement(By.tagName(identifyingString));
		}else if(identifyingType.equals("linktext")){
			return driver.findElement(By.linkText(identifyingString));
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean isDisplayed() {
		return this.getElement().isDisplayed();
	}
}
