package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_10_UploadFile {
	WebDriver driver;
	
	// ĐƯỜNG DẪN TUYỆT ĐỐI
	//String pic_01Path = "C:\\Users\\ADMIN\\git\\Auto_Practice\\uploadFiles\\01.jpg";
	
	// ĐƯỜNG DẪN TƯƠNG ĐỐI--> MÁY KHÁC VẪN CHẠY DC
	String projectPath = System.getProperty("user.dir");
	String pic_01Path = projectPath + "\\uploadFiles\\01.jpg";
	String pic_02Path = projectPath + "\\uploadFiles\\02.jpeg";
	String pic_03Path = projectPath + "\\uploadFiles\\03.jpg";
	String pic_04Path = projectPath + "\\uploadFiles\\04.jpg";



	@BeforeTest
	public void beforeTest() {	
		
		/*System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
		driver = new ChromeDriver();*/
		
		System.setProperty("webdriver.gecko.driver",  projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	
	public void TC_01_Senkeys() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");	
		
		WebElement uploadFile = find("//input[@type='file']");
		uploadFile.sendKeys(pic_01Path);
	
		find("//table//button[@class='btn btn-primary start']").click();
		
		//Assert.assertTrue(find("//p[@class='name']//a[@title='01.jpg']").isDisplayed());
	
	}
	
	
	@Test
	public void TC_02_upload_Muti_Files() throws InterruptedException  {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFiles = find("//input[@type='file']");
		uploadFiles.sendKeys(pic_02Path + "\n" +  pic_03Path + "\n" + pic_04Path);
		
		List <WebElement> startButton = finds("//table//button[@class='btn btn-primary start']");
		for (WebElement start : startButton) {
			start.click();
			Thread.sleep(2000);
			
		}
	
	}
		
	public WebElement find (String xpath ) {
		return driver.findElement(By.xpath(xpath));
	}

	
	public List <WebElement> finds (String xpath ) {
		return driver.findElements (By.xpath(xpath));
	}

	
	@AfterTest
	public void afterTest() {
	
	}

}
