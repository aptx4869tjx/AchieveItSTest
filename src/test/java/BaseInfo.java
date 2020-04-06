import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @program: AchieveItSTest
 * @className: BaseInfo
 * @author: 田家旭
 * @date: 2020-03-26 21:32
 **/

public class BaseInfo {
    public static String chromeDriverPath = "/usr/local/bin/chromedriver";
    public static String firefoxDriverPath = "/usr/local/bin/geckodriver";
    public static String baseUrl = "http://localhost:9527/";
    public static WebDriver driver;

    //项目id输入
    public static WebElement getProjectIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectIdInput"));
    }

    //项目查询按钮
    public static WebElement getSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("searchButton"));
    }

    //获取进入项目按钮
    public static WebElement getEntryButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.className("el-button--text"));
    }

    public static void setUp(String projectIdTest,String itemName) throws InterruptedException {
        //Chrome
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        //Firefox
//        System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BaseInfo.baseUrl);
        //没有导航到项目列表页面，则需要登录
        if (driver.getCurrentUrl().contains("login")) {
            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            //清除用户名的输入框
            username.clear();
            username.sendKeys("wangwu");

            //清除密码输入框
            password.clear();
            password.sendKeys("123456");

            //登录按钮点击
            driver.findElement(By.cssSelector(".el-button")).click();

            WebElement projectIdInput = getProjectIdInput();
            projectIdInput.click();
            projectIdInput.clear();
            projectIdInput.sendKeys(projectIdTest);
            WebElement searchButton = getSearchButton();
            searchButton.click();
            //等待查询结果
            Thread.sleep(5000);
            WebElement entryButton = getEntryButton();
            //进入选择的项目
            entryButton.click();
            Thread.sleep(3000);
            //进入选择的模块
            WebElement item = driver.findElement(By.name(itemName));
            item.click();
            Thread.sleep(2000);
        }
    }
}
