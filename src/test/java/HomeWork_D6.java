import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomeWork_D6 {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        String browser = "Chrome";
        String url = "https://www.google.com/";

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
        driver.manage().window().setSize(new Dimension(750, 800));
        driver.navigate().to("https://tiki.vn/");
        Thread.sleep(2000);
        driver.quit();
    }
}
