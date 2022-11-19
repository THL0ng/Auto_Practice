package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_11_Wait_Part_1_Element_Status {
	WebDriver driver;
	boolean status;
	WebDriverWait waitExplicit;



	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver",  projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		waitExplicit = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_ElementDisplayOrVisible() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐIỀU KIỆN 1 : ELEMENT CÓ HIỂN THỊ TRONG UI VÀ CÓ TRONG DOM
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); // chờ cho element displayed/visible
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();  // kiểm tra element displayed
		System.out.println("ELEMENT CÓ HIỂN THỊ TRONG UI VÀ CÓ TRONG DOM = " + status );

		// ĐIỀU KIỆN 2 : ELEMENT KHÔNG HIỂN THỊ TRÊN UI VÀ CÓ TRONG DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));  // chờ cho element undisplayed / invisible
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();		
		System.out.println("ELEMENT KHÔNG HIỂN THỊ TRÊN UI VÀ CÓ TRONG DOM = " + status );
		
		// ĐIỀU KIỆN 3 : ELEMENT KHÔNG HIỂN THỊ TRONG UI VÀ TRONG DOM
		//waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']"))); 
		//status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		//System.out.println("ELEMENT KHÔNG HIỂN THỊ TRONG UI VÀ TRONG DOM = " + status );
		
		// TC1 Visible : PASS được khi có 2 điều kiện 1+2 ( Có trong DOM )
	}


	@Test
	public void TC_02_ElementUndisplayOrInvisible() throws InterruptedException {
		// ĐIỀU KIỆN 1 : ELEMENT CÓ HIỂN THỊ TRONG UI VÀ CÓ TRONG DOM
		// waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));

		// ĐIỀU KIỆN 2 : ELEMENT KHÔNG HIỂN THỊ TRÊN UI VÀ CÓ TRONG DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));

		// ĐIỀU KIỆN 3 : ELEMENT KHÔNG HIỂN THỊ TRONG UI VÀ TRONG DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));

		// TC2 Invisible : PASS được khi có 2 điều kiện 2+3 ( Element ko hiển thị trong UI)

	}
	
	@Test
	public void TC_03_ElementPresence() throws InterruptedException {
		// ĐIỀU KIỆN 1 : ELEMENT CÓ HIỂN THỊ TRONG UI VÀ CÓ TRONG DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));

		// ĐIỀU KIỆN 2 : ELEMENT KHÔNG HIỂN THỊ TRÊN UI VÀ CÓ TRONG DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));

		// ĐIỀU KIỆN 3 : ELEMENT KHÔNG HIỂN THỊ TRONG UI VÀ TRONG DOM
		// waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));

		// TC3 presence : Có trong DOM mới pass
		
	}
	
	

	@AfterTest
	public void afterTest() {
	
	}

}
