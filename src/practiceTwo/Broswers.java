package practiceTwo;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Broswers {
	public WebDriver driver = null;
	
	private String projectpath = System.getProperty("user.dir");
	
	public Broswers(String broswertype,ParseProperties pps){
		
		if(broswertype.equals("firefox")){
			System.setProperty(pps.getProperty("FFwebdriver"),
					pps.getProperty("FFdriverPath"));
			File firebug = new File(projectpath +"/tool/firebug-2.0.19-fx.xpi");
			File firefinder = new File(projectpath +"/tool/firefinder-1.4.1-fx.xpi");
			FirefoxProfile firefoxprofile = new FirefoxProfile();
			try {
				//加载firefinder,firebug插件
				firefoxprofile.addExtension(firefinder);
				firefoxprofile.addExtension(firebug);
				firefoxprofile.setPreference("extensions.firebug.currentVersion", "2.0.19");
				
				firefoxprofile.setPreference("webdriver.accept.untrusted.certs", "true");
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new FirefoxDriver(firefoxprofile);
			driver.manage().window().maximize();
		}
		
		if(broswertype.equals("iexplore")){
			//设置驱动
			System.setProperty(pps.getProperty("IEwebdriver"),
					pps.getProperty("IEdriverPath"));
			DesiredCapabilities caps = null;
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			caps.setCapability("ignoreZoomSettting", true);
			driver = new InternetExplorerDriver(caps);
		}
		
	}
}
