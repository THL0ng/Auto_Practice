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

public class Topic_00_Tempate {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Veryfy_URL() throws InterruptedException {
		
	
	}
	
	
	@Test
	public void TC_02_Veryfy_Title() throws InterruptedException {
			
	}
	
	@Test
	public void TC_03_Navigate_function() throws InterruptedException {
		
	}
	
	
	@Test
	public void TC_04_Get_PageSource_Code() throws InterruptedException {
	
				
	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
