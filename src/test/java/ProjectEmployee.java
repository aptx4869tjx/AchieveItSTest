import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 总计8个测试用例
 * 6个自动化测试用例 2个手工测试用例（1.测试翻页；2.添加人员点击取消按钮）
 * @program: AchieveItSTest
 * @className: ProjectEmployee
 * @author: 田家旭
 * @date: 2020-04-07 15:58
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectEmployee extends BaseInfo {
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "participant");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试按照员工Id查找
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 4:24 下午
     **/
    @Order(1)
    @Test
    void employeeSearchTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String employeeId = "100002";
        getEmployeeIdSearchInput().sendKeys(employeeId);
        getEmployeeSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_2"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("员工ID")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(employeeId));
        }
    }

    /**
     * 测试按照员工姓名查找
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 5:34 下午
     **/
    @Order(2)
    @Test
    void employeeSearchTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String employeeName = "李四";
        getEmployeeNameSearchInput().sendKeys(employeeName);
        getEmployeeSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_3"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("姓名")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(employeeName));
        }
    }

    /**
     * 按照员工角色查找
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 5:38 下午
     **/
    @Order(3)
    @Test
    void employeeSearchTest_3() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String[] roles = {"项目经理", "开发Leader", "开发", "测试Leader", "测试", "配置管理员", "EPG", "QA"};
        int rolesIndex = 2;
        getEmployeeRolesSearchInput().click();
        Thread.sleep(1000);
        getEmployeeRolesSearchInputOptions().get(rolesIndex).click();
        getEmployeeSearchButton().click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("el-table_1_column_4"));
        for (WebElement e : elements
        ) {
            if (e.getText().equals("") || e.getText().equals("角色")) {
                continue;
            }
            System.out.println(e.getText());
            Assertions.assertTrue(e.getText().contains(roles[rolesIndex]));
        }
    }

    /**
     * 测试查找不存在的员工
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 5:45 下午
     **/
    @Order(4)
    @Test
    void employeeSearchTest_4() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String employeeId = "+-*&";
        getEmployeeIdSearchInput().sendKeys(employeeId);
        getEmployeeSearchButton().click();
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.className("el-table__empty-text"));
        Assertions.assertEquals("暂无数据", element.getText());
    }

    /**
     * 测试添加员工、设置角色、设置权限、删除员工
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 5:54 下午
     **/
    @Order(5)
    @Test
    void addEmployeeTest_1() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        //添加
        String employeeId = "100040";
        String[] roles = {"开发Leader", "开发", "测试Leader", "测试", "配置管理员", "EPG", "QA"};
        int rolesIndex = 2;
        String[] permissions = {"git", "文件系统", "邮件系统", "登记工时"};
        int permissionIndex = 0;
        getOpenAddEmployeeDialogButton().click();
        Thread.sleep(1000);
        WebElement addEmployeeIdInput = getAddEmployeeIdInput();
        addEmployeeIdInput.sendKeys(employeeId);
        getAddEmployeeRolesInput().click();
        Thread.sleep(1000);
        getAddEmployeeRolesInputOptions().get(rolesIndex).click();
        addEmployeeIdInput.click();
        getAddEmployeeSuperiorInput().click();
        Thread.sleep(1000);
        getAddEmployeeSuperiorInputOptions().get(0).click();
        getAddEmployeeSubmitButton().click();
        validateMessageBox("添加成功");

        //设置角色
        Thread.sleep(5000);
        getEmployeeIdSearchInput().sendKeys(employeeId);
        getEmployeeSearchButton().click();
        Thread.sleep(5000);
        getOpenSetRolesDialogButton().get(0).click();
        Thread.sleep(1000);
        //获取第三个选择
        driver.findElement(By.cssSelector("#app > div > section > section > main > div:nth-child(2) > div > div:nth-child(4) > div > div.el-dialog__body > div > label:nth-child(3)")).click();
        Thread.sleep(1000);
        getSetRolesSubmitButton().click();
        validateMessageBox("成功");


        //设置权限
        Thread.sleep(5000);
        getOpenSetPermissionsDialogButton().get(0).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#app > div > section > section > main > div:nth-child(2) > div > div:nth-child(5) > div > div.el-dialog__body > div > label:nth-child(1)")).click();
        Thread.sleep(1000);
        getSetPermissionsSubmitButton().click();
        validateMessageBox("成功");



        //删除
        Thread.sleep(5000);
        getDeleteEmployeeButton().get(0).click();
        Thread.sleep(1000);
        //确定删除按钮
        driver.findElement(By.cssSelector("body > div.el-message-box__wrapper > div > div.el-message-box__btns > button.el-button.el-button--default.el-button--small.el-button--primary")).click();
        validateMessageBox("删除成功");
    }

    /**
     * 测试使用不完整的员工信息添加员工
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/7 9:07 下午
     **/
    @Order(6)
    @Test
    void addEmployeeTest_2() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        String employeeId = "100040";
        getOpenAddEmployeeDialogButton().click();
        Thread.sleep(1000);
        WebElement addEmployeeIdInput = getAddEmployeeIdInput();
        addEmployeeIdInput.sendKeys(employeeId);
        getAddEmployeeSubmitButton().click();
        validateMessageBox("请完整填写所需字段");
    }


    //获取员工Id查询输入框
    private WebElement getEmployeeIdSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("employeeIdSearch"));
    }

    //获取员工姓名查询输入框
    private WebElement getEmployeeNameSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("employeeNameSearch"));
    }

    //获取员工角色查询选择框
    private WebElement getEmployeeRolesSearchInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("employeeRolesSearch"));
    }

    //获取员工角色查询选择框的选项
    private List<WebElement> getEmployeeRolesSearchInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("employeeRolesSearchOption"));
    }

    //获取员工搜索按钮
    private WebElement getEmployeeSearchButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("employeeSearch"));
    }

    //获取打开添加人员对话框按钮
    private WebElement getOpenAddEmployeeDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("openAddEmployeeDialog"));
    }

    //获取添加人员对话框中员工id输入框
    private WebElement getAddEmployeeIdInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeeId"));
    }

    //获取添加人员对话框中员工角色选择框
    private WebElement getAddEmployeeRolesInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeeRoles"));
    }

    //获取添加人员对话框中员工角色选择框的选项
    private List<WebElement> getAddEmployeeRolesInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("addEmployeeRolesOption"));
    }

    //获取添加员工对话框中员工权限选择框
    private WebElement getAddEmployeePermissionsInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeePermissions"));
    }

    //获取添加员工对话框中员工权限选择框的选项
    private List<WebElement> getAddEmployeePermissionsOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("addEmployeePermissionsOption"));
    }

    //获取添加员工对话框中项目上级选择框
    private WebElement getAddEmployeeSuperiorInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeeSuperior"));
    }

    //获取添加员工对话框中项目上级选择框的选项
    private List<WebElement> getAddEmployeeSuperiorInputOptions() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("addEmployeeSuperiorOption"));
    }

    //获取添加人员对话框中取消按钮
    private WebElement getAddEmployeeCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeeCancelSubmit"));
    }

    //获取添加人员对话框中确定按钮
    private WebElement getAddEmployeeSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("addEmployeeSubmit"));
    }

    //获取设置角色按钮
    private List<WebElement> getOpenSetRolesDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("openSetRolesDialogButton"));
    }

    //获取设置权限按钮
    private List<WebElement> getOpenSetPermissionsDialogButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("openSetPermissionsDialogButton"));
    }

    //获取删除员工按钮
    private List<WebElement> getDeleteEmployeeButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("deleteEmployeeButton"));
    }

    //获取设置角色对话框中确定按钮
    private WebElement getSetRolesSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("setRolesSubmit"));
    }

    //获取设置角色对话框中取消按钮
    private WebElement getSetRolesCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("setRolesCancelSubmit"));
    }

    //获取设置权限对话框中确定按钮
    private WebElement getSetPermissionsSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("setPermissionSubmit"));
    }

    //获取设置权限对话框中取消按钮
    private WebElement getSetPermissionsCancelSubmitButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("setPermissionsCancelSubmit"));
    }
}
