package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_8_Window_Tab {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Window() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		// TRẢ ID CỦA TAB ĐANG ĐỨNG
		String parentID = driver.getWindowHandle();
		System.out.println("parent ID = " + parentID );
		
		
		// TẤT CẢ ID CỦA TẤT CẢ CÁC TAB
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByID(parentID);
		
		Assert.assertEquals(driver.getTitle(), "Google");
		
		driver.switchTo().window(parentID);
		
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);		
		switchToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);	
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		closeAllWindowsWithoutParent(parentID);
		
	}
	
	
	public void switchToWindowByID (String parentID) {
		
		// ONLY 2 WINDOWS
		// LẤY RA TẤT CẢ ID ĐANG CÓ
		Set <String> allWindows = driver.getWindowHandles();
		
		// DÙNG VÒNG LẶP DUYỆT QUA TỪNG ID
		for (String runWindow : allWindows) {
			
			// KIỂM TRA ID NÀO KHÁC VỚI PARENT ID THÌ SWITCH QUA
			if (!runWindow.equals(parentID)) {
				
				// SWITCH QUA TAB/WINDOW ĐÓ
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	
	public void switchToWindowByTitle ( String ExpectedTitle) {
		
		// NHIỀU HƠN 2 CỬA SỔ
		// LẤY RA TẤT CẢ ID ĐANG CÓ 
		Set < String> allWindows = driver.getWindowHandles();
		System.out.println("All windows size = " + allWindows.size());
		
		// DÙNG VÒNG LẶP DUYỆT QUA TỪNG ID
		for ( String termID : allWindows) {
			System.out.println(" ID = " + termID);
			
			// SWITCH VÀO TỪNG ID TRƯỚC 
			driver.switchTo().window(termID);
			
			// GET RA TITLE ĐANG CÓ 
			String currentTitle = driver.getTitle();
			System.out.println("Title = " + currentTitle);
			
			// TITLE NÀO GIỐNG VỚI EXPECTED THÌ BREAK KHỎI VÒNG LẶP
			if (currentTitle.equals(ExpectedTitle)) {
				break; 
				
			}
		}
	}
	
	
	public boolean closeAllWindowsWithoutParent ( String parentID) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			
			// ID NÀO MÀ KHÁC VS PARENT THÌ SWITCH QUA
			if (!runWindow.equals(parentID)) {
				
					// SWITCH VÀO WINDOWS ĐÓ
					driver.switchTo().window(runWindow);
					driver.close();
			}
			
		}
			
		
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
		
		
	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
