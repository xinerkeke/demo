package practiceTwo;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewTest {
	private ParseProperties prop, xpathProp;
	private WebDriver driver;
	// private InternetExplorerDriver driver;
	// FirefoxProfile firefoxprofile;
	// private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	Do find;

	@Parameters({ "testData", "testXpath" })
	@BeforeClass
	public void beforeClass(String testdata, String testXpath) {
		prop = new ParseProperties(System.getProperty("user.dir") + testdata);
		xpathProp = new ParseProperties(System.getProperty("user.dir") + testXpath);
		Broswers broswer = new Broswers(BrowserType.FIREFOX, prop);
		driver = broswer.driver;
		find = new Do(driver);
		driver.get(prop.getProperty("url"));
		doLogin();
	}

	@Test
	public void selectItemFromDropDownList() {
	}

	// @Test
	public void testChangeIframe() {

		Wait wai = new Wait(driver);
		wai.WaitForElementPresent(xpathProp.getProperty("JQUERYHOME"));
		// 调整到frame
		driver.switchTo().frame(find.what(xpathProp.getProperty("SLIDERIFRAME")));
		// 获取坐标点
		Point p = find.what(xpathProp.getProperty("SLIDER")).getLocation();
		System.out.println(p);
		// 移动元素
		Actions dragAction = new Actions(driver);
		dragAction.dragAndDropBy(find.what(xpathProp.getProperty("SLIDER")), p.x + 300, p.y).build().perform();
		p = find.what(xpathProp.getProperty("SLIDER")).getLocation();
		System.out.println(p);
		// 切换到原始的frame并验证֤
		driver.switchTo().defaultContent();
		find.what(xpathProp.getProperty("DRAGGABLE")).click();
	}

	// @Test
	public void test() {
	}

	/**
	 * 
	 */
	private void doLogin() {
		// 登录邮箱
		/*
		 * driver.findElement(By.xpath(xpathProp.getProperty("user"))).clear();
		 * driver.findElement(By.xpath(xpathProp.getProperty("user"))).sendKeys(
		 * prop.getProperty("username"));
		 * driver.findElement(By.xpath(xpathProp.getProperty("passwd"))).clear()
		 * ; driver.findElement(By.xpath(xpathProp.getProperty("passwd"))).
		 * sendKeys(prop.getProperty("password"));
		 * driver.findElement(By.xpath(xpathProp.getProperty("loginBtn"))).click
		 * ();
		 */
		find.what(xpathProp.getProperty("LOGIN")).click();
		find.what(xpathProp.getProperty("ACCOUNTLOGIN")).click();
		find.what(xpathProp.getProperty("LOGINNAMW")).clear();
		find.what(xpathProp.getProperty("LOGINNAMW")).sendKeys("15091739690");
		find.what(xpathProp.getProperty("LOGINPASSWD")).clear();
		find.what(xpathProp.getProperty("LOGINPASSWD")).sendKeys("keke..");
		find.what(xpathProp.getProperty("LOGINSUBMIT")).click();
	}

}
