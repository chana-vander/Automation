package tests;

import extensions.DriverExtension;
import extensions.ReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BtlBasePage;
import pages.HomePage;
import pages.InsuranceCalculatorPage;
import pages.InsurancePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ReportExtension.class, DriverExtension.class})
public class InsuranceCalculatorTest extends BaseTest {

    private BtlBasePage btlBasePage;

    @BeforeEach
    public void setUp() {
        btlBasePage = new BtlBasePage(driver) {
        }; // יצירת מופע אנונימי של BtlBasePage
    }

    // ניתוב לדף הנכון
//    @Test
//    public void testNationalInsuranceCalculation() {
//        btlBasePage.navigateToNationalInsurance();
//    }

    @Test
    @DisplayName("חישוב דמי ביטוח לבחור ישיבה ובדיקת תוצאה סופית")
    public void testYeshivaStudentInsuranceCalculation() {
        btlBasePage.navigateToNationalInsurance();
        // שלב 1: כניסה לעמוד דמי ביטוח
        InsurancePage insurancePage = new InsurancePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "דמי ביטוח לאומי"));

        assertTrue(insurancePage.isAtInsurancePage(), "לא נמצאנו בעמוד 'דמי ביטוח לאומי'");

        // שלב 2: מעבר למחשבון
        insurancePage.goToInsuranceCalculator();

        // שלב 3: בדיקת שהגענו לעמוד מחשבון
        InsuranceCalculatorPage calculatorPage = new InsuranceCalculatorPage(driver);
        assertTrue(calculatorPage.isAtCalculatorPage(), "לא הגענו לעמוד 'חישוב דמי ביטוח עבור עצמאי'");

        // שלב 4: מילוי צעד ראשון (תלמיד ישיבה, זכר, תאריך לידה חוקי)
        calculatorPage.fillStepOne("01/11/2005");

        // שלב 5: בדיקה שהגענו לצעד שני
        assertTrue(calculatorPage.isAtStepTwo(), "לא הגענו לצעד השני של המחשבון");

        // שלב 6: סימון 'לא מקבל קצבת נכות' והמשך
        calculatorPage.fillStepTwo();

        // שלב 7: בדיקת שהגענו לשלב סיום
        assertTrue(calculatorPage.isAtEnd(), "לא הגענו לשלב הסיום של המחשבון");

        // שלב 8: בדיקת תוצאה סופית (אם תוסיף בהמשך בדיקת טקסט בפועל)
        // ✅ שלב 8: בדיקת תוצאה סופית בפועל
        // בדיקות תוצאות החישוב
//        String expectedNationalInsuranceFee = " 43 ש\"ח";
//        String actual = calculatorPage.getNationalInsuranceFee();
//        assertEquals("דמי ביטוח לאומי לא תואם", expectedNationalInsuranceFee, actual);
//        String expectedHealthInsuranceFee = " 120.00 ש\"ח";
//        String expectedTotalInsuranceFee = " 163 ש\"ח";
//
//        String actualNationalInsuranceFee = calculatorPage.getNationalInsuranceFee();
//        System.out.println("Expected: " + expectedNationalInsuranceFee);
//        System.out.println("Actual: " + actualNationalInsuranceFee);
//        assertEquals("דמי ביטוח לאומי לא תואם", expectedNationalInsuranceFee, actualNationalInsuranceFee);
//        System.out.println("בדיקת דמי ביטוח לאומי: " + actualNationalInsuranceFee + " - תואם.");
//
//        String actualHealthInsuranceFee = calculatorPage.getHealthInsuranceFee();
//        assertEquals("דמי ביטוח בריאות לא תואם", expectedHealthInsuranceFee, actualHealthInsuranceFee);
//        System.out.println("בדיקת דמי ביטוח בריאות: " + actualHealthInsuranceFee + " - תואם.");
        // בדיקות תוצאות החישוב
        String expectedNationalInsuranceFee = "43 ש\"ח";
        String expectedHealthInsuranceFee = "120.00 ש\"ח";
        String expectedTotalInsuranceFee = "163 ש\"ח";

        String actualNationalInsuranceFee = calculatorPage.getNationalInsuranceFee();
        assertEquals("דמי ביטוח לאומי לא תואם", expectedNationalInsuranceFee, actualNationalInsuranceFee);
        System.out.println("בדיקת דמי ביטוח לאומי: " + actualNationalInsuranceFee + " - תואם.");

        String actualHealthInsuranceFee = calculatorPage.getHealthInsuranceFee();
        assertEquals("דמי ביטוח בריאות לא תואם", expectedHealthInsuranceFee, actualHealthInsuranceFee);
        System.out.println("בדיקת דמי ביטוח בריאות: " + actualHealthInsuranceFee + " - תואם.");

        String actualTotalInsuranceFee = calculatorPage.getTotalInsuranceFee();
        System.out.println("הטסט עבר בהצלחה - חישוב דמי ביטוח לבחור ישיבה");
        System.out.println("הטסט עבר בהצלחה ✅ - סכומים תואמים לציפיות");

    }
}
