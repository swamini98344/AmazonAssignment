package Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class AmazonShoping extends BaseTest {

    @Test
    void testAmazon() throws InterruptedException {

        //openBrowser();

        // search functionality
        String name_of_product = "OnePlus 11R 5G (Galactic Silver, 16GB RAM, 256GB Storage)";

        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(name_of_product);
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        String product = driver.findElement(By.xpath("(//a/span[contains(text(),'OnePlus 11R 5G')])[1]")).getText().trim();
        Assert.assertEquals(product,name_of_product,"Correct Searching results are displayed");

        // filter functionality :price range

       driver.findElement(By.xpath("//input[@id='low-price']")).sendKeys("44000");
       driver.findElement(By.xpath("//input[@id='high-price']")).sendKeys("45000");
       driver.findElement(By.xpath("//span[@id='a-autoid-1']")).click();
       int price = Integer.parseInt(driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText().replace(",","").replace("₹","").trim());
       Assert.assertTrue(isInClosedRange(price,44000,45000));


        //driver.manage().window().maximize();

       String  parent_window = driver.getWindowHandle();
       driver.findElement(By.xpath("//a/span[text()='OnePlus 11R 5G (Galactic Silver, 16GB RAM, 256GB Storage)']")).click();
       Set<String> windows = driver.getWindowHandles();
       for (String window : windows){
           if(!(window.equalsIgnoreCase(parent_window)))
           {
               driver.switchTo().window(window);
               driver.manage().window().maximize();
               String cart = driver.findElement(By.xpath("//span[@id='productTitle']")).getText().trim();
               Assert.assertEquals(cart,name_of_product,"correct product added to the cart");
               driver.findElement(By.xpath("//div[@id='buyNow']/span//input")).click();
           }
        }


       driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("9665058249");
       driver.findElement(By.xpath("//input[@id='continue']")).click();
       driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Swamini@009");
       driver.findElement(By.xpath("//input[@id='signInSubmit']")).click(); // some time its tend to fail as Appplication is asking for captcha details

       driver.findElement(By.xpath("//a[@id='add-new-address-popover-link']")).click();

       // enter form

        driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']")).sendKeys("Swamini Patil");
        driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']")).sendKeys("9665058249");
        driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPostalCode']")).sendKeys("411057");
        driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']")).sendKeys("A25 Vision star");
        driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine2']")).sendKeys("Pune Highway");
        driver.findElement(By.xpath("//input[@id='address-ui-widgets-landmark']")).sendKeys("near wakad bridge");
        driver.findElement(By.xpath("//span[@id='address-ui-widgets-form-submit-button']/span/input")).click();
       /* driver.findElement(By.xpath("//input[@name='continue-bottom']")).click();
        driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();*/
        Thread.sleep(5000);

        //select payment method

        driver.findElement(By.xpath("//div/span[contains(text(),'Net Banking')]/../../../preceding-sibling::div//input")).click();
        driver.findElement(By.xpath("(//span[@class='a-button a-button-dropdown']//span[@class='a-dropdown-prompt'])[1]")).click();
        driver.findElement(By.xpath("//div[@class='a-popover-inner a-lgtbox-vertical-scroll']/ul/li/a[contains(text(),'HDFC Bank')]")).click();
        driver.findElement(By.xpath("//span[@id='orderSummaryPrimaryActionBtn']")).click();
        driver.quit();

    }

    public static boolean isInClosedRange(Integer number, Integer lowerBound, Integer upperBound) {

        return (lowerBound <= number && number <= upperBound);
    }

}
