package testRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import driverManager.DriverManager;
import supportLibraries.ExtentManager;
import supportLibraries.PageNavigation;

public class TestNGListener implements ITestListener, ISuiteListener{

	private static ExtentReports extent = ExtentManager.createInstance("extent.html");

	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public synchronized void onFinish(ITestContext result) {
		
	}

	@Override
	public synchronized void onStart(ITestContext result) {
		
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		try {
			TakesScreenshot ts = (TakesScreenshot)DriverManager.getDriver(null);
			File source = ts.getScreenshotAs(OutputType.FILE);
			String screenshotDirectory= ConfigManager.configInfo.get("screenshotdirectory");
			FileUtils.copyFile(source, new File(screenshotDirectory+result.getName()+".png"));
			//tr.sendEmail((result.getThrowable()).toString(), result.getName());
			test.get().fail(result.getThrowable());
			test.get().fail("details").addScreenCaptureFromPath(screenshotDirectory+result.getName()+".png");
		} catch(Exception e) {
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentTest node = parentTest.get().createNode(result.getMethod().getMethodName());
		test.set(node);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		String browser = suite.getParameter("browser");
		ExtentTest parent = extent.createTest(browser);
		parentTest.set(parent);
		WebDriver driver = DriverManager.getDriver(browser);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		DriverManager.quitDriver();
		extent.flush();
	}

//	@BeforeClass
//	public synchronized void onClassStart(ITestResult result) {
//		String classInstance = result.getTestClass().getName();
//		String[] nameArray = classInstance.split(Pattern.quote("."));
//		//String packageName = nameArray[0];
//		String classInstanceName = nameArray[1];
//		ExtentTest parent = extent.createTest(classInstanceName);
//		parent.assignCategory(browserType.get());
//		parentTest.set(parent);
//	}
}
