package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_11_Wait_Part_5_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Implicit_Wait() {

		driver.get("http://demo.guru99.com/");

		// IMPLICIT
		System.out.println("---------STEP 01 - Start TC_01_Element_Found:" + new Date() + "---------");
		try { 
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println("---------STEP 01 - End TC_01_Element_Found:" + new Date() + "---------");

		// EXPLICIT
		System.out.println("---------STEP 02 - Start TC_01_Element_Found:" + new Date() + "---------");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='btnLogin']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println("---------STEP 02 - Start TC_01_Element_Found:" + new Date() + "---------");

	}

	// TC1 : TÌM THẤY ELEMENT NÊN SET BAO NHIÊU SECOND THÌ IMPLICIT VÀ EXPLICIT CŨNG
	// KO ẢNH HƯỞNG

	@Test

	public void TC_02_Not_Found_Element() {

		// EXPLICIT WAIT(ELEMENT STATUS)
		explicitWait = new WebDriverWait(driver, 10);

		// IMPLICIT WAIT(findElement/findElements)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("c");

		// IMPLICIT ( NOT FOUND ELEMENT )
		System.out.println("---------STEP 01 - Start Implicit:" + new Date() + "---------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='automation-testing']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("-----Step 01 : Nhảy vào catch-------");
			System.out.println(ex.getMessage());
		}
		System.out.println("---------STEP 01 - End Implicit:" + new Date() + "---------");

		// EXPLICIT ( NOT FOUND--THAM SỐ LÀ WEBLEMENT)
		System.out.println("---------STEP 02 - Start Explicit (WebElement):" + new Date() + "---------");
		try {
			WebElement emailTextbox = explicitWait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@name='automation-testing']"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("-----Step 02 : Nhảy vào catch-------");
			System.out.println(ex.getMessage());
		}
		System.out.println("---------STEP 02 - End Explicit (WebElement):" + new Date() + "---------");
		// THAM SỐ LÀ WEBELEMENT : NHẬN TIMEOUT CỦA IMPLICIT

		// EXPLICIT ( NOT FOUND--THAM SỐ LÀ BY)
		System.out.println("---------STEP 03 - Start Explicit (BY):" + new Date() + "---------");
		try {
			WebElement emailTextbox = explicitWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automation-testing']")));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("-----Step 03 : Nhảy vào catch-------");
			System.out.println(ex.getMessage());
		}

		System.out.println("---------STEP 03 - End  Explicit (BY):" + new Date() + "---------");
		// THAM SỐ LÀ BY : NHẬN TIMEOUT CỦA EXPLICIT

	}

	@AfterTest
	public void afterTest() {

	}

}
