package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_06_Button_Radio_Checkbox_Alert {
	WebDriver driver;
	
	JavascriptExecutor javascript ; 
	Alert alert ; 

	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver" , projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/");
		
		// BUTTON
		elementEnabled("//button[@id='button-disabled");
		
		// CHECKBOX
		elementEnabled("//input[@id='development']");
		
		// RADIO BUTTON
		elementEnabled("//input[@id='under_!8']");

		
		
	}
	
	
	@Test
	public void TC_02_ClickByJS() throws InterruptedException {
		
		driver.get("https://demo.nopcommerce.com/");
		
		// driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Desktops ']")).click();
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Desktops ']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
				
	}
	

	@Test
	public void TC_03_CheckBox() throws InterruptedException {
		
		// VIẾT THƯỜNG
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		driver.findElement(By.xpath("//input[@id='eq3']")).click();
		
		elementSelectedStatus("//input[@id='eq3']");
		
		Assert.assertTrue(isElementSelected("//input[@id='eq3']"));
		
		
		driver.findElement(By.xpath("//input[@id='eq3']")).click();
		
		elementSelectedStatus("//input[@id='eq3']");
		
		Assert.assertFalse(isElementSelected("//input[@id='eq3']"));
		
		
		// CLICK BY JS
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		String checkboxInput = "//label[text()='Luggage compartment cover']/preceding-sibling::input";
		
		clickByJS(checkboxInput);
		Thread.sleep(2000);	
		elementSelectedStatus(checkboxInput);
		Assert.assertTrue(isElementSelected(checkboxInput));
		
		
		clickByJS(checkboxInput);
		Thread.sleep(2000);	
		elementSelectedStatus(checkboxInput);
		Assert.assertFalse(isElementSelected(checkboxInput));

				
	}
	
	
	@Test
	public void TC_04_Alert() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/basic-form/");
		String resultMessage = "//p[@id='result']";
		String fullName = "Thanh Long";
		
		// ACCEPT ALERT
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();	
		alert = driver.switchTo().alert();	
		Assert.assertEquals(alert.getText(), "I am a JS Alert");		
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");	
		
		// CANNEL ALERT
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();	
		alert = driver.switchTo().alert();	
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");	

		
		// SENDKEY TO ALERT
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();	
		alert = driver.switchTo().alert();	
		alert.sendKeys(fullName);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: " + fullName);	

	}
	
			
	public void elementEnabled (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
		}	else {
			System.out.println("Element is disabled");

		}
	}
		
	public void elementSelectedStatus (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			System.out.println("Element is Selected");
		}	else {
			System.out.println("Element is deselected");

		}
	}
			
	public boolean isElementSelected (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			return true;
		}	else {
			return false;

		}
	}
		
	public void clickByJS (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);

	}

	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if ( ! element.isSelected()) {
			element.click();
		}
		
	}
		
	public void unCheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
		
	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
