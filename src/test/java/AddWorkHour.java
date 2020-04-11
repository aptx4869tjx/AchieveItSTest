import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 总计5个测试用例
 * 1个自动化测试用例 4个手工测试用例（1.由于功能名称活动名称元素id在不同浏览器打开是变化的，同时采用xpath，cssSelector也无法定位，所以正常添加工时信息采取手工测试；2.编辑工时信息；3.撤销工时信息；4.添加工时信息点击取消按钮）
 * 添加工时信息测试
 *
 * @program: AchieveItSTest
 * @className: WorkHour
 * @author: 田家旭
 * @date: 2020-04-08 18:14
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddWorkHour extends BaseInfo {
    private static String projectIdTest = "12345678901";//测试的项目的projectId
    private static boolean flag = false;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        //使用项目测试员工账号登录
        setUp(projectIdTest, "work-hour", "testUser80", "123456");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试工时信息不完整提交
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/8 9:30 下午
     **/
    @Order(1)
    @Test
    void addWorkHourTest_1() throws InterruptedException {
        flag = true;
        getOpenAddWorkHourDialogButton().click();
        Thread.sleep(1000);
        getAddWorkHourSubmitButton().click();
        validateMessageBox("请完整填写所需字段");
    }

    /**
     * 测试添加工时，修改工时，撤销工时
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/8 9:39 下午
     **/
//    @Order(2)
//    @Test
//    @Disabled
//    void addWorkHourTest_2() throws InterruptedException {
//        if (flag) {
//            driver.navigate().refresh();
//            Thread.sleep(5000);
//        }
//        getOpenAddWorkHourDialogButton().click();
//        Thread.sleep(1000);
//        getAddWorkHourStartTimeInput().clear();
//        getAddWorkHourStartTimeInput().sendKeys("06:00:00");
//        driver.findElement(By.className("el-dialog__title")).click();
//        getAddWorkHourEndTimeInput().clear();
//        getAddWorkHourEndTimeInput().sendKeys("07:00:00");
//        driver.findElement(By.className("el-dialog__title")).click();
//        //TODO
//        //功能名称活动名称无法定位。。。
//
//        getAddWorkHourFunctionInput().click();
//
//        WebElement f = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/ul/li[1]"));
//        WebElement f_1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[1]/ul/li"));
//        f.click();
//        f_1.click();
////        WebElement mainelement = driver.findElements(By.className("el-cascader-node")).get(0);
////        Actions action = new Actions(driver);
////        action.moveToElement(mainelement).perform();
////        driver.findElement(By.cssSelector("#cascader-menu-1897-1-0")).click();
////        WebElement element = driver.findElements(By.className("el-cascader-node")).get(0);
////        action.moveToElement(element).perform();
////        driver.findElement(By.className("el-cascader-node__label")).click();
////        getAddWorkHourSubmitButton().click();
////        validateMessageBox("成功");
//    }

    //获取打开添加工时信息按钮
    private WebElement getOpenAddWorkHourDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("openAddWorkHourDialogButton"));
    }

    //获取添加工时对话框中起始时间输入框
    private WebElement getAddWorkHourStartTimeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addWorkHourStartTime"));
    }

    //获取添加工时信息对话框中结束时间输入框
    private WebElement getAddWorkHourEndTimeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addWorkHourEndTime"));
    }

    //获取添加工时信息功能名称输入框
    private WebElement getAddWorkHourFunctionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addWorkHourFunction"));
    }

    //获取添加工时信息活动名称输入框
    private WebElement getAddWorkHourActivityIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addWorkHourActivityId"));
    }

    //获取添加工时信息提交按钮
    private WebElement getAddWorkHourSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addWorkHourSubmitButton"));
    }
}
