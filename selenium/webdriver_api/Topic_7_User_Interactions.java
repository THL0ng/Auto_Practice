package webdriver_api;

import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_7_User_Interactions {
	WebDriver driver;
	Actions action; 
	Select select;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
				
		System.out.println(driver.toString());	
		action = new Actions(driver);

	}


	@Test
	public void TC_01_Hover_Move_Mouse() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Men']"))).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Casual Shirts']")).click();
		Thread.sleep(2000);

		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Casual Shirts for Men']")).isDisplayed());
		Thread.sleep(2000);
	
	}
	
	
	@Test
	public void TC_02_Click_And_Hold() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li")); 
		
		int numberSize = numbers.size();
		System.out.println("Size before click/hold = " + numberSize); // = 12
		
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform(); // release : nhả chuột ra
		
		
		// KIỂM TRA XEM ĐÃ CHỌN ĐÚNG CHƯA 
		List <WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")); 
		System.out.println("Size before click/hold = " + selectedNumbers.size()); 

		for (WebElement number : selectedNumbers) {
			System.out.println(number.getText());
		}
		
		Assert.assertEquals(selectedNumbers.size(), 8);
		
			
	}
	
	@Test
	public void TC_03_Click_And_Hold_Random() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li")); 
		
		int numberSize = numbers.size();
		System.out.println("Size before click/hold = " + numberSize);
		
		// NHẤN XUỐNG VÀ GIỮ PHÍM CONTROL
		action.keyDown(Keys.CONTROL).perform();
		
		action.click(numbers.get(0))
		.click(numbers.get(4))
		.click(numbers.get(8))
		.click(numbers.get(10))
		.perform();
		
		
		// THẢ PHÍM CONTROL RA
		action.keyUp(Keys.CONTROL).perform();

		
		
		// KIỂM TRA XEM ĐÃ CHỌN ĐÚNG CHƯA 
		List <WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")); 
		System.out.println("Size before click/hold = " + selectedNumbers.size()); 

		for (WebElement number : selectedNumbers) {
			System.out.println(number.getText());
		}
		
		Assert.assertEquals(selectedNumbers.size(), 4);
		
		
	}
	
	
	@Test
	public void TC_04_Double_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		String demoText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		Assert.assertEquals(demoText, "Hello Automation Guys!");
				
	}
	
	@Test
	public void TC_05_Right_Click() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(findByXpath("//span[text()='right click me']")).perform();
		
		action.moveToElement(findByXpath("//span[text()='Quit']")).perform();
		
		Assert.assertTrue(findByXpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']").isDisplayed());
		
		action.click(findByXpath("//span[text()='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		driver.switchTo().alert().accept();
	}
	
	
	
	
	@Test
	public void TC_06_Drag_And_Drop() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = findByXpath("//div[@id='draggable']");
		WebElement targetCircle = findByXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		Assert.assertTrue(findByXpath("//div[@id='droptarget' and text()='You did great!']").isDisplayed());

		
	}
	
	
	
	public WebElement findByXpath (String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
