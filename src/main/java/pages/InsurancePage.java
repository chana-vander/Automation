package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsurancePage extends BtlBasePage {

    public InsurancePage(WebDriver driver) {
        super(driver);
    }
    // כותרת העמוד הראשי
    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    // קישור למחשבון
    @FindBy(linkText = "מחשבון לחישוב דמי הביטוח")
    private WebElement calculatorLink;

    // בדיקה שאנחנו בעמוד הנכון
    public boolean isAtInsurancePage() {
        return pageTitle.getText().contains("דמי ביטוח לאומי");
    }

    // מעבר לעמוד המחשבון
    public void goToInsuranceCalculator() {
        calculatorLink.click();
    }
}