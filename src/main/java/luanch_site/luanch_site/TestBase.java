package luanch_site.luanch_site;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.TestUtil;



public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public static EventFiringWebDriver e_driver;
	//public static WebEventListener eventListener;
	public static String userDirectory=System.getProperty("user.dir");

	
	public TestBase() {
		try {
			prop = new Properties();
			InputStream input = new FileInputStream(
					userDirectory + File.separator+"src/test/resources"+"/config.properties");
			prop.load(input);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Initialization(String browsername) {
		browsername = prop.getProperty("Browser").toLowerCase();
		if (browsername.equals("chrome")) {
			String chromepath = userDirectory+prop.getProperty("resourcePath")+prop.getProperty("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
		} else if (browsername.equals("fireFox")) {
			String fireFoxpath = System.getProperty("fireFoxDriverPath");
			System.setProperty("webdriver.gecko.driver", fireFoxpath);
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		//eventListener = new WebEventListener();
		//e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().timeouts()
		.pageLoadTimeout(TestUtil.page_load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts()
		.implicitlyWait(TestUtil.Implicit_wait, TimeUnit.SECONDS);

		driver.get(prop.getProperty("URL"));
	}

	public static void InitializationOfSiebel(String browsername) {
		browsername = prop.getProperty("Browser").toLowerCase();
		if (browsername.equals("chrome")) {
			String Chropath = System.getProperty("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", Chropath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);
		} else if (browsername.equals("fireFox")) {
			String fireFoxpath = System.getProperty("fireFoxDriverPath");
			System.setProperty("webdriver.gecko.driver", fireFoxpath);
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		//eventListener = new WebEventListener();
		//e_driver.register(eventListener);
		driver = e_driver;

		driver.get(prop.getProperty("SiebelURLUAT4"));

		driver.manage().window().maximize();
		driver.manage()
		.timeouts()
		.pageLoadTimeout(TestUtil.page_load_TimeoutForSiebel,
				TimeUnit.SECONDS);
		driver.manage().timeouts()
		.implicitlyWait(TestUtil.Implicit_wait, TimeUnit.SECONDS);

	}

	public static void InitializationOfUAT2(String browsername) {
		browsername = prop.getProperty("Browser").toLowerCase();
		if (browsername.equals("chrome")) {
			String chromepath = userDirectory+prop.getProperty("resourcePath")+prop.getProperty("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
		} else if (browsername.equals("fireFox")) {
			String fireFoxpath = System.getProperty("fireFoxDriverPath");
			System.setProperty("webdriver.gecko.driver", fireFoxpath);
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		//eventListener = new WebEventListener();
		//e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().timeouts()
		.pageLoadTimeout(TestUtil.page_load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts()
		.implicitlyWait(TestUtil.Implicit_wait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URLuat2"));
	}


	//protected void waitForPageLoad(long timeout) {

	//	new WebDriverWait(driver, timeout).until(
	//			driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	//}
	public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(pageLoadCondition);
    }

}

