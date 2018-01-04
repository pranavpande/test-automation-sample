package supportLibraries;

import java.io.FileNotFoundException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverManager.DriverManager;
import testRunner.ConfigManager;

public class PageNavigation {

	public PageNavigation() throws FileNotFoundException {
		ConfigManager.readConfigFile();
	}

	public void login() {

		WebDriver driver = DriverManager.getDriver(null);
		driver.get("https://cd-loadapp.iformbuilder.com/");
		driver.findElement(By.xpath("//img[@src='https://iform-artwork.s3.amazonaws.com/load.iformbuilder.com/sign_in.png']")).click();
		driver.findElement(By.name("USERNAME")).sendKeys(ConfigManager.configInfo.get("loginusername"));
		driver.findElement(By.name("PASSWORD")).sendKeys(ConfigManager.configInfo.get("loginpassword"));
		driver.findElement(By.xpath("//input[@src='https://iform-artwork.s3.amazonaws.com/load.iformbuilder.com/login.png']")).click();

	}

	public void logout(){

		WebDriver driver = DriverManager.getDriver(null);
		driver.findElement(By.xpath("//img[@src='https://iform-artwork.s3.amazonaws.com/load.iformbuilder.com/logout.png']")).click();
		
	}

	public void refreshPage() throws InterruptedException{

		DriverManager.getDriver(null).navigate().refresh();
	}

	public void sleep(double seconds) throws InterruptedException{
		Thread.sleep((long) (seconds*1000));
	}

	public void acceptPrompt(){
		try { 
			Alert alert = DriverManager.getDriver(null).switchTo().alert();
			alert.accept();
		}catch (NoAlertPresentException e) {}
	}

	public void declinePrompt(){
		try { 
			Alert alert = DriverManager.getDriver(null).switchTo().alert();
			alert.dismiss();
		}catch (NoAlertPresentException e) {}
	}

	public void loadPage(String modalDialogue) {


		if(modalDialogue.equals("true")){
			acceptPrompt();
		}
		if(modalDialogue.equals("false")){
			declinePrompt();
		}
		WebDriver driver = DriverManager.getDriver(null);

		if (ConfigManager.configInfo.get("datacollector").equals("false")){
			String currentURL = driver.getCurrentUrl().split("iformbuilder.com")[1];

			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebDriverWait wait = new WebDriverWait(driver, 60);

			if(currentURL.contains("?")){
				String [] url = currentURL.split("\\?"); 
				currentURL = url[0];
			}
			switch (currentURL) {
			case "/exzact/dataViews.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/dataList.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminUsers.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminUserGroup.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminAssignment.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminFormAssignment.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminFormGroup.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminFormLocalization.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;
			case "/exzact/adminFormScoring.php":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pReload.pButton > span")));      
				break;  
			default:    

			}
		}
	}

