package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UnemploymentPage extends BtlBasePage {

    public UnemploymentPage(WebDriver driver) {
        super(driver);
    }
    // כותרת העמוד הראשי
    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    // קישור למחשבון
    @FindBy(linkText = "למחשבוני דמי אבטלה")
    private WebElement calculatorLink;

    @FindBy(linkText = "חישוב דמי אבטלה")
    private WebElement unemploymentCalculatorLink;

    // בדיקה שאנחנו בעמוד הנכון
    public boolean isAtInsurancePage() {
        return pageTitle.getText().contains("אבטלה");
    }

    // מעבר לעמוד המחשבון
    public void goToInsuranceCalculator() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(calculatorLink));
        calculatorLink.click();
    }

    // מעבר לעמוד חישוב דמי אבטלה
    public void goToUnemploymentCalculator() {
        unemploymentCalculatorLink.click();
    }
}
