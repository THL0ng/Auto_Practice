package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_3_Element_Exercise {
	WebDriver driver;

	By Email = By.xpath("//input[@id='mail']");
	By Age = By.xpath("//input[@id='under_18']");
	By Education = By.xpath("//div[@class='container']//textarea[@id='edu']");
	By Job1 = By.xpath("//select[@id='job1']");
	By Job2 = By.xpath("//select[@id='job2']");
	By Dev_checkbox = By.xpath("//input[@id='development']");
	By Slider = By.xpath("//input[@id='slider-1']");
	By Pwd_Disabled = By.xpath("//input[@id='disable_password']");
	By RadioButton_Disabled = By.xpath("//input[@id='radio-disabled']");
	By Biography = By.xpath("//textarea[@id='bio']");
	By JobRole03 = By.xpath("//select[@id='job3']");
	By Interest = By.xpath("//input[@id='check-disbaled']");
	By Slider2 = By.xpath("//input[@id='slider-2']");
	By Language = By.xpath("//input[@id='java']");


	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Check_IsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement Email = driver.findElement(By.xpath("//input[@id='mail']"));
		Assert.assertTrue(Email.isDisplayed());
		if (Email.isDisplayed()) {
			Email.sendKeys("Automation testing");

		}

		System.out.println("Email : " + "Element is Display");

		WebElement Age = driver.findElement(By.xpath("//input[@id='under_18']"));
		Assert.assertTrue(Age.isDisplayed());
		if (Age.isDisplayed()) {
			Age.click();

		}

		System.out.println("Age : " + "Element is Display");

		WebElement Education = driver.findElement(By.xpath("//div[@class='container']//textarea[@id='edu']"));
		Assert.assertTrue(Education.isDisplayed());
		if (Education.isDisplayed()) {
			Education.sendKeys("Automation testing");

		}

		System.out.println("Education : " + "Element is Display");

		WebElement User5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		Assert.assertFalse(User5.isDisplayed());

		System.out.println("User5 : " + "Element is not Display");

	}

	@Test
	public void TC_02_Check_Enebled_Disabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement EmailTextbox = driver.findElement(Email);
		Assert.assertTrue(EmailTextbox.isEnabled());
		if (EmailTextbox.isEnabled()) {
			System.out.println("Element [" + Email + "] is Enabled ");

		} else {
			System.out.println("Element [" + Email + "] is Disabled ");

		}

		WebElement AgeTextbox = driver.findElement(Age);
		Assert.assertTrue(AgeTextbox.isEnabled());
		if (AgeTextbox.isEnabled()) {
			System.out.println("Element [" + Age + "] is Enabled ");

		} else {
			System.out.println("Element [" + Age + "] is Disabled ");

		}

		WebElement EducationTextbox = driver.findElement(Education);
		Assert.assertTrue(EducationTextbox.isEnabled());
		if (EducationTextbox.isEnabled()) {
			System.out.println("Element [" + Education + "] is Enabled ");

		} else {
			System.out.println("Element [" + Education + "] is Disabled ");

		}

		WebElement Job1Textbox = driver.findElement(Job1);
		Assert.assertTrue(Job1Textbox.isEnabled());
		if (Job1Textbox.isEnabled()) {
			System.out.println("Element [" + Job1 + "] is Enabled ");

		} else {
			System.out.println("Element [" + Job1 + "] is Disabled ");

		}

		WebElement Job2Textbox = driver.findElement(Job2);
		Assert.assertTrue(Job2Textbox.isEnabled());
		if (Job2Textbox.isEnabled()) {
			System.out.println("Element [" + Job2 + "] is Enabled ");

		} else {
			System.out.println("Element [" + Job2 + "] is Disabled ");

		}

		WebElement Devcheckbox = driver.findElement(Dev_checkbox);
		Assert.assertTrue(Devcheckbox.isEnabled());
		if (Devcheckbox.isEnabled()) {
			System.out.println("Element [" + Dev_checkbox + "] is Enabled ");

		} else {
			System.out.println("Element [" + Dev_checkbox + "] is Disabled ");

		}

		WebElement SliderTextbox = driver.findElement(Slider);
		Assert.assertTrue(SliderTextbox.isEnabled());
		if (SliderTextbox.isEnabled()) {
			System.out.println("Element [" + Slider + "] is Enabled ");

		} else {
			System.out.println("Element [" + Slider + "] is Disabled ");

		}

		WebElement Disabled_Pwd = driver.findElement(Pwd_Disabled);
		Assert.assertFalse(Disabled_Pwd.isEnabled());
		if (Disabled_Pwd.isEnabled()) {
			System.out.println("Element [" + Pwd_Disabled + "] is Enabled ");

		} else {
			System.out.println("Element [" + Pwd_Disabled + "] is Disabled ");

		}

		WebElement Age_Radiobutton_Disabled = driver.findElement(RadioButton_Disabled);
		Assert.assertFalse(Age_Radiobutton_Disabled.isEnabled());
		if (Age_Radiobutton_Disabled.isEnabled()) {
			System.out.println("Element [" + RadioButton_Disabled + "] is Enabled ");

		} else {
			System.out.println("Element [" + RadioButton_Disabled + "] is Disabled ");

		}

		WebElement BiographyButton = driver.findElement(Biography);
		Assert.assertFalse(BiographyButton.isEnabled());
		if (BiographyButton.isEnabled()) {
			System.out.println("Element [" + Biography + "] is Enabled ");

		} else {
			System.out.println("Element [" + Biography + "] is Disabled ");

		}

		WebElement JobRoleTextbox = driver.findElement(JobRole03);
		Assert.assertFalse(JobRoleTextbox.isEnabled());
		if (JobRoleTextbox.isEnabled()) {
			System.out.println("Element [" + JobRole03 + "] is Enabled ");

		} else {
			System.out.println("Element [" + JobRole03 + "] is Disabled ");

		}

		WebElement InterestCheckbox = driver.findElement(Interest);
		Assert.assertFalse(InterestCheckbox.isEnabled());
		if (InterestCheckbox.isEnabled()) {
			System.out.println("Element [" + Interest + "] is Enabled ");

		} else {
			System.out.println("Element [" + Interest + "] is Disabled ");

		}

		WebElement Slider2Button = driver.findElement(Slider2);
		Assert.assertFalse(Slider2Button.isEnabled());
		if (Slider2Button.isEnabled()) {
			System.out.println("Element [" + Slider2 + "] is Enabled ");

		} else {
			System.out.println("Element [" + Slider2 + "] is Disabled ");

		}

	}
	
	@Test
	public void TC_03_Check_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement AgeTextbox = driver.findElement(Age);	
		AgeTextbox.click();
		Assert.assertTrue(AgeTextbox.isSelected());
		
		WebElement LanguageTextbox = driver.findElement(Language);	
		LanguageTextbox.click();
		Assert.assertTrue(LanguageTextbox.isSelected());
		
		
	}
	
	
	
	

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
