package practiceTwo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Do {
	private WebDriver driver;
	public Do(WebDriver driver){
		this.driver = driver;
	}
	
	
	public WebElement what(String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	
	public List<WebElement> whats(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
}
