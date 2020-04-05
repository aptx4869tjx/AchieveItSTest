import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: AchieveItSTest
 * @className: RiskManagement
 * @author: 田家旭
 * @date: 2020-04-04 18:35
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RiskManagement extends BaseInfo {
    private static boolean flag = false;//是否按顺序全部执行
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "risk");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 新建风险测试
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/4 6:44 下午
     **/
    @Test
    @Order(1)
    void addRiskTest_1() throws InterruptedException {
        Thread.sleep(10000);
    }


    private static WebElement getRiskManagementItem() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("risk"));
    }

    private WebElement getOpenNewRiskButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("openNewRiskButton"));
    }

    private WebElement getCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("cancelSubmitButton"));
    }

    private WebElement getSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("submitButton"));
    }

    private WebElement getRiskTypeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskType"));
    }

    private WebElement getRiskStatusInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskStatus"));
    }

    private WebElement getRiskLevelInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskLevel"));
    }

    private WebElement getRiskResponsibleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskResponsible"));
    }

    private WebElement getRiskAffectInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskAffect"));
    }

    private WebElement getRiskTrackFreqInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskTrackFreq"));
    }

    private WebElement getRiskRelatedInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskRelated"));
    }

    private WebElement getRiskReactInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskReact"));
    }

    private WebElement getRiskStrategyInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskStrategy"));
    }

    private WebElement getRiskDescription() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskDescription"));
    }
}
