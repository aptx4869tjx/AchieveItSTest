import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 总计8个测试用例
 * 4个自动化测试用例 4个手工测试用例（1.由于功能名称活动名称元素id在不同浏览器打开是变化的，同时采用xpath，cssSelector也无法定位，所以修改工时信息采取手工测试；2.撤销工时信息；3.审核通过工时信息；4.审核驳回工时信息）
 *
 * @program: AchieveItSTest
 * @className: WorkHourSearch
 * @author: 田家旭
 * @date: 2020-04-10 16:04
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkHourSearch extends BaseInfo {
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "work-hour", "lisi", "123456");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 按照员工查询
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/10 4:21 下午
     **/
    @Order(1)
    @Test
    void workHourSearchTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getAllWorkHourTab().click();
        Thread.sleep(5000);
        getEmployeeIdSearch().click();
        getEmployeeIdSearchOptions().get(0).click();
        String id = getEmployeeIdSearch().getAttribute("value");
        System.out.println(id);
        getWorkHourSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_2_column_9"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("员工ID")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(id.contains(e.getText()));
        }
    }

    /**
     * 按照日期查询
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/10 4:32 下午
     **/
    @Order(2)
    @Test
    void workHourSearchTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getAllWorkHourTab().click();
        Thread.sleep(5000);
        getDateSearch().sendKeys("2020-04-10");
        getWorkHourSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_2_column_11"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("日期")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().equals("2020年4月10日"));
        }
    }


    /**
     * 按照审核状态查询
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/10 4:36 下午
     **/
    @Order(3)
    @Test
    void workHourSearchTest_3() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getAllWorkHourTab().click();
        Thread.sleep(5000);
        String[] status = {"审核中", "已通过", "已驳回", "已撤回"};
        int statusIndex = 0;
        getAuditingStatusSearch().click();
        getAuditingStatusSearchOptions().get(statusIndex).click();
        getWorkHourSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_2_column_15"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("审核状态")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().equals(status[statusIndex]));
        }
    }

    /**
     * 查询不存在的工时信息
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/10 4:45 下午
     **/
    @Order(4)
    @Test
    void workHourSearchTest_4() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getAllWorkHourTab().click();
        Thread.sleep(5000);
        getDateSearch().sendKeys("2019-04-10");
        getWorkHourSearchButton().click();
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.className("el-table__empty-text"));
        Assertions.assertEquals("无工时条目", element.getText());
    }


    //获取所有工时的tab按钮
    private WebElement getAllWorkHourTab() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.id("tab-all"));
    }

    //获取员工Id搜索选择框
    private WebElement getEmployeeIdSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("employeeIdSearch"));
    }

    //获取员工搜索框的选项
    private List<WebElement> getEmployeeIdSearchOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("employeeIdSearchOption"));
    }

    //获取日期查询输入框
    private WebElement getDateSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("dateSearch"));
    }

    //获取审核状态查询选择框
    private WebElement getAuditingStatusSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("auditingStatusSearch"));
    }

    //获取审核状态选择框选项
    private List<WebElement> getAuditingStatusSearchOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("auditingStatusSearchOption"));
    }

    //获取查询按钮
    private WebElement getWorkHourSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("workHourSearchButton"));
    }
}
