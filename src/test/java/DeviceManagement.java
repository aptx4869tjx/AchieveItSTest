import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 总计8个测试用例
 * 6个自动化测试用例 2个手工测试用例（1.翻页；2.添加设备点击取消按钮）
 *
 * @program: AchieveItSTest
 * @className: DeviceManagement
 * @author: 田家旭
 * @date: 2020-04-07 19:20
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceManagement extends BaseInfo {
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        //使用设备管理员账号登录
        setUp(projectIdTest, "device", "wangwu", "123456");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试按照资产类型查询设备
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 7:23 下午
     **/
    @Order(1)
    @Test
    void deviceSearchTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String deviceType = "电脑";
        getDeviceTypeSearchInput().sendKeys(deviceType);
        Thread.sleep(1000);
        getDeviceSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_2"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("资产类型")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(deviceType));
        }
    }

    /**
     * 按照设备状态查询
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 7:46 下午
     **/
    @Order(2)
    @Test
    void deviceSearchTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String[] deviceStatus = {"完好", "损坏"};
        int statusIndex = 0;
        getDeviceStatusSearchInput().click();
        Thread.sleep(1000);
        getDeviceStatusSearchInputOptions().get(statusIndex).click();
        getDeviceSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_5"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("设备状态")) {
                continue;
            }
            System.out.println(e.findElement(By.tagName("span")).getText());
            Assertions.assertTrue(e.findElement(By.tagName("span")).getText().contains(deviceStatus[statusIndex]));
        }
    }

    /**
     * 按照设备是否归还查询设备
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 7:54 下午
     **/
    @Order(3)
    @Test
    void deviceSearchTest_3() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String[] deviceIsReturned = {"已归还", "未归还"};
        int index = 1;
        getDeviceIsReturnedSearchInput().click();
        Thread.sleep(1000);
        getDeviceIsReturnedSearchInputOptions().get(index).click();
        getDeviceSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_6"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("是否归还")) {
                continue;
            }
            System.out.println(e.findElement(By.tagName("span")).findElement(By.tagName("span")).getText());
            Assertions.assertTrue(e.findElement(By.tagName("span")).findElement(By.tagName("span")).getText().contains(deviceIsReturned[index]));
        }
    }

    /**
     * 查询不存在的资产
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 8:01 下午
     **/
    @Order(4)
    @Test
    void deviceSearchTest_4() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String deviceType = "+_*&";
        getDeviceTypeSearchInput().sendKeys(deviceType);
        Thread.sleep(1000);
        getDeviceSearchButton().click();
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.className("el-table__empty-text"));
        Assertions.assertEquals("暂无数据", element.getText());
    }

    /**
     * 测试添加新的设备，更改设备状态，归还设备
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 9:05 下午
     **/
    @Order(5)
    @Test
    void addDeviceTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        //添加设备
        getOpenAddDeviceDialogButton().click();
        String deviceType = "功能测试电脑";
        getAddDeviceTypeInput().sendKeys(deviceType);
        getAddDeviceUsageTimeLimitInput().clear();
        getAddDeviceUsageTimeLimitInput().sendKeys("2020-10-01");
        getAddDeviceSubmitButton().click();
        validateMessageBox("成功");

        //更新设备状态
        Thread.sleep(5000);
        getDeviceTypeSearchInput().sendKeys(deviceType);
        getDeviceSearchButton().click();
        Thread.sleep(5000);
        getDeviceChangeStatusButton().get(0).click();
        validateMessageBox("成功");

        //归还设备
        Thread.sleep(5000);
        getDeviceReturnButton().get(0).click();
        validateMessageBox("成功");
    }

    /**
     * 测试使用不完整的信息添加设备
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 9:16 下午
     **/
    @Order(6)
    @Test
    void addDeviceTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getOpenAddDeviceDialogButton().click();
        getAddDeviceSubmitButton().click();
        validateMessageBox("请完整填写所需字段");
    }


    //获取资产类型查询输入框
    private WebElement getDeviceTypeSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("deviceTypeSearch"));
    }

    //获取设备状态查询选择框
    private WebElement getDeviceStatusSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("deviceStatusSearch"));
    }

    //获取设备状态查询选择框的选项
    private List<WebElement> getDeviceStatusSearchInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("deviceStatusSearchOption"));
    }

    //获取设备是否归还查询选择框
    private WebElement getDeviceIsReturnedSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("deviceIsReturnedSearch"));
    }

    //获取设备是否归还选择框的选项
    private List<WebElement> getDeviceIsReturnedSearchInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("deviceIsReturnedSearchOption"));
    }

    //获取设备查询按钮
    private WebElement getDeviceSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("deviceSearchButton"));
    }

    //获取打开添加设备对话框按钮
    private WebElement getOpenAddDeviceDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("openAddDeviceDialogButton"));
    }

    //获取添加设备对话框中设别类型输入框
    private WebElement getAddDeviceTypeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addDeviceType"));
    }

    //获取添加设备对话框中使用期限日期选择器
    private WebElement getAddDeviceUsageTimeLimitInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addDeviceUsageTimeLimit"));
    }

    //获取添加设备对话框中设备状态输入框
    private WebElement getAddDeviceStatusInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addDeviceStatus"));
    }

    //获取添加设备对话框中确定提交按钮
    private WebElement getAddDeviceSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addDeviceSubmit"));
    }

    //获取添加设备对话框中取消提交ann
    private WebElement getAddDeviceCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addDeviceCancelSubmit"));
    }

    //获取更改设备状态按钮
    private List<WebElement> getDeviceChangeStatusButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("deviceChangeStatusButton"));
    }

    //获取设备归还按钮
    private List<WebElement> getDeviceReturnButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("deviceReturnButton"));
    }
}
