package tests;

import extensions.DriverExtension;
import extensions.ReportExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
@ExtendWith({DriverExtension.class, ReportExtension.class})
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = DriverManager.getDriver();
        driver.get("https://www.btl.gov.il/");
    }

    @AfterAll
    public static void closeDriver() {
        DriverManager.quitDriver(); // רק בסוף כל הריצה!
    }
}
