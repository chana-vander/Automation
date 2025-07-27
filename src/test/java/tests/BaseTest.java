package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

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
