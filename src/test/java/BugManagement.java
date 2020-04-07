import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @program: AchieveItSTest
 * @className: BugManagement
 * @author: 田家旭
 * @date: 2020-04-06 13:46
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BugManagement extends BaseInfo {
    private static boolean flag = false;//是否按顺序全部执行
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "defect");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 按照缺陷id来查找缺陷
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 2:19 下午
     **/
    @Order(1)
    @Test
    void bugSearchTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String bugId = "3";
        getBugIdSearchInput().sendKeys(bugId);
        getBugSearchButton().click();
        Thread.sleep(6000);
        List<WebElement> idResults = driver.findElements(By.className("el-table_1_column_2"));
        for (WebElement e : idResults
        ) {
            if (e.getText().equals("") || e.getText().equals("缺陷ID")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(bugId));
        }

    }

    /**
     * 按照缺陷标题查找缺陷
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 5:01 下午
     **/
    @Order(2)
    @Test
    void bugSearchTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String bugTitle = "第一个缺陷";
        getBugTitleSearchInput().sendKeys(bugTitle);
        getBugSearchButton().click();
        Thread.sleep(6000);
        List<WebElement> titleResults = driver.findElements(By.className("el-table_1_column_3"));
        for (WebElement e : titleResults
        ) {
            if (e.getText().equals("") || e.getText().equals("标题")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(bugTitle));
        }
    }

    /**
     * 按照缺陷提出人查找缺陷
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 5:05 下午
     **/
    @Order(3)
    @Test
    void bugSearchTest_3() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String bugIntroducer = "王五";
        getBugIntroducerSearchInput().sendKeys(bugIntroducer);
        getBugSearchButton().click();
        Thread.sleep(6000);
        List<WebElement> introducerResults = driver.findElements(By.className("el-table_1_column_4"));
        for (WebElement e : introducerResults
        ) {
            if (e.getText().equals("") || e.getText().equals("提出人")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(bugIntroducer));
        }
    }

    /**
     * 按照缺陷状态查找缺陷
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 5:10 下午
     **/
    @Order(4)
    @Test
    void bugSearchTest_4() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        int statusIndex = 1;
        String[] bugStatus = {"NEW", "OPENED", "PROCESSED", "SOLVED", "CLOSED"};
        getBugStatusSearchInput().click();
        Thread.sleep(1000);
        getBugStatusSearchOptions().get(statusIndex).click();
        getBugSearchButton().click();
        Thread.sleep(6000);
        List<WebElement> statusResults = driver.findElements(By.className("el-table_1_column_5"));
        for (WebElement e : statusResults
        ) {
            if (e.getText().equals("") || e.getText().equals("缺陷状态")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().equals(bugStatus[statusIndex]));
        }
    }

    /**
     * 查询不存在的缺陷信息
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 5:17 下午
     **/
    @Order(5)
    @Test
    void bugSearchTest_5() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getBugIdSearchInput().sendKeys("0000");
        getBugSearchButton().click();
        Thread.sleep(5000);
        WebElement emptyText = driver.findElement(By.className("el-table__empty-text"));
        Assertions.assertEquals("无缺陷条目", emptyText.getText());
    }

    /**
     * 测试正常修改缺陷信息
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 5:40 下午
     **/
    @Order(6)
    @Test
    void bugEditTest_1() throws InterruptedException {
        driver.navigate().refresh();
        String editBugId = "2";
        String editBugTitle = "修改过的第一个缺陷";
        Thread.sleep(5000);
        getBugIdSearchInput().sendKeys(editBugId);
        getBugSearchButton().click();
        Thread.sleep(5000);
        getOpenEditBugDialogButton().get(0).click();
        Thread.sleep(3000);
        WebElement editBugTitleInput = getEditBugTitleInput();
        editBugTitleInput.click();
        editBugTitleInput.clear();
        editBugTitleInput.sendKeys(editBugTitle);
        Thread.sleep(1000);
        getEditBugSubmitButton().click();
        validateMessageBox("缺陷更新成功");
    }

    /**
     * 测试修改缺陷信息后不提交
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 6:06 下午
     **/
    @Order(7)
    @Test
    void bugEditTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String editBugId = "2";
        getBugIdSearchInput().sendKeys(editBugId);
        getBugSearchButton().click();
        Thread.sleep(5000);
        getOpenEditBugDialogButton().get(0).click();
        Thread.sleep(3000);
        WebElement editBugStatusInput = getEditBugStatusInput();
        editBugStatusInput.click();
        Thread.sleep(1000);
        String oldStatus = editBugStatusInput.getAttribute("value");
        System.out.println(oldStatus);
        System.out.println("divide");
        getEditBugStatusOptions().get(4).click();
        Thread.sleep(1000);
        getEditBugCancelSubmitButton().click();
        List<WebElement> statusResult = driver.findElements(By.className("el-table_1_column_5"));
        for (WebElement e : statusResult
        ) {
            if (e.getText().equals("") || e.getText().equals("缺陷状态")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().equals(oldStatus));
        }
    }

    /**
     * 测试使用不完整的信息新增缺陷
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 6:39 下午
     **/
    @Order(8)
    @Test
    void bugAddTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getOpenAddBugDialogButton().click();
        getNewBugTitleInput().sendKeys("功能测试缺陷标题");
        getNewBugResponsibleInput().click();
        Thread.sleep(1000);
        getNewBugResponsibleOptions().get(1).click();
        getNewBugPriorityInput().click();
        Thread.sleep(1000);
        getNewBugPriorityOptions().get(1).click();
        getNewBugSubmitButton().click();
        validateMessageBox("请完整填写所需内容");
    }

    /**
     * 测试正常的添加缺陷信息
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/6 6:47 下午
     **/
    @Order(9)
    @Test
    void bugAddTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        getOpenAddBugDialogButton().click();
        getNewBugTitleInput().sendKeys("功能测试缺陷标题");
        getNewBugResponsibleInput().click();
        Thread.sleep(1000);
        getNewBugResponsibleOptions().get(1).click();
        getNewBugPriorityInput().click();
        Thread.sleep(1000);
        getNewBugPriorityOptions().get(1).click();
        getNewBugDescriptionInput().sendKeys("功能测试缺陷描述内容");
        getNewBugSubmitButton().click();
        validateMessageBox("新增缺陷成功");
    }


    //获取bugId的查询输入框
    private WebElement getBugIdSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugIdSearch"));
    }

    //获取缺陷标题的查询输入框
    private WebElement getBugTitleSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugTitleSearch"));
    }

    //获取缺陷提出人的查询输入框
    private WebElement getBugIntroducerSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugIntroducerSearch"));
    }

    //获取缺陷状态的查询选择框
    private WebElement getBugStatusSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugStatusSearch"));
    }

    //获取缺陷状态查询输入框的选项
    private List<WebElement> getBugStatusSearchOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("bugStatusSearchOption"));
    }

    //获取缺陷查询搜索按钮
    private WebElement getBugSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugSearchButton"));
    }

    //获取打开添加缺陷对话框的按钮
    private WebElement getOpenAddBugDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("bugOpenAddBugDialogButton"));
    }

    //获取添加缺陷中标题输入框
    private WebElement getNewBugTitleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugTitle"));
    }

    //获取添加缺陷中缺陷负责人的选择框
    private WebElement getNewBugResponsibleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugResponsible"));
    }

    //获取添加缺陷中缺陷负责人选择框的选项
    private List<WebElement> getNewBugResponsibleOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newBugResponsibleOption"));
    }

    //获取添加缺陷中优先级的选择框
    private WebElement getNewBugPriorityInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugPriority"));
    }

    //获取添加缺陷中缺陷优先级选择框的选项
    private List<WebElement> getNewBugPriorityOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("newBugPriorityOption"));
    }

    //获取添加缺陷中缺陷描述的输入框
    private WebElement getNewBugDescriptionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugDescription"));
    }

    //获取添加缺陷的确定提交按钮
    private WebElement getNewBugSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugSubmit"));
    }

    //获取添加缺陷的取消提交按钮
    private WebElement getNewBugCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("newBugCancelSubmit"));
    }

    //获取编辑缺陷中缺陷标题输入框
    private WebElement getEditBugTitleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugTitle"));
    }

    //获取编辑缺陷中缺陷优先级的选择框
    private WebElement getEditBugPriorityInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugPriority"));
    }

    //获取编辑缺陷中缺陷优先级选择框的选项
    private List<WebElement> getEditBugPriorityOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("editBugPriorityOption"));
    }

    //获取编辑缺陷中缺陷负责人的选择框
    private WebElement getEditBugResponsibleInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugResponsible"));
    }

    //获取编辑缺陷中缺陷负责人输入框的选项
    private List<WebElement> getEditBugResponsibleOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("editBugResponsibleOption"));
    }

    //获取编辑缺陷中缺陷状态的选择框
    private WebElement getEditBugStatusInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugStatus"));
    }

    //获取编辑缺陷中缺陷状态选择框的选项
    private List<WebElement> getEditBugStatusOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("editBugStatusOption"));
    }

    //获取编辑缺陷中缺陷描述输入框
    private WebElement getEditBugDescriptionInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugDescription"));
    }

    //获取编辑缺陷的确定提交按钮
    private WebElement getEditBugSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugSubmit"));
    }

    //获取编辑缺陷的取消提交按钮
    private WebElement getEditBugCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("editBugCancelSubmit"));
    }

    //获取打开编辑缺陷对话框的按钮
    private List<WebElement> getOpenEditBugDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("openEditDialogButton"));
    }

}
