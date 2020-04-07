import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @program: AchieveItSTest
 * @className: ProjectInfo
 * @author: 田家旭
 * @date: 2020-04-06 22:26
 **/

public class ProjectInfo extends BaseInfo {
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "base-info");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
//        driver.quit();
    }

    /**
     * 测试正常修改项目信息
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 10:09 上午
     **/
    @Order(1)
    @Test
    void editProjectInfoTest_1() throws InterruptedException {
        Thread.sleep(2000);
        WebElement projectNameInput = getProjectNameInput();
        projectNameInput.clear();
        projectNameInput.sendKeys("教务系统开发");
        WebElement scheduledDateInput = getScheduledDateInput();
        scheduledDateInput.clear();
        scheduledDateInput.sendKeys("2020 年 04 月 07 日");
        WebElement majorMilestoneInput = getMajorMilestoneInput();
        majorMilestoneInput.click();
        majorMilestoneInput.clear();
        majorMilestoneInput.sendKeys("主要的里程碑");

        WebElement businessFieldInput = getBusinessFieldInput();
        businessFieldInput.clear();
        businessFieldInput.sendKeys("主要的业务领域");
        WebElement mainFunctionInput = getMainFunctionInput();
        mainFunctionInput.clear();
        mainFunctionInput.sendKeys("主要的功能");
        getSubmitInfoButton().click();
        validateMessageBox("更新成功");
    }

    //测试不完整字段时，及时selenium清空了输入但依然存在原先的内容。。。这里采取手动测试
//
//    @Order(2)
//    @Test
//    void editProjectInfoTest_2() throws InterruptedException {
//        Thread.sleep(2000);
//        getProjectNameInput().clear();
//        getScheduledDateInput().clear();
//        Thread.sleep(1000);
////        getSubmitInfoButton().click();
////        validateMessageBox("请完整填写所需字段");
//    }


    //获取项目名称输入框
    private WebElement getProjectNameInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectName"));
    }

    //获取客户信息输入框
    private WebElement getClientIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("clientId"));
    }

    //获取预定时间选择器
    private WebElement getScheduledDateInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("scheduledDate"));
    }

    //获取交付时间选择器
    private WebElement getDeliveryDateInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("deliveryDate"));
    }

    //获取项目上级的选择框
    private WebElement getSuperiorInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectSuperior"));
    }

    //获取项目上级选择框的选项
    private List<WebElement> getSuperiorInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("projectSuperiorOption"));
    }

    //获取主要里程碑的输入框
    private WebElement getMajorMilestoneInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("majorMilestone"));
    }

    //获取业务领域的输入框
    private WebElement getBusinessFieldInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("businessField"));
    }

    //获取主要功能的输入框
    private WebElement getMainFunctionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("mainFunction"));
    }

    //获取修改信息按钮
    private WebElement getSubmitInfoButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("submitInfo"));
    }
}
