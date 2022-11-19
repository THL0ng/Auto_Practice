package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_02_Selenium_API_Commands {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver" , projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Veryfy_URL() throws InterruptedException {
		
		driver.get("http://live.techpanda.org");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Thread.sleep(2000);
		
		String homepageURL = driver.getCurrentUrl();
		Assert.assertEquals(homepageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		
		
		String RegisterPageURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterPageURL, "http://live.techpanda.org/index.php/customer/account/create/");
			
	}
	
	
	@Test
	public void TC_02_Veryfy_Title() throws InterruptedException {
		
		driver.get("http://live.techpanda.org");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Thread.sleep(2000);
		
		String TitleLoginPage = driver.getTitle();
		Assert.assertEquals(TitleLoginPage, "Customer Login");
			
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		Thread.sleep(2000);
		
		String TitleRegisterPage = driver.getTitle();
		Assert.assertEquals(TitleRegisterPage, "Create New Customer Account");
		
			
	}
	
	
	@Test
	public void TC_03_Navigate_function() throws InterruptedException {
		
		driver.get("http://live.techpanda.org");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Thread.sleep(2000);
		
			
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		Thread.sleep(2000);
		
		String RegisterPageURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterPageURL, "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		String LoginpageURL = driver.getCurrentUrl();
		Assert.assertEquals(LoginpageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		String TitleRegisterPage  = driver.getTitle();
		Assert.assertEquals(TitleRegisterPage, "Create New Customer Account");
		
				
	}
	
	

	@Test
	public void TC_04_Get_PageSource_Code() throws InterruptedException {
		
		driver.get("http://live.techpanda.org");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Thread.sleep(2000);
		
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		Thread.sleep(2000);
		
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
				
	}

	
	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
