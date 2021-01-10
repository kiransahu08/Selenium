package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility 
{
	
	public static void elementView(WebDriver driver,WebElement element)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	
	public static WebElement waitForElement(WebDriver driver,By locator)
	{
		return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	

	
}
