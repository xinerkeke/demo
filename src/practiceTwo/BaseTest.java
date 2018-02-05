package practiceTwo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	private  ParseProperties prop;
	private  ParseProperties xpathProp;
	protected WebDriver driver = null;
	private  Do find;
	JCookies cookies;
	

	private static String testdata = "\\src\\practiceTwo\\test.properties";
	private static String testXpath = "\\src\\practiceTwo\\xpath.properties";

	@BeforeTest
	public void beforeTest() {
		prop = new ParseProperties(System.getProperty("user.dir") + testdata);
		xpathProp = new ParseProperties(System.getProperty("user.dir") + testXpath);
		Broswers broswer = new Broswers(BrowserType.FIREFOX, prop);
		driver = broswer.driver;
		find = new Do(driver);
		doLogin();
	}

	// @Parameters({ "testData", "testXpath" })
	// @BeforeClass
	// public void beforeTest(/*String testdata, String testXpath*/) {
	//
	// }

	public void doLogin() {
		cookies = new JCookies(driver);
		driver.get(prop.getProperty("url") + "/usda/adminLogin.jsp");
		Cookie tmp = cookies.readCookies();
		if (tmp == null) {
			driver.findElement(By.id("loginName")).clear();
			driver.findElement(By.id("loginName")).sendKeys("usda");
			driver.findElement(By.id("loginPassword")).clear();
			driver.findElement(By.id("loginPassword")).sendKeys("usda");
			driver.findElement(By.id("loginBtn")).click();
			cookies.writeCookies();
		} else {                
//		    driver.manage().deleteAllCookies();
//	        String name = "JSESSIONID";
//		    String value = "ABCB23F9A2FB9EC9E6C99B7C4304C4AB";
//		    String domain = "10.10.20.55";
//		    String path = "/usda";
//		    Date expiry = null;
//		    boolean isSecure = false;
//		    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
//		    driver.manage().addCookie(ck);
//		    name = "imoiaDqmLoginName";
//		    value = "";
//		    domain = "10.10.20.55";
//		    path = "/usda/";
//		    expiry = null;
//		    isSecure = false;
//		    Cookie ck1 = new Cookie(name, value, domain, path, expiry, isSecure);
//		    driver.manage().addCookie(ck1); 
			driver.get(prop.getProperty("url") + "/usda/home.do?projectId=test1010");
		}
	}
	
	
	 public int compare_date(String DATE1, String DATE2) {
	       
	       
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
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

}
