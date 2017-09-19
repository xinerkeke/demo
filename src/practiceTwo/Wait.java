package practiceTwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	private WebDriver driver;
	
	public Wait(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void WaitForElementPresent(String locator) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}
	
	public void WaitForElementIsEnable(String locator) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
}
