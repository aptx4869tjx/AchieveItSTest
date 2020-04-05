import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 项目功能列表测试
 *
 * @program: AchieveItSTest
 * @className: FunctionList
 * @author: 田家旭
 * @date: 2020-04-02 17:17
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FunctionList extends BaseInfo{
    private static boolean flag = false;//是否按顺序全部执行
    private static String projectIdTest = "12345678901";//测试的项目的projectId

    @BeforeAll
    public static void setUp() throws InterruptedException {
        setUp(projectIdTest,"function-list");
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 测试上传正确格式的excel文件
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/2 5:38 下午
     **/
    @Order(1)
    @Test
    void uploadExcelTest() throws InterruptedException {
        flag = true;
        String uploadFilePath = "src/main/resources/测试.xlsx";
        File excelUpload = new File(uploadFilePath);
        if (excelUpload.exists()) {
            System.out.println(excelUpload.exists());
            Thread.sleep(1000);
            WebElement excelUploadInput = getExcelUploadInput();
            System.out.println(excelUpload.getAbsolutePath());
            excelUploadInput.sendKeys(excelUpload.getAbsolutePath());
            Thread.sleep(3000);
            WebElement uploadFunctionListButton = getUploadFunctionListButton();
            uploadFunctionListButton.click();
            WebElement message = driver.findElement(By.className("el-message__content"));
            while (message.getText() == null || message.getText().equals("")) {
                Thread.sleep(500);
            }
//            Thread.sleep(3000);
            Assertions.assertEquals("成功上传", driver.findElement(By.className("el-message__content")).getText());
        }
    }

    /**
     * 测试下载功能列表的Excel文件
     *
     * @param
     * @return void
     * @author 田家旭
     * @date 2020/4/2 8:10 下午
     **/
    @Order(2)
    @Test
    void downloadExcelTest() throws InterruptedException {
        if (flag) {
            Thread.sleep(5000);
        }
        WebElement downloadFunctionListButton = getDownloadFunctionListButton();
        downloadFunctionListButton.click();
//        Thread.sleep(1000);
        WebElement message = driver.findElement(By.className("el-message__content"));
        while (message.getText() == null || message.getText().equals("")) {
            Thread.sleep(500);
        }
        Assertions.assertEquals("下载成功", message.getText());
    }





    private WebElement getExcelUploadInput() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("excel-upload-input"));
    }

    private WebElement getHandleUploadButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("handleUpload"));
    }

    private WebElement getUploadFunctionListButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("uploadFunctionList"));
    }

    private WebElement getDownloadFunctionListButton() {
        if (driver == null) {
            return null;
        }
        return driver.findElement(By.name("downloadFunctionList"));
    }
}
