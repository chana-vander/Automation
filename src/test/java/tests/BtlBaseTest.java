package tests;

import extensions.DriverExtension;
import extensions.ReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BranchesPage;
import pages.BtlBasePage;
import pages.HomePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({DriverExtension.class, ReportExtension.class})
public class BtlBaseTest extends BaseTest{

    private BtlBasePage btlBasePage;
    private HomePage homePage;
    private BranchesPage branchesPage;

    @BeforeEach
    public void setUp() {
        btlBasePage = new BtlBasePage(driver) {
        }; // יצירת מופע אנונימי של BtlBasePage
        homePage=new HomePage(driver){};
    }
    //search
    //בה עבד וכתב:
    //Tests passed : 1 of 1 test
    @Test
    public void testSearchResults() {
        homePage.searchFor("חישוב סכום דמי לידה ליום");

        // המתנה עד שהטקסט יופיע ב-body של העמוד
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "תוצאות חיפוש"));

        // עכשיו האסרטיב עושה בדיקה אמיתית אחרי שהטקסט כבר קיים
        assertTrue(driver.getPageSource().contains("תוצאות חיפוש"),
                "לא נמצאה תוצאת חיפוש מתאימה!");
    }

    //branches
    @Test
    public void testBranchesPage() {
        BranchesPage branchesPage = new BranchesPage(driver);

        // נכנסים לעמוד הסניפים ומחכים לטעינה
        branchesPage.clickOnBranchesAndRe();
        assertTrue(branchesPage.isAt(), "לא הגעת לעמוד סניפים");

        // לוחצים על הסניף הראשון ברשימה
        branchesPage.clickFirstBranch();

        // בודקים שמופיעים הפרטים הנדרשים
        assertTrue(branchesPage.hasBranchInfo(), "פרטי הסניף אינם מופיעים כראוי");
    }

}
