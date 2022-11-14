package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_9_Javascript_Executor_Exercise {
	WebDriver driver;
	JavascriptExecutor jsExecutor ; 
	WebElement element;
	
	String username = "mngr454723";
	String password = "EbejepY";
	String customerID;	

	// INPUT OR OUTPUT DATA OF NEW CUSTOMER
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
		By genderCheckbox = By.xpath("//input[@value='m']");
		By genderCheckbox2 = By.name("gender");
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
		/*driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();*/
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;

	}

	
	public void TC_01_JS() throws InterruptedException {
		navigateToUrlByJS("http://live.techpanda.org/");
		
		// NHỮNG HÀM NÀO CẦN GET RA DỮ LIỆU THÌ PHẢI THÊM RETURN VÀO TRƯỚC ĐOẠN JS
		// GET/VERIFY DOMAIN
		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.techpanda.org");
		
		
		String liveURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveURL, "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS(("//a[text()='Mobile']"));
		
		
		hightlightElement(("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
		clickToElementByJS(("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
		
		String pageInnerText = getInnerText();
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS(("//a[text()='Customer Service']"));
		
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		Thread.sleep(2000);
		
		pageInnerText = getInnerText();
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		
		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "demo.guru99.com");
		

	}
	
	
	
	public void TC_04_Remove_attribute() throws InterruptedException {
		driver.get("https://demo.guru99.com/v4/index.php");

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		String HomePageWelcomeMSG = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(HomePageWelcomeMSG, "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextbox).sendKeys(customerName);

		driver.findElement(genderCheckbox).click();

		
		// REMOVE ATTRIBUTE TYPE = 'DATA' ( DATE OF BIRTH )
		removeAttributeInDOM("//input[@id='dob']", "type");
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
		Assert.assertEquals(customerName,driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());

		Assert.assertEquals(gender,driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		
		Assert.assertEquals(dateOfBirth,driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());

		Assert.assertEquals(address,driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());

		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());

		Assert.assertEquals(state,driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());

		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());

		Assert.assertEquals(phone,driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());

		Assert.assertEquals(email,driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		
		
		
	}
	
	@Test
	public void TC_05_Create_Account() throws InterruptedException {
		navigateToUrlByJS("http://live.techpanda.org/");
		
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		
		clickToElementByJS("//span[text()='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "Automation");
		sendkeyToElementByJS("//input[@id='lastname']", "FC");
		sendkeyToElementByJS("//input[@id='email_address']", "Automation" + random() + "@gmail.com");
		sendkeyToElementByJS("//input[@id='password']", "123555");
		sendkeyToElementByJS("//input[@id='confirmation']", "123555");

		
		clickToElementByJS("//button[@title='Register']");
		
		String registerMSG = getInnerText();
		Assert.assertTrue(registerMSG.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());

		

	}
			
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) throws InterruptedException {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		Thread.sleep(2000);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
		
	}

	public void scrollToElementOnTop(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element(locator));
	}

	public void scrollToElementOnDown(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element(locator));
	}

	public String getElementValidationMessage(String locator) {
		element = driver.findElement(By.xpath(locator));
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				element(locator));
		return status;
	}

	public WebElement element(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	

	
	@AfterTest
	public void afterTest() {
	
	}

}
