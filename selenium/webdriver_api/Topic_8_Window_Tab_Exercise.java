package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_8_Window_Tab_Exercise {
	WebDriver driver;
	String email = "automationfc.com@gmail.com";
	String password = "Automation000***"; 

	@BeforeTest
	public void beforeTest() {
		
		
		/*String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
		driver = new ChromeDriver();*/
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	/*@Test
	public void TC_01_Windows_Tab() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();

	
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");	
		driver.switchTo().window(parentID);
		
		
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		driver.switchTo().window(parentID);
		
		
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.switchTo().window(parentID);
		
		
		closeAllWindowsWithoutParent(parentID);
		
	}*/
	
	
	/*@Test
	public void TC_02_Windows_Tab() throws InterruptedException {
		driver.get("https://skills.kynaenglish.vn/");
		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//ul[@class='bottom']//following-sibling::div[@class='social']//img[@alt='facebook']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		
		driver.switchTo().window(parentID);

			
		driver.findElement(By.xpath("//ul[@class='bottom']//following-sibling::div[@class='social']//img[@alt='youtube']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		closeAllWindowsWithoutParent(parentID);

	
	}	*/
	
	
	/*@Test
	public void TC_03_Windows_Tab() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
	
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		driver.findElement(By.xpath("//div[@class='actions']/button[@class='button']")).click();
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='button']")).click();
		
		switchToWindowByTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		driver.switchTo().alert().accept();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	
	}	*/
	
	
	
	@Test
	public void TC_04_Windows_Tab() throws InterruptedException {
		driver.get("https://dictionary.cambridge.org/vi/");
		String parentID = driver.getWindowHandle();

		
		driver.findElement(By.xpath("//div[@class='pr hdib lpr-5']//span[text()='Đăng nhập']")).click();
		Thread.sleep(2000);

		
		switchToWindowByTitle("Login");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Thread.sleep(2000);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-textbox']//span[text()='This field is required']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-password']//span[text()='This field is required']")).isDisplayed());

		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys(email);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys(password);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Thread.sleep(2000);

		
		driver.switchTo().window(parentID);
		Thread.sleep(2000);

		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='tb lpl-2 cdo-username']")).getText(), "Automation FC");


		
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
