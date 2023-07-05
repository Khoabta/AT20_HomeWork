import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class HomeWork_D7 {

    static WebDriver driver;
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
        String url1 = "https://demoqa.com/text-box";
        setUpBrowser(url1);
        BT1();
        Thread.sleep(2000);
        driver.quit();
        String url2 = "https://demo.guru99.com/test/login.html";
        setUpBrowser(url2);
        BT2();
        Thread.sleep(2000);
        driver.quit();
    }

    //Bài tập 1
    public static void BT1(){
        WebElement txtFullname = driver.findElement(By.id("userName"));
        txtFullname.sendKeys("Khoabta");
        WebElement txtEmail = driver.findElement(By.xpath("//input[@type='email']"));
        txtEmail.sendKeys("khoa@gmail.com");
        WebElement txtCurrentAdd = driver.findElement(By.id("currentAddress"));
        txtCurrentAdd.sendKeys("HCMC");
        WebElement txtPermanentAdd = driver.findElement(By.id("permanentAddress"));
        txtPermanentAdd.sendKeys("HCMC");
        WebElement btnSubmit = driver.findElement(By.id("submit"));
        Actions actions = new Actions(driver);
        actions.moveToElement(btnSubmit).click().perform();
        WebElement getEmail = driver.findElement(By.xpath("//p[@id='email']"));
        System.out.println(getEmail.getText());
    }

    //Bài tập 2
    public static void BT2(){
        WebElement txtUsername = driver.findElement(By.id("email"));
        txtUsername.sendKeys("khoa@gmail.com");
        WebElement txtPassword = driver.findElement(By.id("passwd"));
        txtPassword.sendKeys("123456");
        WebElement btnSubmit = driver.findElement(By.id("SubmitLogin"));
        btnSubmit.click();

        WebElement msgSuccess = driver.findElement(By.xpath("//h3[contains(text(), 'Successfully Logged in...')]"));
        String msg = msgSuccess.getText();
        if (msg.equals("Successfully Logged in...")){
            System.out.println("Pass");
        }
        else System.out.println("Fail");
    }
}
