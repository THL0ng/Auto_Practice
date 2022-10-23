package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_4_Textbox_Textarea_DropdownList {
	WebDriver driver;
	String username = "mngr449953";
	String password = "byzehYv";
	String customerID ;
	
	
	// INPUT OR OUTPUT DATA
	
	String customerName = "Thanh Long"; 
	String gender = "male"; 
	String dateOfBirth = "1995-12-04"; 
	String address = "16A LHP"; 
	String city = "HCM"; 
	String state = "VietNam"; 
	String pin = "100000"; 
	String phone = "0906999593"; 
	String email = "automation" + random() + "@gmail.com";

	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	
	
	// LOCATOR FOR NEW / EDIT CUSTOMER FORM
	By nameTextbox = By.name("name");
	By genderCheckbox = By.name("rad1");
	By dateOfBirthTextbox = By.name("dob");
	By adddressTextbox = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	By submitButton = By.name("sub");




	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/index.php");
		
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		
		String HomePageWelcomeMSG = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(HomePageWelcomeMSG, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']" )).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();


	}

	@Test
	public void TC_01_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextbox).sendKeys(customerName);
		
		driver.findElement(genderCheckbox).click();
		
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		
		driver.findElement(adddressTextbox).sendKeys(address);
		
		driver.findElement(cityTextbox).sendKeys(city);
		
		driver.findElement(stateTextbox).sendKeys(state);
		
		driver.findElement(pinTextbox).sendKeys(pin);
		
		driver.findElement(phoneTextbox).sendKeys(phone);
		
		driver.findElement(emailTextbox).sendKeys(email);
		
		driver.findElement(passwordTextbox).sendKeys(password);
		
		driver.findElement(submitButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//table[@id='customer']//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		
		// VERIFY OUTPUT DATA = INPUT DATA 
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
		
	}
	
	
	@Test
	public void TC_02_Edit_Customer() throws InterruptedException {
		
	
			
	}
	
	
	@Test
	public void TC_03_() throws InterruptedException {
		
		
		
				
	}
	
	

	@Test
	public void TC_04_ () throws InterruptedException {
		
				
	}

	
	@AfterTest
	public void afterTest() {
		

	}

}
