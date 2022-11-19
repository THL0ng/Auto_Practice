package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_05_DropdownList {
	WebDriver driver;
	Select select;
	
	Actions action;
	
	WebDriverWait waitExplicit;
	By numberAllItems = By.xpath("//ul[@id='number-menu']//li");
	
	@BeforeTest
	public void beforeTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver" , projectPath + "\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			
		waitExplicit = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Default_DropdownList() throws InterruptedException {
		driver.get("https://egov.danang.gov.vn/reg");
		
		// THAO TÁC VỚI CITY DROPDOWN LIST
		select = new Select(driver.findElement(By.name("tinhThuongTru")));
	
		
		
		// KIỂM TRA DROPDOWN NÀY CÓ ĐƯỢC PHÉP CHỌN NHIỀU HAY KO
		boolean cityDropdownstatus = select.isMultiple();
		System.out.println("city status = " + cityDropdownstatus);
		Assert.assertFalse(cityDropdownstatus);
		// DC PHÉP CHỌN NHIỀU
		//Assert.assertTrue(cityDropdownstatus);
		
		
		
		
		/*<select id="thuongtru_quanhuyen" class="egov-select" style="width:99%; display:block;" name="huyenThuongTru">
			<option id="Chọn" value="">Huyện/Quận</option>  --> 0
			<option value="7238">quận Liên Chiểu</option> --> 1
			<option value="7244">quận Thanh Khê</option> --> 2
			<option value="7255">quận Hải Châu</option> --> 3
			<option value="7269">quận Sơn Trà</option> --> 4
			<option value="7277">quận Ngũ Hành Sơn</option> --> 5
			<option value="7282">quận Cẩm Lệ</option> --> 6
			<option value="7289">huyện Hòa Vang</option> --> 7
			<option value="7301">huyện Hoàng Sa</option> --> 8
		</select>*/
		
		
		select.selectByIndex(5);
		Thread.sleep(3000);
		//VERIFY KẾT QUẢ ĐÚNG KO 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");
		
		
		select.selectByValue("11803");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Cà Mau");


		
		select.selectByVisibleText("tỉnh Lạng Sơn");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Lạng Sơn");
		
		// ĐẾM XEM TRONG DROPDOWN LIST CÓ BAO NHIÊU ITEM
		int cityNumber = select.getOptions().size();
		System.out.println(" Tất cả tình thành = " +  cityNumber);
		Assert.assertEquals(cityNumber, 66);

		
		// IN RA TẤT CẢ GIÁ TRỊ NẰM TRONG DROPDOWN
		List <WebElement> allOptions = select.getOptions();
		for (WebElement option :allOptions ) {
			System.out.println(option.getText());
		}
		
		

		

	}

	@Test
	public void TC_02_Custom_DropdownList_JQUERY() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// 1- CLICK VÀO THẺ CHỨA DROPDOWN LIST ĐỂ NÓ XỔ RA HẾT ITEMS
		driver.findElement(By.xpath("//span[@id='number-button']")).click();
		
		// KHAI BÁO 1 LIST WEBELEMENT CHỨA ALL ITEMS BÊN TRONG
		List <WebElement> allItems = driver.findElements(numberAllItems);
		
		// WAIT CHO ĐẾN KHI TẤT CẢ CÁC ITEMS ĐƯỢC XUẤT HIỆN Ở TRONG DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(numberAllItems));
		
		// GET TEXT TỪNG ITEMS SAU ĐÓ SO SÁNH VS ITEMS MÌNH CẦN CHỌN
		for (WebElement item : allItems) {
			System.out.println(item.getText());
			
		// KIỂM TRA ITEM NÀO ĐÚNG VỚI CÁI MÌNH CẦN THÌ CLICK VÀO
			if (item.getText().equals("5")) {
				item.click();
				break;
			}
		}
		// KIỂM TRA ITEM ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		boolean status = driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed();
		System.out.println("Status = " + status );
		Assert.assertTrue(status);

	}
	
	
	// CÁCH VIẾT THỨ 2 RÚT GỌN
	@Test
	public void TC_03_Custom_DropdownList_JQUERY() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// span[@id='number-button']
		// ul[@id='number-menu']/li
		//  5 
		
		
		// CLICK VÀO 5 VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//span[@id='number-button']" , "//ul[@id='number-menu']/li" , "5");	
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));	
		Thread.sleep(2000);
		
		// CLICK VÀO 10 VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//span[@id='number-button']" , "//ul[@id='number-menu']/li" , "10");	
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']"));	
		Thread.sleep(2000);
		
		// CLICK VÀO 3 VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//span[@id='number-button']" , "//ul[@id='number-menu']/li" , "3");	
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']"));	
		Thread.sleep(2000);
				
		
	}
	
	
	// TẠO RA HÀM DÙNG CHUNG 
	public void selectItemInCustomDropdown(String parentXpath, String allItemsXpath, String expectedText) {
		// 1- CLICK VÀO THẺ CHỨA DROPDOWN LIST ĐỂ NÓ XỔ RA HẾT ITEMS
		driver.findElement(By.xpath(parentXpath)).click();

		// KHAI BÁO 1 LIST WEBELEMENT CHỨA ALL ITEMS BÊN TRONG
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));

		// WAIT CHO ĐẾN KHI TẤT CẢ CÁC ITEMS ĐƯỢC XUẤT HIỆN Ở TRONG DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		// GET TEXT TỪNG ITEMS SAU ĐÓ SO SÁNH VS ITEMS MÌNH CẦN CHỌN
		for (WebElement item : allItems) {
			System.out.println(item.getText());

			// KIỂM TRA ITEM NÀO ĐÚNG VỚI CÁI MÌNH CẦN THÌ CLICK VÀO
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}

	}
	
	
	public boolean isElementDisplayed (String xpathhLocator) {
		WebElement element = driver.findElement(By.xpath(xpathhLocator));
		if (element.isDisplayed()) {
			return true;
		}	else {
			return false;
		
		}
	}
	
	
	@Test
	public void TC_04_Custom_DropdownList_REACT() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		
		
		// CLICK VÀO CHRISTIAN VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i" , "//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span" , "Christian");	
		Assert.assertTrue(isElementDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[text()='Christian']"));	
		Thread.sleep(2000);
		
		// CLICK VÀO JENNY HESS VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i" , "//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span" , "Jenny Hess");	
		Assert.assertTrue(isElementDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[text()='Jenny Hess']"));
		Thread.sleep(2000);

		
		
		// // CLICK VÀO Stevie Feliciano VÀ KIỂM TRA NÓ ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
		selectItemInCustomDropdown("//div[@id='types-selection']//i[@class='dropdown icon']" , "//div[@class='visible menu transition']/div" , "Stevie Feliciano");	
		Assert.assertTrue(isElementDisplayed("//div[@class='divider text' and text()='Stevie Feliciano']"));
		Thread.sleep(2000);

		
		
	}
	

	
	@Test
	public void TC_05_Custom_DropdownList_EDIABLE() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		inputItemInCustomDropdown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']" , "//input[@class= 'search']", "American Samoa");	
		Assert.assertTrue(isElementDisplayed("//div[contains(@class, 'search selection')]/div[text()='American Samoa']"));	
				
	}
	
	public void inputItemInCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		// 1- CLICK VÀO THẺ CHỨA DROPDOWN LIST ĐỂ NÓ XỔ RA HẾT ITEMS
		driver.findElement(By.xpath(parentXpath)).click();
		
		
		// 2- INPUT TEXT VÀO TEXTBOX 
		driver.findElement(By.xpath(inputXpath)).sendKeys(expectedText);
		
		
		// 3- TRUYỀN PHÍM ENTER VÀO INPUT TEXT
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();

	}
	

	@AfterTest
	public void afterTest() {

	}

}
