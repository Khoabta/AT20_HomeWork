import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomeWork_D8 {

    static WebDriver driver;
    static String path = System.getProperty("user.dir");
    public static void setUpBrowser(String url) throws InterruptedException {
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
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void main(String[] args) throws InterruptedException {
        String url1 = "https://files.fm/";
        setUpBrowser(url1);
        BT1();
        Thread.sleep(4000);
        driver.quit();

        String url2 = "https://demo.guru99.com/test/upload/";
        setUpBrowser(url2);
        BT2();
        Thread.sleep(2000);
        driver.quit();

        String url3 = "https://demoqa.com/select-menu";
        setUpBrowser(url3);
        BT3();
        Thread.sleep(2000);
        driver.quit();
    }

    //Bài tập 1
    public static void BT1() throws InterruptedException {
        WebElement file = driver.findElement(By.xpath("//div[@id='uploadifive-file_upload']/input[2]"));
        file.sendKeys(path + "/img/duahau.jpg");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement btnUpload = driver.findElement(By.xpath("//div[@class='upload_button uploadify_start_upoad_button start_upload_button']"));
        executor.executeScript("arguments[0].click()", btnUpload);
        Thread.sleep(15000);

        WebElement msgSuccess = driver.findElement(By.xpath("//div[contains(text(), 'Upload completed. Send a link: ')]"));
        String msg = msgSuccess.getText();
        System.out.println("Expected result: " +msg);
        if (msg.equals("Upload completed. Send a link:")){
            System.out.println("Pass");
        }
        else System.out.println("Fail");

    }

    //Bài tập 2
    public static void BT2(){
        WebElement file = driver.findElement(By.xpath("//input[@type='file']"));
        file.sendKeys(path + "/img/duahau.jpg");
        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkBox.click();
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        btnSubmit.click();
    }

    //Bài tập 3
    public static void BT3() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        WebElement btnDropValue = driver.findElement(By.xpath("//div[contains(text(),'Select Option')]"));
        btnDropValue.click();
        WebElement selectValue = driver.findElement(By.xpath("//div[contains(text(), 'Another root option')]"));
        executor.executeScript("arguments[0].click()", selectValue);

        WebElement btnDropOne = driver.findElement(By.xpath("//div[contains(text(),'Select Title')]"));
        btnDropOne.click();
        Thread.sleep(1000);
        List<WebElement> selectOne = driver.findElements(By.xpath("//div[@tabindex='-1']"));
        int size = selectOne.size();
        Random random = new Random();
        int clickValue = random.nextInt(size);
        executor.executeScript("arguments[0].click()", selectOne.get(clickValue));

    }

}
