package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InsuranceCalculatorPage extends BtlBasePage {

    public InsuranceCalculatorPage(WebDriver driver) {
        super(driver);
    }

    // כותרת ראשית של עמוד המחשבון
    @FindBy(tagName = "h1")
    private WebElement calculatorTitle;

//    public boolean isAtCalculatorPage() {
//        return calculatorTitle.getText().contains("חישוב דמי ביטוח עבור עצמאי");
//    }

    public boolean isAtCalculatorPage() {
        return calculatorTitle.getText().contains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד");
    }

    // ======== צעד ראשון ========
    //בחירת הרדיו : בחור ישיבה
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2")
    private WebElement yeshivaStudentRadio;

    //בחירת הרדיו : זכר
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0")
    private WebElement maleRadio;

    //תאריך לידה
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date")
    private WebElement birthDateInput;

    //כפתור הבא
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton")
    private WebElement nextButton;

    // מילוי הצעד הראשון של המחשבון
    public void fillStepOne(String birthDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(yeshivaStudentRadio)).click();
        wait.until(ExpectedConditions.elementToBeClickable(maleRadio)).click();
        wait.until(ExpectedConditions.visibilityOf(birthDateInput)).clear();
        birthDateInput.sendKeys(birthDate);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    // ======== צעד שני ========

    // כותרת של הצעד השני
    @FindBy(css = "h2#header")
    private WebElement stepTwo;

    // בדיקה אם הגענו לשלב השני של המחשבון
    // מחכים שהכותרת תופיע ושהטקסט יהיה "צעד שני"
    // אם הכותרת לא מופיעה או הטקסט שגוי,
    // נחזיר false ונדפיס הודעת שגיאה
    // אם הכותרת נכונה, נחזיר true
    // אם יש שגיאה, נדפיס הודעת שגיאה
    // ונחזיר false
//    public boolean isAtStepTwo() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        try {
//            wait.until(ExpectedConditions.visibilityOf(stepTwo));
//            wait.until(ExpectedConditions.textToBePresentInElement(stepTwo, "צעד שני"));
//            return stepTwo.getText().trim().equals("צעד שני");
//        } catch (Exception e) {
//            System.out.println("שגיאה בהגעה לשלב השני: " + e.getMessage());
//            return false;
//        }
//    }
//    public boolean isAtStepTwo() {
//        System.out.println("בודק אם הגענו לשלב השני של המחשבון...");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        try {
//            // המתן שהאלמנט יופיע בדף
//            wait.until(ExpectedConditions.visibilityOf(stepTwo));
//
//            // המתן שהטקסט יכיל את "צעד שני"
//            wait.until(ExpectedConditions.textToBePresentInElement(stepTwo, "צעד שני"));
//
//            // קריאה לטקסט בפועל
//            String text = stepTwo.getText().trim();
//            System.out.println("טקסט באלמנט: '" + text + "'");
//
//            // בדיקה סופית
//            return text.equals(" צעד שני");
//
//        } catch (Exception e) {
//            System.out.println("שגיאה בהגעה לשלב השני: " + e.getMessage());
//            return false;
//        }
//    }
    public boolean isAtStepTwo() {
        System.out.println("בודק אם הגענו לשלב השני של המחשבון...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            // המתן שהאלמנט יופיע בדף
            wait.until(ExpectedConditions.visibilityOf(stepTwo));

            // המתן שהטקסט יכיל את "צעד שני"
            wait.until(ExpectedConditions.textToBePresentInElement(stepTwo, "צעד שני"));

            // קריאה לטקסט בפועל עם טרימינג של רווחים מיותרים
            String text = stepTwo.getText().trim();
            System.out.println("טקסט באלמנט: '" + text + "'");

            // בדיקה שהטקסט **מכיל** את המחרוזת "צעד שני" (ולא שווה לה במדויק)
            return text.contains("צעד שני");

        } catch (Exception e) {
            System.out.println("שגיאה בהגעה לשלב השני: " + e.getMessage());
            return false;
        }
    }

//    public boolean isAtStepTwo() {
//        System.out.println("בודק אם הגענו לשלב השני של המחשבון...");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        try {
//            wait.until(ExpectedConditions.visibilityOf(stepTwo));
//            String text = stepTwo.getText().trim();
//            System.out.println("טקסט באלמנט: '" + text + "'");
//            return text.contains("צעד שני");
//        } catch (Exception e) {
//            System.out.println("שגיאה בהגעה לשלב השני: " + e.getMessage());
//            return false;
//        }
//    }

    // בחירת הרדיו: לא מקבל קצבת נכות
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1")
    private WebElement noDisabilityRadio;

    // כפתור הבא לשלב שני
    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton")
    private WebElement nextStepTwoButton;

    //    // מילוי הצעד השני של המחשבון
//    public void fillStepTwo() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.until(ExpectedConditions.elementToBeClickable(noDisabilityRadio));
//        noDisabilityRadio.click();  // קליק רגיל
//
//        wait.until(ExpectedConditions.elementToBeClickable(nextStepTwoButton));
//        nextStepTwoButton.click();  // קליק רגיל
//    }
// מילוי הצעד השני של המחשבון עם קליקים ב-JS
    public void fillStepTwo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(noDisabilityRadio));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noDisabilityRadio);

        wait.until(ExpectedConditions.elementToBeClickable(nextStepTwoButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextStepTwoButton);
    }

    // ======== סיום המחשבון ========

    //חיפוש כותרת הסיום של המחשבון
    @FindBy(xpath = "//h2[contains(text(), 'סיום')]")
    private WebElement end;

    // בדיקה אם הגענו לסיום המחשבון
    public boolean isAtEnd() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(end));
            return end.getText().contains("סיום");
        } catch (TimeoutException e) {
            System.out.println("שלב סיום לא הופיע בזמן: " + e.getMessage());
            return false;
        }
    }
    public String getNationalInsuranceFee() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nationalInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[1]")));
        return nationalInsuranceElement.getText() + " ש\"ח";
    }

    // פונקציה להוציא את דמי הביטוח הבריאות
    public String getHealthInsuranceFee() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement healthInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[2]")));
        return healthInsuranceElement.getText() + " ש\"ח";
    }

    // פונקציה להוציא את סך הכל דמי הביטוח
    public String getTotalInsuranceFee() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[3]")));
        return totalInsuranceElement.getText() + " ש\"ח";
    }
//    public String getNationalInsuranceFee() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement nationalInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[1]")));
//        return nationalInsuranceElement.getText() + " ש\"ח";
//    }
//
//    // פונקציה להוציא את דמי הביטוח הבריאות
////    public String getHealthInsuranceFee() {
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////        WebElement healthInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[1]")));
////        return healthInsuranceElement.getText() + " ש\"ח";
////    }
//    public String getHealthInsuranceFee() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement healthInsuranceElement = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//ul[@class='CalcResult']/li[2]/strong")
//                )
//        );
//        return healthInsuranceElement.getText().trim() + " ש\"ח";
//    }
//
//    // פונקציה להוציא את סך הכל דמי הביטוח
//    public String getTotalInsuranceFee() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement totalInsuranceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CalcResult']/li/strong[3]")));
//        return totalInsuranceElement.getText() + " ש\"ח";
//    }

}
