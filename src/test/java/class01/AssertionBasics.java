package class01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AssertionBasics {
    public static WebDriver driver;

    //precondition
    @BeforeMethod
    public void SetupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //    post-condition--> to  close the browser
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    //testcase
    //enter the username:Admin
    //enter the password:abracadabra
    //click login button
    //verify that the massage invalid is displayed
    //verify that password textBox displayed
    @Test
    public void invalidCredentials() {
        //locate the WebElement username and send keys
        WebElement username = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        username.sendKeys("Admin");
        //locate the WebElement password and send keys
        WebElement password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        password.sendKeys("abracadabra");
        //locate the login button and click login
        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='Submit']"));
        loginBtn.click();
        //verify the error message
        WebElement error = driver.findElement(By.xpath("//span[@id='spanMessage']"));
        String errorMsg = error.getText();
        //  check if the error message is correct
        String expectedError = "Invalid credential";

        //assert value
        Assert.assertEquals(errorMsg,expectedError);
        System.out.println("I am here");
        //finding the password again as it is discarded when we clicked on login button because
        //the DOM was refreshed
        //password textBox is displayed
        password=driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        boolean pswrdDisplayed=password.isDisplayed();
        // verify that the text box is displayed
        Assert.assertTrue(pswrdDisplayed);
    }
}
