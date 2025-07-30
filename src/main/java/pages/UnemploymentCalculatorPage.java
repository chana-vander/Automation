package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UnemploymentCalculatorPage extends BtlBasePage {
    public UnemploymentCalculatorPage(WebDriver driver) {
        super(driver);
    }

    // כותרת ראשית של עמוד המחשבון
    @FindBy(tagName = "h1")
    private WebElement calculatorTitle;

    // בדיקה אם הגענו לעמוד הנכון
    public boolean isAtCalculatorPage() {
        return calculatorTitle.getText().contains("חישוב סכום דמי אבטלה");
    }

    //שדה מילוי תאריך הפסקת עבודה
    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_DynDatePicker_PiturimDate_Date")
    private WebElement terminationDateInput;

    //רדיו גיל
    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_rdb_age_1_lbl")
    private WebElement ageRadio;

    //לחיצה על המשך
    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StartNavigationTemplateContainerID_StartNextButton")
    private WebElement nextButton;

    //מילוי העמוד הראשון של האבטלה
    public void fillStepOne(String terminationDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(terminationDateInput)).clear();
        terminationDateInput.sendKeys(terminationDate);
        ageRadio.click();
        nextButton.click();
    }

    // כותרת של הצעד השני
    @FindBy(css = ".txtbox_sallary")
    private List<WebElement> incomeInputs;

    //מילוי משכורות
    public void fillSalaries(List<String> salaries) {
        System.out.println("🔍 מתחיל למלא משכורות...");
        System.out.println(" נמצאו " + incomeInputs.size() + "שדות להכנסת משכורות.");
        System.out.println(" נשלחו " + salaries.size() + " ערכי משכורת.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(".txtbox_sallary"), 0));

        for (int i = 0; i < incomeInputs.size() && i < salaries.size(); i++) {
            WebElement input = incomeInputs.get(i);
            String salary = salaries.get(i);
            try {
                System.out.println("✏️ ממלא שדה מספר " + (i + 1) + " ב־" + salary);
                input.clear();
                input.sendKeys(salary);
            } catch (Exception e) {
                System.out.println("❌ שגיאה במילויהמשכורת של שדה מספר " + (i + 1) + ": " + e.getMessage());
            }
        }
        System.out.println("✅ סיימנו למלאות את המשכורות.");
    }

    // כפתור המשך
    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StepNavigationTemplateContainerID_StepNextButton")
    private WebElement StepNextButton;

    //לחיצה על המשך
    public void clickStepNextButton() {
        StepNextButton.click();
    }

    //דף תוצאות חישוב
    @FindBy(tagName = "h1")
    private WebElement HeaderTitle;

    //בדיקה אם הגענו לדף תוצאות חישוב
    public boolean isHeaderTitle() {
        return HeaderTitle.getText().contains("חישוב סכום דמי אבטלה");
    }

    //בדיקה שקיימות תוצאות החישוב
    @FindBy(xpath = "//*[contains(text(),'שכר יומי ממוצע לצורך חישוב דמי אבטלה')]")
    private WebElement averageDailyWage;

    @FindBy(xpath = "//*[contains(text(),'דמי אבטלה ליום')]")
    private WebElement dailyUnemploymentBenefit;

    @FindBy(xpath = "//*[contains(text(),'דמי אבטלה לחודש (לפי 25 ימים):  ')]")
    private WebElement monthlyUnemploymentBenefit;

    public boolean isAverageDailyWageDisplayed() {
        return averageDailyWage.isDisplayed();
    }

    public boolean isDailyUnemploymentBenefitDisplayed() {
        return dailyUnemploymentBenefit.isDisplayed();
    }

    //    public boolean isMonthlyUnemploymentBenefitDisplayed() {
    //    return monthlyUnemploymentBenefit.isDisplayed();
    //    }

    public boolean isMonthlyUnemploymentBenefitDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(monthlyUnemploymentBenefit));
            return monthlyUnemploymentBenefit.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}