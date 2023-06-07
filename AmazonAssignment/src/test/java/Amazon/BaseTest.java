package Amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public class BaseTest {

    public WebDriver driver;


    @BeforeMethod
    public void openBrowser() {

        ChromeOptions o= new ChromeOptions();
        o.addArguments("−−incognito");
        DesiredCapabilities c = new DesiredCapabilities();
        c.setBrowserName("chrome");
        c.setCapability(ChromeOptions.CAPABILITY, o);
        driver = new ChromeDriver(o);
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.in/");
        driver.manage().window().fullscreen();
    }

}
