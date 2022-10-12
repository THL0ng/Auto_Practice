package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Topic_1 {
	WebDriver driver;

	String Firstname = "automation";
	String Lastname = "testing";
	String ValidEmail = "automation" + random() + "@gmail.com";
	String ValidPassword = "123123";

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Login_Empty() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Thread.sleep(2000);

		String EmptyMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(EmptyMsg, "This is a required field.");

	}

	@Test
	public void TC_02_Login_With_Email_Invalid() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123456@123456");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Thread.sleep(2000);

		String EmailInvalidMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(EmailInvalidMsg, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Password_Less_6Char() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Thread.sleep(2000);

		String Enter6orMore = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(Enter6orMore, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_With_Password_Incorrect() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Thread.sleep(2000);

		String PWD_Incorrect = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(PWD_Incorrect, "Invalid login or password.");

	}
	
	
	@Test
	public void TC_05_Create_New_Account() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		Thread.sleep(2000);

	}
	
	

	@Test
	public void TC_06_Login_With_Vaild_Email_And_Password() throws InterruptedException {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		Thread.sleep(2000);

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}

}
