import com.sun.source.tree.IfTree;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomeWork_D10 {

    static WebDriver driver;
    static WebDriverWait wait;
    public static String baseURL = "https://tiki.vn";
    static String path = System.getProperty("user.dir");
    @Parameters({"browser"})
    @BeforeMethod
    public static void setUpBrowser(String browser) throws InterruptedException {

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
        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test
    public static void BT1() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement txtSearch = driver.findElement(By.xpath("//input[@type='text']"));
        txtSearch.sendKeys("Laptop");
        WebElement btnSearch = driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']"));
        btnSearch.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement firstItem = driver.findElement(By.xpath("(//span[@class='style__StyledItem-sc-qg694h-0 eLUkQI'])[1]"));
        executor.executeScript("arguments[0].click()", firstItem);

        WebElement txtBrand = driver.findElement(By.xpath("//td[contains(text(), 'Thương hiệu')]"));
        WebElement txtBrandOrigin = driver.findElement(By.xpath("//td[contains(text(), 'Xuất xứ thương hiệu')]"));
        WebElement txtOrigin = driver.findElement(By.xpath("//td[normalize-space(text())='Xuất xứ']")); //Tìm text xpath chính xác

        Assert.assertEquals("Thương hiệu", txtBrand.getText());
        Assert.assertEquals("Xuất xứ thương hiệu", txtBrandOrigin.getText());
        Assert.assertEquals("Xuất xứ", txtOrigin.getText());

    }

    @Test
    public static void BT2(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement txtSearch = driver.findElement(By.xpath("//input[@type='text']"));
        txtSearch.sendKeys("Sữa tươi không đường");
        WebElement btnSearch = driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']"));
        btnSearch.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement secondItem = driver.findElement(By.xpath("(//span[@class='style__StyledItem-sc-qg694h-0 eLUkQI'])[2]"));
        executor.executeScript("arguments[0].click()", secondItem);

        WebElement txtBrand = driver.findElement(By.xpath("//td[contains(text(), 'Thương hiệu')]"));
        WebElement txtBrandOrigin = driver.findElement(By.xpath("//td[contains(text(), 'Xuất xứ thương hiệu')]"));
        WebElement txtExpiredDate = driver.findElement(By.xpath("//td[contains(text(), 'Hạn sử dụng')]"));
        WebElement txtGuaranty = driver.findElement(By.xpath("//td[contains(text(), 'Sản phẩm có được bảo hành không?')]"));
        WebElement txtOrigin = driver.findElement(By.xpath("//td[normalize-space(text())='Xuất xứ']"));

        Assert.assertEquals("Thương hiệu", txtBrand.getText());
        Assert.assertEquals("Xuất xứ thương hiệu", txtBrandOrigin.getText());
        Assert.assertEquals("Hạn sử dụng", txtExpiredDate.getText());
        Assert.assertEquals("Sản phẩm có được bảo hành không?", txtGuaranty.getText());
        Assert.assertEquals("Xuất xứ", txtOrigin.getText());
    }


    @AfterMethod
    public static void quitBrowser(){
        driver.quit();
    }
}
