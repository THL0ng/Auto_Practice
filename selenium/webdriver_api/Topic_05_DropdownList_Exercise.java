package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_05_DropdownList_Exercise {
	WebDriver driver;
	Select select;
	
	String Firstname = " Thanh";
	String Lastname = " Long";

	
	String email = "automation" + random() + "@gmail.com";
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	 
	WebDriverWait waitExplicit;

	By numberItems = By.xpath("//li[@class='ui-menu-item']/div");
	
	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver" , projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
				
	}

	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com");
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(Firstname);

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(Lastname);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		select.selectByValue("1");
		
		int allDay = select.getOptions().size();
		System.out.println(" Tất cả các ngày = " +  allDay);
		Assert.assertEquals(allDay, 32);
		
		List <WebElement> allDayOptions = select.getOptions();
		for (WebElement DayList :allDayOptions ) {
			System.out.println(DayList.getText());
		}
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("May");
		
		int allMonth = select.getOptions().size();
		System.out.println("Tất cả các tháng = " + allMonth);
		Assert.assertEquals(allMonth, 13);
		
		List <WebElement> allMonthOptions = select.getOptions();
		for (WebElement MonthList :allMonthOptions ) {
			System.out.println(MonthList.getText());
		}
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1980");
		
		int allYear = select.getOptions().size();
		System.out.println("Tất cả các tháng = " + allYear);
		Assert.assertEquals(allYear, 112);
		
		List <WebElement> allYearOptions = select.getOptions();
		for (WebElement YearList :allYearOptions ) {
			System.out.println(YearList.getText());
		}
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

	
		driver.findElement(By.xpath("//button[text()='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		

	}

	@Test
	public void TC_02_Customer_DropdownList() {
		
	}
	



	@AfterTest
	public void afterTest() {
		
	}

}
