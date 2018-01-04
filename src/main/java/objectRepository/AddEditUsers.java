package objectRepository;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonComponents.Button;
import commonComponents.CheckBox;
import commonComponents.DropDown;
import commonComponents.Label;
import commonComponents.TextBox;
import driverManager.DriverManager;
import supportLibraries.PageNavigation;

public class AddEditUsers {
	
	public HashMap <String, String> currentUserInfo = new HashMap<String, String>();
	
	public String status;
	//Top row
	public Button newButton= new Button("Xpath", "//*[@id='fbutton_New']/div/span/div");
	public Button editButton = new Button("Xpath", "//*[@id='fbutton_Edit']/div/span/div");
	public Button deleteButton = new Button("Xpath", "//*[@id='fbutton_Delete']/div/span/div");
	
	//Second to last row
	public TextBox searchBox = new TextBox("Name", "q");
	public CheckBox exactMatch = new CheckBox("Name", "qwildcard");
	public DropDown typeSearch = new DropDown("Name", "qtype");
	public Button searchButton = new Button("Name", "qSearch");
	public Button resetButton = new Button("Name", "qReset");
	
	//last row buttons and textboxes
	public DropDown resultsShown = new DropDown("Name", "rp");
	public Button firstButton = new Button("css selector", "div.pLast.pButton > span");
	public Button previousButton = new Button("css selector", "div.pLast.pButton > span");
	public TextBox pageNumber = new TextBox("css selector", "div.pLast.pButton > span");
	public Button nextButton = new Button("css selector", "div.pLast.pButton > span");
	public Button lastButton = new Button("css selector", "div.pLast.pButton > span");
	public Button reloadButton = new Button("css selector", "div.pReload.pButton > span");
	public Label pageStatus = new Label("Css selector", "span.pPageStat");
	
	public void search(String username) throws InterruptedException{
		WebDriver driver = DriverManager.getDriver(null);
		searchBox.fill(username);
		exactMatch.check();
		typeSearch.selectByValue("USERNAME");
		searchButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(pageStatus.getIdentifyingString()), "Processing, please wait ..."));
	}
	
	public void search(String id, String typeOfSearch) throws InterruptedException{
		
		searchBox.fill(id);
		exactMatch.check();
		typeSearch.selectByText(typeOfSearch);
		searchButton.click();
	}
	
	public void storeUserInfo(String username){
		
		HashMap<String, String> hash = new HashMap<String, String>();
		WebDriver driver = DriverManager.getDriver(null);

		String rowID = driver.findElement(By.xpath("//a[contains(text(), '" + username + "')]/../../..")).getAttribute("id");
		
		hash.put("username", driver.findElement(By.xpath("//*[@id='" + rowID + "_USERNAME']/div")).getText());
		hash.put("firstname", driver.findElement(By.xpath("//*[@id='" + rowID + "_FIRSTNAME']/div")).getText());
		hash.put("lastname", driver.findElement(By.xpath("//*[@id='" + rowID + "_LASTNAME']/div")).getText());
		hash.put("email", driver.findElement(By.xpath("//*[@id='" + rowID + "_EMAIL']/div")).getText());
		
		
		if(driver.findElements(By.xpath("//*[@id='" + rowID + "_IS_LOCKED']/div/img")).size() != 0){
			hash.put("status", driver.findElement(By.xpath("//*[@id='" + rowID + "_IS_LOCKED']/div/img")).getAttribute("src"));
		}else{
			hash.put("status", driver.findElement(By.xpath("//*[@id='" + rowID + "_IS_LOCKED']/div")).getText());
		}
		
		if(driver.findElements(By.xpath("//*[@id='" + rowID + "_RIGHTS_TO_CREATE_FORM']/div/img")).size() != 0){
			hash.put("formright", driver.findElement(By.xpath("//*[@id='" + rowID + "_RIGHTS_TO_CREATE_FORM']/div/img")).getAttribute("src"));
		}else{
			hash.put("formright", driver.findElement(By.xpath("//*[@id='" + rowID + "_RIGHTS_TO_CREATE_FORM']/div")).getText());
		}
		
		if(driver.findElements(By.xpath("//*[@id='" + rowID + "_COMPANY_ADMIN']/div/img")).size() != 0){
			hash.put("companyadmin", driver.findElement(By.xpath("//*[@id='" + rowID + "_COMPANY_ADMIN']/div/img")).getAttribute("src"));
		}else{
			hash.put("companyadmin", driver.findElement(By.xpath("//*[@id='" + rowID + "_COMPANY_ADMIN']/div")).getText());
		}
		
		currentUserInfo = hash;
	}
	
	public void selectUser(String username){

		//potential optimization, but this code works
		WebDriver driver = DriverManager.getDriver(null);
		String rowID = driver.findElement(By.xpath("//a[contains(text(), '" + username + "')]/../../..")).getAttribute("id");
		WebElement element = driver.findElement(By.xpath("//*[@id='" + rowID + "_FIRSTNAME']"));
		Actions action = new Actions(driver);
		//move one pixel offset from element to click on div, therefore selecting row
		action.moveToElement(element, 50, 1).click().perform();
		
	}
	
	public void selectUser() {	
		//potential optimization, but this code works
		WebDriver driver = DriverManager.getDriver(null);
		String rowID = driver.findElement(By.xpath("//*[@id='flex1']/tbody/tr[1]")).getAttribute("id");
		WebElement element = driver.findElement(By.xpath("//*[@id='" + rowID + "_FIRSTNAME']"));
		Actions action = new Actions(driver);
		//move one pixel offset from element to click on div, therefore selecting row
		action.moveToElement(element, 1, 1).click().perform();
	}

	public String getStatus(){

		return DriverManager.getDriver(null).findElement(By.id("status")).getText();

	}

	public void goTo() throws FileNotFoundException{
		PageNavigation navigation = new PageNavigation();
		navigation.goTo("Add/Edit Users");

	}
}
