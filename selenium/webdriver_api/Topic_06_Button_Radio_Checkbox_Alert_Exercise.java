package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_06_Button_Radio_Checkbox_Alert_Exercise {
	WebDriver driver;
	String email = "automation" + random() + "@gmail.com";
	String password = "123456";
	
	Select select;
	

	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	
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
	public void TC_01_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']//a[text()='Đăng nhập']")).click();
		
		elementEnabled("//button[@class='fhs-btn-login']//span[text()='Đăng nhập']"); 
		
		driver.findElement(By.xpath("//div[@class='popup-login-content']//span[@class='fhs-input-icon fhs-textbox-alert']//preceding::input[@id='login_username']")).sendKeys(email);
		
		driver.findElement(By.xpath("//div[@class='fhs-input-group']//input[@id='login_password']")).sendKeys(password);
		
		
		elementEnabled("//div[@class='fhs-btn-box']//span[text()='Đăng nhập']");
	
		
	}
	
	
	
	@Test
	public void TC_02_Default_Checkbox_RadioButton() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		driver.findElement(By.xpath("//input[@id='eq5']")).click();
		
		elementSelectedStatus("//input[@id='eq5']");
		
		driver.findElement(By.xpath("//input[@id='eq5']")).click();

		elementSelectedStatus("//input[@id='eq5']");

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		driver.findElement(By.xpath("//input[@id='engine3']")).click();

		elementSelectedStatus("//input[@id='engine3']");
	
		
	}
	
	
	@Test
	public void TC_03_Default_Checkbox_RadioButton() throws InterruptedException {
		driver.get("https://material.angular.io/components/radio/examples");
		
		driver.findElement(By.xpath("//label[@for='mat-radio-4-input']//span[@class='mat-radio-outer-circle']")).click();
		
		elementSelectedStatus("//label[@for='mat-radio-4-input']//span[@class='mat-radio-outer-circle']");
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		driver.findElement(By.xpath("//label[@for='mat-checkbox-1-input']/span[@class='mat-checkbox-inner-container']")).click();
		
		driver.findElement(By.xpath("//label[@for='mat-checkbox-2-input']/span[@class='mat-checkbox-inner-container']")).click();
		
		
		elementSelectedStatus("//label[@for='mat-checkbox-1-input']/span[@class='mat-checkbox-inner-container']");
		elementSelectedStatus("//label[@for='mat-checkbox-2-input']/span[@class='mat-checkbox-inner-container']");
		
		
		driver.findElement(By.xpath("//label[@for='mat-checkbox-1-input']/span[@class='mat-checkbox-inner-container']")).click();
		
		driver.findElement(By.xpath("//label[@for='mat-checkbox-2-input']/span[@class='mat-checkbox-inner-container']")).click();
		
		
		elementSelectedStatus("//label[@for='mat-checkbox-1-input']/span[@class='mat-checkbox-inner-container']");
		elementSelectedStatus("//label[@for='mat-checkbox-2-input']/span[@class='mat-checkbox-inner-container']");

	}
	
	@Test
	public void TC_04_Default_Checkbox_RadioButton() throws InterruptedException {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='i14']//div[@class='AB7Lab Id5V1']")).isSelected());
		
		driver.findElement(By.xpath("//div[@id='i14']//div[@class='AB7Lab Id5V1']")).click();
		

	}
	
	
	@Test
	public void TC_05_Accept_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");		

		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");	
		

	}
	
	@Test
	public void TC_06_Confirm_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();	
		alert = driver.switchTo().alert();	
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");	
		

	}
	
	
	@Test
	public void TC_07_Prompt_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		String fullName = "Thanh Long";
		
		
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
				
		
	public void clickByJS (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);

	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
