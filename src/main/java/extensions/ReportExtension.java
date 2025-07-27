package extensions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ReportManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class ReportExtension implements BeforeAllCallback, AfterAllCallback, TestWatcher {
    private static ExtentReports extent;
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ReportExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        extent = ReportManager.getReporter();
        context.getStore(NAMESPACE).put("extent", extent);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        ExtentReports ext = (ExtentReports) context.getStore(NAMESPACE).get("extent", ExtentReports.class);
        if (ext != null) ext.flush();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ExtentTest test = getOrCreateTest(context);
        WebDriver driver = extensions.DriverExtension.getDriver(context);
        attachScreenshot(driver, context.getDisplayName(), test);
        test.fail("טסט נכשל: " + cause.getMessage());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        ExtentTest test = getOrCreateTest(context);
        test.pass("עבר בהצלחה");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        ExtentTest test = getOrCreateTest(context);
        test.skip("הטסט נוטרל: " + reason.orElse("לא צוינה סיבה"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        ExtentTest test = getOrCreateTest(context);
        test.skip("הטסט בוטל: " + cause.getMessage());
    }

    private ExtentTest getOrCreateTest(ExtensionContext context) {
        ExtentTest test = (ExtentTest) context.getStore(NAMESPACE).get(context.getDisplayName(), ExtentTest.class);
        if (test == null) {
            ExtentReports ext = (ExtentReports) context.getStore(NAMESPACE).get("extent", ExtentReports.class);
            if (ext == null) ext = extent;
            test = ext.createTest(context.getDisplayName());
            context.getStore(NAMESPACE).put(context.getDisplayName(), test);
        }
        return test;
    }

    private void attachScreenshot(WebDriver driver, String name, ExtentTest test) {
        if (driver == null) {
            test.warning("לא ניתן לצלם מסך כי הדרייבר לא מאותחל");
            return;
        }
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dirPath = "screenshots/";
            new File(dirPath).mkdirs();
            String path = dirPath + name + ".png";
            Files.copy(src.toPath(), new File(path).toPath());
            test.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            test.warning("שגיאה בצילום מסך: " + e.getMessage());
        }
    }
}
