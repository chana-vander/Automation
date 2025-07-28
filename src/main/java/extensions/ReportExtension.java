package extensions;

import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportManager;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class ReportExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback, TestWatcher, ParameterResolver {

    private static ExtentTest test;

    @Override
    public void beforeAll(ExtensionContext context) {
        ReportManager.getReport();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        ReportManager.flushReport();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        test = ReportManager.getReport().createTest(testName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        // Nothing here, handled in TestWatcher
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        test.fail(cause);
        attachScreenshot(); // ✅ קודם כל לצלם
        DriverManager.quitDriver(); // ✅ רק אחר כך לסגור
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        test.pass("Test passed");
        DriverManager.quitDriver(); // גם אחרי הצלחה סוגרים

    }

    private void attachScreenshot() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null && driver instanceof TakesScreenshot) {
            try {
                // ודא שהדף נטען לחלוטין
                new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(d -> ((JavascriptExecutor) d)
                                .executeScript("return document.readyState").equals("complete"));

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String destDir = "screenshots";
                String dest = destDir + "/" + System.currentTimeMillis() + ".png";

                Files.createDirectories(Paths.get(destDir));
                Files.copy(src.toPath(), Paths.get(dest));

                test.addScreenCaptureFromPath(dest);
            } catch (IOException e) {
                test.warning("Screenshot failed: " + e.getMessage());
            } catch (Exception e) {
                test.warning("Unexpected error during screenshot: " + e.getMessage());
            }
        } else {
            test.warning("Driver is null or doesn't support screenshots");
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return null;
    }
}