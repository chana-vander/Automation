package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import pages.BtlBasePage;
import enums.MainMenu;
import pages.NavigationPage;

public class NavigationTest extends BaseTest {

    @ParameterizedTest(name = "Navigate to קצבאות והטבות -> {0} and check title contains {1}")
    @CsvSource({
            "אבטלה, אבטלה",
            "דמי לידה, דמי לידה",
            "נכות כללית, נכות כללית",
            "מענק בעבודה נדרשת, מענק לחייל משוחרר בעבודה נדרשת (מועדפת)",
            "ניידות, ניידות"
    })
    public void testNavigateFromBenefitsMenu(String subMenu, String expectedTitlePart) {
        BtlBasePage btlBasePage = new BtlBasePage(driver);
        // נווט לתפריט קצבאות והטבות
        btlBasePage.clickMenu(MainMenu.קצבאות);
        // נווט לתת-תפריט
        btlBasePage.clickSubMenu(subMenu);

        NavigationPage navigationPage = new NavigationPage(driver);
        String actualTitle = navigationPage.getPageTitle(); // שליפת כותרת הדף
        System.out.println("🔹 כותרת שנמצאה בדף: " + actualTitle);
        System.out.println("🔹 טקסט שציפיתי לראות: " + expectedTitlePart);

        Assertions.assertTrue(actualTitle.contains(expectedTitlePart), "❌ לא הגעת לדף הנכון!");
    }
}
