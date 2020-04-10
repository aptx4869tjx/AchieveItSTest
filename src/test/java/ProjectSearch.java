import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 总计6个测试用例
 * 6个自动化测试用例 0个手工测试用例
 *
 * @program: AchieveItSTest
 * @className: ProjectSearch
 * @author: 田家旭
 * @date: 2020-03-31 10:44
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectSearch {
    private static WebDriver driver;
    private static boolean flag = false;//是否按顺序全部执行
    private String[] statusText = {"已归档", "审核中", "已驳回", "进行中"};

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
        }
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }


    /*
     * 测试项目Id查询，存在的项目
     * @author 田家旭
     * @date 2020/3/31 11:22 上午
     * @param
     * @return void
     **/
    @Order(1)
    @Test
    public void findByProjectIdTest_1() throws InterruptedException {
        flag = true;
        String projectId = "12";
        WebElement projectIdInput = getProjectIdInput();
        projectIdInput.click();
        projectIdInput.sendKeys(projectId);
        WebElement searchButton = getSearchButton();
        searchButton.click();
        //查询过后，需要等待结果返回并渲染
        Thread.sleep(5000);
        List<WebElement> projectIds = driver.findElements(By.className("el-table_1_column_2"));
        System.out.println(projectIds.size());
        for (WebElement e : projectIds
        ) {
            String s = e.getText();
            //有些元素不在表格内，但class也符合筛选条件，对此不做判断
            if (s.equals("")) {
                continue;
            }
            System.out.println(s);

            Assertions.assertTrue(s.contains(projectId) || s.equals("项目ID"));
        }
    }

    /**
     * 测试通过projectId查找不存在的项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/31 11:29 上午
     **/
    @Order(2)
    @Test
    public void findByProjectIdTest_2() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(2000);

        }
        String projectId = "[][=+";
        WebElement projectIdInput = getProjectIdInput();
        projectIdInput.click();
        projectIdInput.clear();
        projectIdInput.sendKeys(projectId);
        WebElement searchButton = getSearchButton();
        searchButton.click();
        //查询过后，需要等待结果返回并渲染
        Thread.sleep(5000);
        WebElement emptyText = getEmptyText();
        Assertions.assertEquals("未参与项目", emptyText.getText());
    }

    /**
     * 测试通过项目名称查找存在的项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/31 8:17 下午
     **/
    @Order(3)
    @Test
    public void findByProjectNameTest_1() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(2000);

        }
        String projectName = "时间测试";
        WebElement projectNameInput = getProjectNameInput();
        projectNameInput.click();
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        WebElement searchButton = getSearchButton();
        searchButton.click();
        //等待查询结果渲染
        Thread.sleep(5000);
        List<WebElement> projectNames = driver.findElements(By.className("el-table_1_column_3"));
        for (WebElement e : projectNames
        ) {
            String s = e.getText();
            //有些元素不在表格内，但class也符合筛选条件，对此不做判断
            if (s.equals("")) {
                continue;
            }
            System.out.println(s);

            Assertions.assertTrue(s.contains(projectName) || s.equals("项目名称"));
        }
    }

    /**
     * 测试通过不合理的项目名查找不存在的项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/31 8:23 下午
     **/
    @Order(4)
    @Test
    public void findByProjectNameTest_2() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(2000);

        }
        String projectName = "]=/?";
        WebElement projectNameInput = getProjectNameInput();
        projectNameInput.click();
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        WebElement searchButton = getSearchButton();
        searchButton.click();
        //等待查询结果渲染
        Thread.sleep(5000);
        WebElement emptyText = getEmptyText();
        Assertions.assertEquals("未参与项目", emptyText.getText());
    }


    /**
     * 测试通过项目状态来查找相应的项目
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/3/31 8:27 下午
     **/
    @Order(5)
    @Test
    public void findByProjectStatusTest_1() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(2000);

        }
        WebElement projectStatusInput = getProjectStatusInput();
        List<WebElement> statusOption = getStatusOption();
        WebElement searchButton = getSearchButton();
        System.out.println(statusOption.size());
        Thread.sleep(1000);
        for (int i = 0; i < 4; i++) {
            projectStatusInput.click();
            Thread.sleep(2000);
            statusOption.get(i).click();
            searchButton.click();
            Thread.sleep(3000);
            List<WebElement> statusResults = driver.findElements(By.className("el-table_1_column_6"));
            for (WebElement e : statusResults
            ) {
                String s = e.getText();
                // 对空的元素不做判断
                if (s.equals(""))
                    continue;
                System.out.println(s);
                //验证项目状态
                Assertions.assertTrue(s.contains(statusText[i]) || s.equals("项目状态"));
            }
        }
    }

    /**
     * 综合查询
     *
     * @param
     * @return org.openqa.selenium.WebElement
     * @author 田家旭
     * @date 2020/3/31 9:48 下午
     **/
    @Order(6)
    @Test
    public void findProjectByConditions() throws InterruptedException {
        if (flag) {
            driver.navigate().refresh();
            Thread.sleep(2000);

        }
        WebElement projectIdInput = getProjectIdInput();
        WebElement projectNameInput = getProjectNameInput();
        WebElement projectStatusInput = getProjectStatusInput();
        WebElement searchButton = getSearchButton();
        List<WebElement> statusOption = getStatusOption();
        String projectId = "1";
        String projectName = "测试";
        int statusIndex = 3;
        //等待页面渲染
        Thread.sleep(3000);
        projectIdInput.click();
        projectIdInput.clear();
        projectIdInput.sendKeys(projectId);
        projectNameInput.click();
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        projectStatusInput.click();
        Thread.sleep(1000);
        System.out.println(statusOption.size());
        statusOption.get(statusIndex).click();
        searchButton.click();
        //等待查询结果
        Thread.sleep(5000);
        List<WebElement> projectIds = driver.findElements(By.className("el-table_1_column_2"));
        List<WebElement> projectNames = driver.findElements(By.className("el-table_1_column_3"));
        List<WebElement> statusResults = driver.findElements(By.className("el-table_1_column_6"));
        for (WebElement e : projectIds
        ) {
            String s = e.getText();
            //有些元素不在表格内，但class也符合筛选条件，对此不做判断
            if (s.equals("")) {
                continue;
            }
            System.out.println(s);

            Assertions.assertTrue(s.contains(projectId) || s.equals("项目ID"));
        }
        for (WebElement e : projectNames
        ) {
            String s = e.getText();
            //有些元素不在表格内，但class也符合筛选条件，对此不做判断
            if (s.equals("")) {
                continue;
            }
            System.out.println(s);

            Assertions.assertTrue(s.contains(projectName) || s.equals("项目名称"));
        }
        for (WebElement e : statusResults
        ) {
            String s = e.getText();
            // 有些元素不在表格内，但class也符合筛选条件，对此不做判断
            if (s.equals(""))
                continue;
            System.out.println(s);
            //验证项目状态
            Assertions.assertTrue(s.contains(statusText[statusIndex]) || s.equals("项目状态"));
        }
    }


    private WebElement getProjectIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectIdInput"));
    }

    private WebElement getProjectNameInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectNameInput"));
    }

    private WebElement getProjectStatusInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("projectStatusInput"));
    }

    private WebElement getSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("searchButton"));
    }

    private WebElement getEmptyText() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.className("el-table__empty-text"));
    }

    private List<WebElement> getStatusOption() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.className("el-select-dropdown__item"));
    }

}
