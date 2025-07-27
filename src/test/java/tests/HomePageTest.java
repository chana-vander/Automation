//package tests;
//
//import extensions.DriverExtension;
//import extensions.ReportExtension;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import pages.BranchesPage;
//import pages.HomePage;
//import pages.BtlBasePage;
//
//import java.time.Duration;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith({DriverExtension.class, ReportExtension.class})
//public class HomePageTest {
//
//    @BeforeAll
//    public static void globalSetUp() {
//        // אפשר להוסיף כאן לוגיקה גלובלית אם צריך
//    }
//
//    @AfterAll
//    public static void globalTearDown() {
//        // אפשר להוסיף כאן לוגיקה גלובלית אם צריך
//    }
//
//    @Test
//    public void testSearchBirthAllowance(WebDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        HomePage homePage = new HomePage(driver);
////        homePage.navigateTo();
////
////        // המתנה מפורשת #1 - בדיקה שהכותרת נכונה
////        wait.until(ExpectedConditions.titleContains("דף הבית, הביטוח הלאומי"));
////        assertTrue(homePage.isAt());
////
////        // ביצוע חיפוש
////        homePage.search("חישוב סכום דמי לידה ליום");
//
//        // המתנה מפורשת #2 - המתנה שהטקסט של תוצאות החיפוש יופיע
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'תוצאות חיפוש עבור חישוב סכום דמי לידה ליום')]")));
//
//        assertTrue(driver.getPageSource().contains("תוצאות חיפוש עבור חישוב סכום דמי לידה ליום"));
//    }
//
//    @Test
//    public void testBranchesPage(WebDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        HomePage homePage = new HomePage(driver);
////        homePage.navigateTo();
//
//        BranchesPage branchesPage = homePage.goToBranchesPage();
//
//        // המתנה מפורשת לבדיקה שדף הסניפים עלה
//        wait.until(ExpectedConditions.titleContains("סניפים וערוצי שירות"));
//        assertTrue(branchesPage.isAt());
//
//        branchesPage.clickFirstBranch();
//
//        // המתנה שהמידע על הסניף ייטען (למשל ש"כתובת" תופיע)
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'כתובת')]")));
//
//        assertTrue(branchesPage.hasBranchInfo());
//    }
//
//    @Test
//    public void testYeshivaStudentInsurance(WebDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        HomePage homePage = new HomePage(driver);
////        homePage.navigateTo();
//
//        // כניסה לדמי ביטוח -> דמי ביטוח לאומי
//        BtlBasePage basePage = new BtlBasePage(driver);
//        basePage.clickMenu(enums.MainMenu.דמי_ביטוח);
//        basePage.clickSubMenu("דמי ביטוח לאומי");
//
//        // בדיקה שהגענו לדף דמי ביטוח לאומי
//        wait.until(ExpectedConditions.titleContains("דמי ביטוח לאומי"));
//        assertTrue(driver.getTitle().contains("דמי ביטוח לאומי"));
//
//        // כניסה למחשבון
//        basePage.clickSubMenu("מחשבון לחישוב דמי ביטוח");
//
//        // בדיקה שהגענו לדף מחשבון
//        wait.until(ExpectedConditions.titleContains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד"));
//        assertTrue(driver.getTitle().contains("חישוב דמי ביטוח עבור עצמאי"));
//
//        // שלב ראשון: הזנת תאריך לידה (1/11/2006)
//        driver.findElement(By.id("BirthDateInput")).sendKeys("01/11/2006");
//        driver.findElement(By.cssSelector("button.next-step")).click();
//
//        // שלב שני: בחירת "לא" לקצבת נכות
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'מקבל קצבת נכות')]")));
//        driver.findElement(By.xpath("//label[contains(text(),'לא')]/preceding-sibling::input[@type='radio']")).click();
//        driver.findElement(By.cssSelector("button.next-step")).click();
//
//        // שלב סיום: בדיקת תוצאות
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'סך הכל דמי ביטוח לחודש')]")));
//        String pageSource = driver.getPageSource();
//        assertTrue(pageSource.contains("דמי ביטוח לאומי : 43"));
//        assertTrue(pageSource.contains("דמי ביטוח בריאות : 120.00"));
//        assertTrue(pageSource.contains("סך הכל דמי ביטוח לחודש : 163"));
//    }
//}
