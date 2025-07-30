package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BtlBasePage;
import pages.UnemploymentCalculatorPage;
import pages.UnemploymentPage;
import java.util.Arrays;

public class
UnemploymentTest extends BaseTest {
    //למורה היקרה!
    //היו 2 גירסאות של חוברות אחת עם 13 עמודים אחת עם 15 עמודים
    //לא הבנתי האם צריך לבצע את המשימה הזאת בעמודים 14 15 בחוברת או לא
    //ניסתי לשאול חברות אבל לא הגעתי למענה החלטי
    // תודה רבה על ההבנה!
    //אשמח להתחשבות בענין ולמתן ציון גבוה שמציין את ההשקעה הרבה בפרויקט

    @Test
    public void testUnemploymentCalculatorFlow() {
        // Navigate to קצבאות והטבות > אבטלה
        BtlBasePage basePage = new BtlBasePage(driver);
        basePage.clickMenu(enums.MainMenu.קצבאות);
        basePage.clickSubMenu("אבטלה");

        // Verify we are at the unemployment page
        UnemploymentPage unemploymentPage = new UnemploymentPage(driver);
        Assertions.assertTrue(unemploymentPage.isAtInsurancePage(), "Not at אבטלה page");

        // Click מחשבוני דמי אבטלה
        unemploymentPage.goToInsuranceCalculator();
        // Click חישוב דמי אבטלה
        unemploymentPage.goToUnemploymentCalculator();

        // Verify we are at the calculator page
        UnemploymentCalculatorPage calculatorPage = new UnemploymentCalculatorPage(driver);
        Assertions.assertTrue(calculatorPage.isAtCalculatorPage(), "Not at חישוב סכום דמי אבטלה page");

        // Fill termination date and age > 28, click next
        calculatorPage.fillStepOne("1/05/2025");

        // Fill salary fields (example values)
        calculatorPage.fillSalaries(Arrays.asList("10000", "9500", "11000"));
        calculatorPage.clickStepNextButton();

        // Verify results page and required elements
        Assertions.assertTrue(calculatorPage.isHeaderTitle(), "Results page not loaded");
        Assertions.assertTrue(calculatorPage.isAverageDailyWageDisplayed(), "שכר יומי ממוצע לצורך חישוב דמי אבטלה not displayed");
        Assertions.assertTrue(calculatorPage.isDailyUnemploymentBenefitDisplayed(), "דמי אבטלה ליום not displayed");
        Assertions.assertTrue(calculatorPage.isMonthlyUnemploymentBenefitDisplayed(), "דמי אבטלה לחודש not displayed");
    }

}