	public void goTo(String tabName) {

		WebDriver driver = DriverManager.getDriver(null);
		String location = tabName.toLowerCase();
		String testUrlString = ConfigManager.configInfo.get("testurl");
		String datacollector = ConfigManager.configInfo.get("datacollector");
		if (datacollector.equals("true")) {
			switch (location) {
			case "home":
				driver.get("https://qa-console.zerionsoftware.com/");
				loadPage("");
				break;
			case "form assignment":
				driver.findElement(By.cssSelector("#zws-navigation > ul > li:nth-child(2) > a")).click();
				driver.findElement(By.cssSelector("#zws-navigation > ul > li:nth-child(2) > ul > li:nth-child(2) > a")).click();
				loadPage("");
				break;
			case "data":
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[1]/a")).click();
				loadPage("");
				break;
			case "form builder":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Form Builder")).click();
				loadPage("");
				break;
			case "form group":
				driver.findElement(By.cssSelector("#zws-navigation > ul > li:nth-child(2) > a")).click();
				driver.findElement(By.cssSelector("#zws-navigation > ul > li:nth-child(2) > ul > li:nth-child(3) > a")).click();
				loadPage("");
				break;
			case "localization":
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[2]/a")).click();
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[2]/ul/li[4]/a")).click();
				loadPage("");
				break;
			case "scoring":
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[2]/a")).click();
				WebElement myDynamicElement = (new WebDriverWait(driver, 10))
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='zws-navigation']/ul/li[2]/ul/li[5]/a")));
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[2]/ul/li[5]/a")).click();
				loadPage("");
				break;
			case "package builder":
				//Longer because of drop-down within drop-down
				Actions actions = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackages = driver.findElement(By.partialLinkText("Form Packages"));
				actions.moveToElement(formPackages).perform();
				WebElement packageBuilder = driver.findElement(By.linkText("Package Builder"));
				actions.moveToElement(packageBuilder);
				actions.click();
				actions.perform();
				loadPage("");
				break;
			case "package import":
				Actions action = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackage = driver.findElement(By.partialLinkText("Form Packages"));
				action.moveToElement(formPackage).perform();
				WebElement packageImport = driver.findElement(By.linkText("Package Import"));
				action.moveToElement(packageImport);
				action.click();
				action.perform();
				loadPage("");
				break;

			case "manage packages":
				Actions action_2 = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackagebutton = driver.findElement(By.partialLinkText("Form Packages"));
				action_2.moveToElement(formPackagebutton).perform();
				WebElement managePackages = driver.findElement(By.linkText("Manage Packages"));
				action_2.moveToElement(managePackages);
				action_2.click();
				action_2.perform();
				loadPage("");
				break;

			case "notifications":
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[3]/a")).click();
				driver.findElement(By.xpath("//*[@id='zws-navigation']/ul/li[3]/ul/li[3]/a")).click();
				loadPage("");
				break;
			case "add/edit users":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("Add/Edit Users")).click();
				loadPage("");
				break;
			case "my profile":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("My Profile")).click();
				loadPage("");
				break;
			case "user group":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("User Group")).click();
				loadPage("");
				break;
			case "company info":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("Company Info")).click();
				loadPage("");
				break;
			case "license":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("License")).click();	
				loadPage("");
				break;
			case "api apps":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("API Apps")).click();
				loadPage("");
				break;
			case "billing":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("Billing")).click();	
				loadPage("");
				break;
			case "fb v2":
				driver.findElement(By.linkText("Support")).click();
				driver.findElement(By.linkText("FB V2")).click();
				loadPage("");
				break;
			case "ol v2":
				driver.findElement(By.linkText("Support")).click();
				driver.findElement(By.linkText("OL V2")).click();
				loadPage("");
				break;

			default:
				System.out.println("method \'navigateTo\' in PagelessActions.java incorrect input \'" + location + "\'");	
			}



		}else {
			switch (location) { 
			case "data":
				driver.findElement(By.linkText("Data")).click();
				loadPage("");
				break;
			case "form assignment":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Form Assignment")).click();
				loadPage("");
				break;
			case "form builder":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Form Builder")).click();
				loadPage("");
				break;
			case "form group":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Form Group")).click();
				loadPage("");
				break;
			case "localization":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Localization")).click();
				loadPage("");
				break;
			case "scoring":
				driver.findElement(By.linkText("Forms")).click();
				driver.findElement(By.linkText("Scoring")).click();
				loadPage("");
				break;
			case "package builder":
				//Longer because of drop-down within drop-down
				Actions actions = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackages = driver.findElement(By.partialLinkText("Form Packages"));
				actions.moveToElement(formPackages).perform();
				WebElement packageBuilder = driver.findElement(By.linkText("Package Builder"));
				actions.moveToElement(packageBuilder);
				actions.click();
				actions.perform();
				loadPage("");
				break;
			case "package import":
				Actions action = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackage = driver.findElement(By.partialLinkText("Form Packages"));
				action.moveToElement(formPackage).perform();
				WebElement packageImport = driver.findElement(By.linkText("Package Import"));
				action.moveToElement(packageImport);
				action.click();
				action.perform();
				loadPage("");
				break;

			case "manage packages":
				Actions action_2 = new Actions(driver);
				driver.findElement(By.linkText("Forms")).click();
				WebElement formPackagebutton = driver.findElement(By.partialLinkText("Form Packages"));
				action_2.moveToElement(formPackagebutton).perform();
				WebElement managePackages = driver.findElement(By.linkText("Manage Packages"));
				action_2.moveToElement(managePackages);
				action_2.click();
				action_2.perform();
				loadPage("");
				break;

			case "notifications":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("Notifications")).click();
				loadPage("");
				break;
			case "add/edit users":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("Add/Edit Users")).click();
				loadPage("");
				break;
			case "my profile":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("My Profile")).click();
				loadPage("");
				break;
			case "user group":
				driver.findElement(By.linkText("Users")).click();
				driver.findElement(By.linkText("User Group")).click();
				loadPage("");
				break;
			case "company info":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("Company Info")).click();
				loadPage("");
				break;
			case "license":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("License")).click();	
				loadPage("");
				break;
			case "api apps":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("API Apps")).click();
				loadPage("");
				break;
			case "billing":
				driver.findElement(By.linkText("Company")).click();
				driver.findElement(By.linkText("Billing")).click();	
				loadPage("");
				break;
			case "fb v2":
				driver.findElement(By.linkText("Support")).click();
				driver.findElement(By.linkText("FB V2")).click();
				loadPage("");
				break;
			case "ol v2":
				driver.findElement(By.linkText("Support")).click();
				driver.findElement(By.linkText("OL V2")).click();
				loadPage("");
				break;

			case "home":
				if (driver==null) {
					System.out.println("driver hasn't be setup!!!");
					break;
				}

				driver.get("https://"+testUrlString+".iformbuilder.com/");
				loadPage("");
				break;
			case "homepage":
				driver.get("https://"+testUrlString+".iformbuilder.com/");
				loadPage("");
				break;


			default:
				System.out.println("method \'navigateTo\' in PagelessActions.java incorrect input \'" + location + "\'");		
			}
		}
	}
}
