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

public class Topic_3_Element {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_() throws InterruptedException {

		driver.get("https://automationfc.github.io");

		// THAO TÁC VỚI 1 ELEMENT

		// TƯƠNG TÁC LÊN ELEMENT 1 LẦN THÌ KO CẦN KHAI BÁO BIẾN
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc@gmail.com");

		// TƯƠNG TÁC LÊN ELEMENT NHIỀU LẦN THÌ CẦN KHAI BÁO BIẾN
		WebElement EmailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));

		EmailTextBox.clear();

		EmailTextBox.sendKeys("automationfc@gmail.com");

		Assert.assertTrue(EmailTextBox.isDisplayed());

		// THAO TÁC VỚI NHIỀU ELEMENT
		// TƯƠNG TÁC LÊN ALL LINK Ở PAGE HIỆN TẠI

		List<WebElement> links = driver.findElements(By.xpath("//a"));

		// CÓ BAO NHIÊU LINK Ở PAGE HIỆN TẠI
		System.out.println("links size = " + links.size());

		// GET RA ALL TEXT CỦA LINKS
		for (WebElement link : links) {
			System.out.println("Text = " + link.getText());
		}

		
		//-------------------------------------------------------------------------------------------------------------
		
		// WEB ELEMENT METHOD (API)
		WebElement element = driver.findElement(By.cssSelector(""));

		// XÓA DỮ LIỆU TRONG TEXTBOX/TEXT AREA TRƯỚC KHI SENDKEYS
		element.clear();

		// NHẬP DỮ LIỆU TRONG TEXTBOX/TEXT AREA, DROPDOWN LIST (EDITABLE)
		element.sendKeys("");
		
		// CLICK VÀO LINK / BUTTON / CHECKBOX / RADIO BUTTON / IMAGE /DROPDOWN LIST ( CUSTOM)
		element.click();
		
		
		// GET ATTRIBUTE
		WebElement passwordTextbox = driver.findElement(By.id("password"));
		String passwordTextboxHint = passwordTextbox.getAttribute("placehoder");
		System.out.println(passwordTextboxHint);
		
		// TEST GIAO DIỆN NGƯỜI DÙNG GUI ( GRAPHIC USER INTERFACE) NHƯ FONT/ SIZE/ COLOR/ LOCATION / POSITION....
		WebElement buttonDisabled = driver.findElement(By.id("button-disabled"));
		String buttonFontSize = buttonDisabled.getCssValue("font-size");
		String buttonBackground = buttonDisabled.getCssValue("background-color");
		System.out.println(buttonFontSize);
		System.out.println(buttonBackground);
		
		
		// LẤY RA VỊ TRÍ SO VỚI ĐỘ PHÂN GIẢI MÀN HÌNH	
		buttonDisabled.getLocation();
		
		// LẤY RA KÍCH THƯỚC CHIỀU RỘNG CHIỀU CAO
		buttonDisabled.getSize();
		
		
		// KIỂM TRA 1 ELEMENT BẤT KÌ CÓ HIỂN THỊ (DISPLAY OR VISIBLE : NHÌN THẤY VÀ THAO TÁC DC) TRÊN PAGE HAY KO 
		WebElement userAvatar5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		Assert.assertFalse(userAvatar5.isDisplayed());
		
		
		// KIỂM TRA 1 ELEMENT CÓ DC PHÉP THAO TÁC HAY KO 
		Assert.assertFalse(buttonDisabled.isEnabled());
		
		
		// KIỂM TRA 1 ELEMENT DC CHỌN HAY CHƯA
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		Assert.assertFalse(under18Radio.isSelected());
		
		under18Radio.click();
		Assert.assertTrue(under18Radio.isSelected());

		
		// WORK CHO ELEMENT LÀ FORM 
		
		userAvatar5.submit();
		
				
		
		
		
		
		
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
