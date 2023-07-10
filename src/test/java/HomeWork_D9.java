import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomeWork_D9 {

    static WebDriver driver;
    static String path = System.getProperty("user.dir");
    public static void setUpBrowser() throws InterruptedException {
        String browser = "Chrome";

        switch (browser){
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("invalid browser" + browser);
        }
        driver.manage().window().maximize();
    }

    public static void main(String[] args) throws InterruptedException {

        String url1 = "https://demo.guru99.com/test/drag_drop.html";
        setUpBrowser();
        driver.get(url1);
        BT1();
        Thread.sleep(5000);
        driver.quit();

//        String url2 = "https://gofile.io/uploadFiles";
//        setUpBrowser();
//        driver.get(url2);
//        BT2();
//        Thread.sleep(5000);
//        driver.quit();

        setUpBrowser();
        BT3();
        Thread.sleep(2000);
        driver.quit();
    }

    //Bài tập 1
    public static void BT1() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement btnBank = driver.findElement(By.xpath("//a[contains(text(), ' BANK ')]"));
        WebElement btnSales = driver.findElement(By.xpath("//a[contains(text(), ' SALES ')]"));
        WebElement btnValue = driver.findElement(By.xpath("(//a[contains(text(), ' 5000')])[1]"));
        WebElement debitAccount = driver.findElement(By.xpath("//ol[@id='bank']"));
        WebElement debitAmount = driver.findElement(By.xpath("//ol[@id='amt7']"));
        WebElement creditAccount = driver.findElement(By.xpath("//ol[@id='loan']"));
        WebElement creditAmount = driver.findElement(By.xpath("//ol[@id='amt8']"));

        actions.dragAndDrop(btnBank, debitAccount).build().perform();
        actions.dragAndDrop(btnValue, debitAmount).build().perform();
        actions.dragAndDrop(btnSales, creditAccount).build().perform();
        actions.dragAndDrop(btnValue, creditAmount).build().perform();

        WebElement result = driver.findElement(By.xpath("//a[contains(text(), 'Perfect!')]"));
        String msg = result.getText();
        if(msg.equals("Perfect!")){
            System.out.println("BT1 - Pass");
        }
        else System.out.println("BT1 - Fail");
    }

    //Bài tập 2
    public static void BT2() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement addFile = driver.findElement(By.xpath("//input[@type='file']"));
        executor.executeScript("arguments[0].removeAttribute('style')", addFile);
        //executor.executeScript("arguments[0].setAttribute('value', 'C:\\Users\\Admin\\IdeaProjects\\AT20_HomeWorkD6\\img\\duahau.jpg');", addFile);
        addFile.sendKeys(path + "/img/duahau.jpg");
    }

    //Bài tập 3
    public static void BT3(){

        driver.navigate().to("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width");
        WebElement iFrameResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
        driver.switchTo().frame(iFrameResult);
        WebElement iFrameExample = driver.findElement(By.xpath("//iframe[@title='Iframe Example']"));
        driver.switchTo().frame(iFrameExample);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement txtNoti = driver.findElement(By.xpath("//h1[contains(text(), 'This page is displayed in an iframe')]"));
        String expectedNoti = txtNoti.getText();
        System.out.println("Expected result: " +expectedNoti);
        if(expectedNoti.equals("This page is displayed in an iframe")){
            System.out.println("BT3 - Pass");
        }
        else System.out.println("BT3 - Fail");
    }
}
