import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 总计5个测试用例
 * 5个自动化测试用例 0个手工测试用例
 *
 * @program: AchieveItSTest
 * @className: LoginTest
 * @author: 田家旭
 * @date: 2020-03-17 18:10
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private static WebDriver driver;
    private static boolean flag = false;//是否按顺序全部执行

    @BeforeAll
    public static void setUp() {
        //Chrome
        System.setProperty("webdriver.chrome.driver", BaseInfo.chromeDriverPath);
        driver = new ChromeDriver();

        //Firefox
//        System.setProperty("webdriver.gecko.driver",BaseInfo.firefoxDriverPath);
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BaseInfo.baseUrl);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 密码用户名均正确
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/18 1:35 下午
     **/
    @Order(1)
    @Test
    void loginTest_1() throws InterruptedException {
        flag = true;
        System.out.println(driver.getCurrentUrl());
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        //清除用户名的输入框
        username.clear();
        username.sendKeys("wangwu");

        //清除密码输入框
        password.clear();
        password.sendKeys("123456");

//        Thread.sleep(2000);
        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();
        //退出按钮点击,可能需要修改
        //TODO
        driver.findElement(By.name("exit")).click();
    }

    /**
     * 密码错误，用户名存在
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/18 1:35 下午
     **/
    @Order(2)
    @Test
    void loginTest_2() throws InterruptedException {
        //如果顺序全部执行则需要等待一段时间，便于查看
        if (flag) {
            Thread.sleep(2000);
            driver.navigate().refresh();
        }
        WebElement username = driver.findElement(By.name("username"));
        //清除用户名的输入框
        username.clear();
        username.sendKeys("wangwu");
        driver.findElement(By.name("password")).sendKeys("12345634");

        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();

        WebElement message = driver.findElement(By.className("el-message__content"));
        while (message.getText() == null || message.getText().equals("")) {
            Thread.sleep(500);
        }

        //比对错误信息，开发过程可能更改
        Assertions.assertEquals("用户名或密码错误", message.getText());

    }

    /**
     * 用户名存在，密码为空
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/26 9:58 下午
     **/
    @Order(3)
    @Test
    void loginTest_3() throws InterruptedException {
        //如果顺序全部执行则需要等待一段时间，便于查看
        if (flag) {
            Thread.sleep(2000);
            driver.navigate().refresh();

        }
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        //清除用户名的输入框
        username.clear();
        username.sendKeys("wangwu");
        password.clear();
        password.sendKeys("");
        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();
        Thread.sleep(1000);

        Assertions.assertEquals("密码不能为空", driver.findElement(By.className("el-form-item__error")).getText());

    }

    /**
     * 登录用户不存在
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/18 1:51 下午
     **/
    @Order(4)
    @Test
    void loginTest_4() throws InterruptedException {
        if (flag) {
            Thread.sleep(2000);
            driver.navigate().refresh();

        }
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        //清除用户名的输入框
        username.clear();
        username.sendKeys("zhangsanzzzz");

        password.clear();
        password.sendKeys("123456");
        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();
        WebElement message = driver.findElement(By.className("el-message__content"));
        while (message.getText() == null || message.getText().equals("")) {
            Thread.sleep(500);
        }
        //比对错误信息,开发过程可能更改
        Assertions.assertEquals("用户名或密码错误", message.getText());
    }

    /**
     * 用户名为空
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/26 10:10 下午
     **/
    @Order(5)
    @Test
    void loginTest_5() throws InterruptedException {
        if (flag) {
            Thread.sleep(2000);
            driver.navigate().refresh();

        }
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        //清除用户名的输入框
        username.clear();

        password.clear();
        password.sendKeys("123456");

        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();
        Thread.sleep(1000);

        //比对错误信息,开发过程可能更改
        Assertions.assertEquals("用户名不能为空", driver.findElement(By.className("el-form-item__error")).getText());
    }


}
