import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 总计7个测试用例
 * 2个自动化测试用例 5个手工测试用例（在其余信息不完整的情况添加项目）
 *
 * @program: AchieveItSTest
 * @className: AddProject
 * @author: 田家旭
 * @date: 2020-04-09 21:18
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddProject extends BaseInfo {
    private static boolean flag = false;

    @BeforeAll
    static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        //Firefox
//        System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BaseInfo.baseUrl);
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        //清除用户名的输入框
        username.clear();
        username.sendKeys("lisi");

        //清除密码输入框
        password.clear();
        password.sendKeys("123456");

        //登录按钮点击
        driver.findElement(By.cssSelector(".el-button")).click();
        Thread.sleep(5000);
    }

    @AfterAll
    static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试信息不完整时添加项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/9 9:40 下午
     **/
    @Order(1)
    @Test
    void newProjectTest_1() throws InterruptedException {
        flag = true;
        getNewProjectButton().click();
        Thread.sleep(5000);
        getSubmitInfoButton().click();
        validateMessageBox("请完整填写所需字段");
    }

    /**
     * 测试正常的添加项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/10 10:09 上午
     **/
    @Order(2)
    @Test
    void newProjectTest_2() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(5000);
        }
        getNewProjectButton().click();
        Thread.sleep(5000);
        getProjectClientIdInput().sendKeys("1000");
        getProjectNameInput().sendKeys("功能测试用例项目");
        getDeliveryDateInput().clear();
        getDeliveryDateInput().sendKeys("2020-10-01");
        getSuperiorInput().click();
        getSuperiorInputOptions().get(0).click();
        getMajorMilestoneInput().click();
        getMajorMilestoneInput().sendKeys("功能测试用例项目的主要里程碑");
        getMainTechniqueInput().sendKeys("功能测试用例项目的主要技术");
        getBusinessFieldInput().sendKeys("功能测试用例项目的业务领域");
        getMainFunctionInput().sendKeys("功能测试用例项目的主要功能");
        getSubmitInfoButton().click();
        validateMessageBox("立项成功");
    }


    //获取新建项目按钮
    private WebElement getNewProjectButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectButton"));
    }

    //获取客户Id输入框
    private WebElement getProjectClientIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectClientId"));
    }

    //获取项目名称输入框
    private WebElement getProjectNameInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectName"));
    }


    //获取预定时间选择器
    private WebElement getScheduledDateInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectScheduledDate"));
    }

    //获取交付时间选择器
    private WebElement getDeliveryDateInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectDeliveryDate"));
    }

    //获取项目上级的选择框
    private WebElement getSuperiorInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectSuperior"));
    }

    //获取项目上级选择框的选项
    private List<WebElement> getSuperiorInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("NewProjectSuperiorOption"));
    }

    //获取主要里程碑的输入框
    private WebElement getMajorMilestoneInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectMajorMilestone"));
    }

    //获取采用技术输入框
    private WebElement getMainTechniqueInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectMainTechnique"));
    }

    //获取业务领域的输入框
    private WebElement getBusinessFieldInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectBusinessField"));
    }

    //获取主要功能的输入框
    private WebElement getMainFunctionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectMainFunction"));
    }

    //获取添加项目按钮
    private WebElement getSubmitInfoButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newProjectSubmitButton"));
    }
}
