import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 总计8个测试用例
 * 7个自动化测试用例 1个手工测试用例（翻页）
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
     * 通过风险Id查询存在的风险
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 10:13 上午
     **/
    @Test
    @Order(1)
    void searchRiskTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String searchId = "310";
        WebElement riskIdSearch = getRiskIdSearch();
        riskIdSearch.click();
        riskIdSearch.clear();
        riskIdSearch.sendKeys(searchId);
        getRiskSearchButton().click();
        //等待查询结果
        Thread.sleep(6000);
        List<WebElement> idSearchResults = driver.findElements(By.className("el-table_1_column_2"));
        for (WebElement e : idSearchResults
        ) {
            if (e.getText().equals("") || e.getText().equals("风险 ID")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(searchId));
        }
    }


    /**
     * 按照风险类型查询风险
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 2:25 下午
     **/
    @Order(2)
    @Test
    void searchRiskTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String searchType = "技术风险";
        WebElement riskTypeSearch = getRiskTypeSearch();
        riskTypeSearch.click();
        riskTypeSearch.clear();
        riskTypeSearch.sendKeys(searchType);
        getRiskSearchButton().click();
        //等待查询结果
        Thread.sleep(6000);
        List<WebElement> idSearchResults = driver.findElements(By.className("el-table_1_column_3"));
        for (WebElement e : idSearchResults
        ) {
            if (e.getText().equals("") || e.getText().equals("风险类型")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(searchType));
        }
    }

    /**
     * 按照风险责任人查询风险
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 11:11 上午
     **/
    @Order(3)
    @Test
    void searchRiskTest_3() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String searchResponsible = "张三";
        WebElement riskResponsibleSearch = getRiskResponsibleSearch();
        riskResponsibleSearch.click();
        riskResponsibleSearch.clear();
        riskResponsibleSearch.sendKeys(searchResponsible);
        getRiskSearchButton().click();
        //等待查询结果
        Thread.sleep(6000);
        List<WebElement> idSearchResults = driver.findElements(By.className("el-table_1_column_4"));
        for (WebElement e : idSearchResults
        ) {
            if (e.getText().equals("") || e.getText().equals("风险责任人")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(searchResponsible));
        }
    }


    /**
     * 按照风险状态进行查询
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 11:20 上午
     **/
    @Order(4)
    @Test
    void searchRiskTest_4() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        int statusIndex = 1;
        String[] riskStatus = {"NEW", "OPENED", "PROCESSED", "SOLVED", "CLOSED"};
        getRiskStatusSearch().click();
        Thread.sleep(1000);
        getRiskStatusOptionSearch().get(statusIndex).click();
        getRiskSearchButton().click();
        //等待查询结果
        Thread.sleep(6000);
        List<WebElement> idSearchResults = driver.findElements(By.className("el-table_1_column_5"));
        for (WebElement e : idSearchResults
        ) {
            if (e.getText().equals("") || e.getText().equals("风险状态")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(riskStatus[statusIndex]));
        }
    }

    /**
     * 查询不存在的风险
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 11:20 上午
     **/
    @Order(5)
    @Test
    void searchRiskTest_5() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String searchId = "【】；'865";
        WebElement riskIdSearch = getRiskIdSearch();
        riskIdSearch.click();
        riskIdSearch.clear();
        riskIdSearch.sendKeys(searchId);
        getRiskSearchButton().click();
        //等待查询结果
        Thread.sleep(6000);
        WebElement emptyText = driver.findElement(By.className("el-table__empty-text"));
        Assertions.assertEquals("暂无数据", emptyText.getText());
    }

    /**
     * 测试更新风险信息
     * @author 田家旭
     * @date 2020/4/10 10:00 上午
     * @param
     * @return void
     **/
    @Order(6)
    @Test
    void updateRiskTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String riskId = "1315";
        getRiskIdSearch().sendKeys(riskId);
        getRiskSearchButton().click();
        Thread.sleep(5000);
        getOpenEditDialogButton().get(0).click();
        Thread.sleep(3000);
        getEditRiskTypeInput().clear();
        getEditRiskTypeInput().sendKeys("功能测试修改的风险类型");
        getEditRiskStrategyInput().clear();
        getEditRiskStrategyInput().sendKeys("功能测试修改的风险策略");
        getEditRiskSubmitButton().click();
        validateMessageBox("更新成功");
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
    @Order(7)
    void addRiskTest_1() throws InterruptedException {
        flag = true;
        int statusIndex = 0;
        int riskLevelIndex = 1;
        int riskResponsibleIndex = 1;
        int riskAffectIndex = 2;
        int[] riskRelatedIndexs = {1, 2, 3};
        WebElement newRiskButton = getOpenNewRiskButton();
        newRiskButton.click();
        Thread.sleep(2000);
        WebElement riskTypeInput = getRiskTypeInput();
        riskTypeInput.click();
        riskTypeInput.clear();
        riskTypeInput.sendKeys("技术风险");
        getRiskStatusInput().click();
        getNewRiskStatusOptions().get(statusIndex).click();
        getRiskLevelInput().click();
        getNewRiskLevelOptions().get(riskLevelIndex).click();
        getRiskResponsibleInput().click();
        getNewRiskResponsibleOptions().get(riskResponsibleIndex).click();
        getRiskAffectInput().click();
        getNewRiskAffectOptions().get(riskAffectIndex).click();
        getRiskTrackFreqInput().sendKeys("5");
        getRiskRelatedInput().sendKeys("");
        List<WebElement> newRiskRelatedOptions = getNewRiskRelatedOptions();
        for (int index : riskRelatedIndexs
        ) {
            newRiskRelatedOptions.get(index).click();
        }
        getRiskReactInput().sendKeys("功能测试风险应对");
        getRiskStrategyInput().sendKeys("功能测试应对策略");
        getRiskDescriptionInput().sendKeys("功能测试风险描述");
        getSubmitButton().click();
        validateMessageBox("风险新增成功");

    }


    //新建风险按钮
    private WebElement getOpenNewRiskButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("openNewRiskButton"));
    }

    //取消新建风险提交按钮
    private WebElement getCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("cancelSubmitButton"));
    }

    //确定提交新建风险按钮
    private WebElement getSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("submitButton"));
    }

    //新建风险中风险类型输入框
    private WebElement getRiskTypeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskType"));
    }

    //新建风险中风险状态输入框
    private WebElement getRiskStatusInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskStatus"));
    }

    //新建风险中风险级别输入框
    private WebElement getRiskLevelInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskLevel"));
    }

    //新建风险中风险责任人输入框
    private WebElement getRiskResponsibleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskResponsible"));
    }

    //新建风险中风险影响度输入框
    private WebElement getRiskAffectInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskAffect"));
    }

    //新建风险中风险跟踪频度输入框
    private WebElement getRiskTrackFreqInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskTrackFreq"));
    }

    //新建风险中风险相关者输入框
    private WebElement getRiskRelatedInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskRelated"));
    }

    //新建风险中风险应对输入框
    private WebElement getRiskReactInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskReact"));
    }

    //新建风险中风险策略输入框
    private WebElement getRiskStrategyInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskStrategy"));
    }

    //新建风险中风险描述输入框
    private WebElement getRiskDescriptionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskDescription"));
    }

    //风险id的查询输入框
    private WebElement getRiskIdSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskIdSearch"));
    }

    //风险类型查询输入框
    private WebElement getRiskTypeSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskTypeSearch"));
    }

    //风险责任人查询输入框
    private WebElement getRiskResponsibleSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskResponsibleSearch"));
    }

    //风险状态查询输入框
    private WebElement getRiskStatusSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("riskStatusSearch"));
    }

    //风险状态查询输入框的选项
    private List<WebElement> getRiskStatusOptionSearch() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("riskStatusOptionSearch"));
    }

    //风险搜索按钮
    private WebElement getRiskSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("searchRiskButton"));
    }

    //新建风险状态的下拉选项
    private List<WebElement> getNewRiskStatusOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newRiskStatusOption"));
    }

    //新建风险级别的下拉选项
    private List<WebElement> getNewRiskLevelOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newRiskLevelOption"));
    }

    //新建风险责任人的下拉选项
    private List<WebElement> getNewRiskResponsibleOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newRiskResponsibleOption"));
    }

    //新建风险影响度的下拉选项
    private List<WebElement> getNewRiskAffectOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newRiskAffectOption"));
    }

    //新建风险相关者的下拉选项
    private List<WebElement> getNewRiskRelatedOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newRiskRelatedOption"));
    }

    //获取消息提示框的文本内容
    private String getMessageBoxContent() throws InterruptedException {
        WebElement message = driver.findElement(By.className("el-message__content"));
        while (message.getText() == null || message.getText().equals("")) {
            Thread.sleep(200);
        }
        return message.getText();
    }

    //获取打开编辑风险对话框按钮
    private List<WebElement> getOpenEditDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("openEditDialogButton"));
    }

    //确定提交编辑风险按钮
    private WebElement getEditRiskSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editRiskSubmitButton"));
    }

    //新建风险中风险类型输入框
    private WebElement getEditRiskTypeInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editRiskType"));
    }

    //获取新建风险中风险应对策略输入框
    private WebElement getEditRiskStrategyInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editRiskStrategy"));
    }
}
