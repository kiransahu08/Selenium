package stepDefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import helper.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSearchProduct 
{
	public WebDriver driver;

	// Implemented hook which will run before running scenario
	@Before
	public void setup() {
		System.out.println("**** Running Setup ****");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("**** Browser is up and running ****");

	}
	
	// Implement hook which will run after scenario
	@After
	public void tearDown() 
	{
		System.out.println("**** Running Teardown ****");
		
		driver.quit();
		
		System.out.println("**** Session Closed ****");

	}

	String laptopName;

	@When("Open www.amazon.co.uk")
	public void open_www_amazon_co_uk() 
	{
		// Opening application
		driver.get("http://www.amazon.co.uk");
	}

	@Then("From the hamburger menu in the upper left-hand corner select Electronics & Computers")
	public void from_the_hamburger_menu_in_the_upper_left_hand_corner_select_electronics_computers() 
	{
		// accepting cookies 
		if (driver.findElements(By.xpath("//input[contains(@name,'accept')]")).size() > 0) {
			System.out.println("Cookies accepted");
			driver.findElement(By.xpath("//input[contains(@name,'accept')]")).click();
		}
		
		driver.findElement(By.xpath("//a[@id='nav-hamburger-menu']//i")).click();
		
		// scrolling for element for view
		Utility.elementView(driver, driver.findElement(By.xpath("//div[text()='Electronics & Computers']")));
		
		driver.findElement(By.xpath("//div[text()='Electronics & Computers']")).click();
	}

	@Then("From the following menu select Laptops")
	public void from_the_following_menu_select_laptops() 
	{
		// scrolling for element for view
		Utility.elementView(driver, driver.findElement(By.xpath("//a[text()='Laptops']")));
	
		driver.findElement(By.xpath("//a[text()='Laptops']")).click();

	}

	@Then("Select Display Size {int}-{int}")
	public void select_display_size(Integer int1, Integer int2) 
	{
		driver.findElement(By.xpath("//span[text()='15 to 16 in']")).click();
	}

	@Then("Select CPU Type -Intel Core i5")
	public void select_cpu_type_intel_core_i5() 
	{
		driver.findElement(By.xpath("//span[text()='Intel Core i5']")).click();
	}

	@Then("Select Storage Type SSD")
	public void select_storage_type_ssd() 
	{
		driver.findElement(By.xpath("//span[text()='SSD']")).click();

	}

	@Then("the result will be filtered.")
	public void the_result_will_be_filtered() 
	{
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'results')]")).isDisplayed());

	}

	@Then("Select a five starred Laptop with the lowest price")
	public void select_a_five_starred_laptop_with_the_lowest_price() 
	{

		new Select(Utility.waitForElement(driver, By.xpath("//select[contains(@id,'sort-select')]")))
				.selectByVisibleText("Price: Low to High");
		
		laptopName = driver.findElement(By.xpath("//span[text()='5.0 out of 5 stars']//preceding::span[1]")).getText();
		
		System.out.println("Laptop Name in Product Listing Page: " + laptopName);
		
		driver.findElement(By.xpath("//span[text()='5.0 out of 5 stars']//preceding::span[1]")).click();
	}

	@Then("Once the details page for the laptop is opened")
	public void once_the_details_page_for_the_laptop_is_opened() 
	{
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='imgTagWrapperId']//img")).isDisplayed());
	}

	@Then("verify it's the selected laptop")
	public void verify_it_s_the_selected_laptop() 
	{
		String laptopNameFromUI = driver.findElement(By.xpath("//h1[contains(@class,'size-large')]")).getText();
	
		System.out.println("Laptop Name in Product Description Page: " + laptopNameFromUI);
		
		Assert.assertTrue(laptopNameFromUI.contains(laptopName));
		
	}

}
