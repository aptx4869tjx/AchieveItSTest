import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 总计2个测试用例
 * 1个自动化测试用例 1个手工测试用例（全选提交）
 *
 * @program: AchieveItSTest
 * @className: ArchiveManagement
 * @author: 田家旭
 * @date: 2020-04-11 10:05
 **/

public class ArchiveManagement extends BaseInfo {
    private static String projectIdTest = "20201038D01";

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest, "file");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试提交项目的资产
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/11 10:18 上午
     **/
    @Order(1)
    @Test
    void updateFileTest_1() throws InterruptedException {
        Thread.sleep(3000);
        if (getUpdateFileButton() != null && getUpdateFileButton().size() != 0) {
            getUpdateFileButton().get(0).click();
            validateMessageBox("提交成功");
        }
    }


    //获取提交项目资产按钮
    private List<WebElement> getUpdateFileButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElements(By.name("updateFileButton"));
    }
}
