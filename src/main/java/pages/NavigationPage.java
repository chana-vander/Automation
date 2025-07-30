package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage extends BtlBasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    private WebElement headerTitle;

    // בדיקה שהכותרת מכילה טקסט מסוים
    public boolean isAtPage(String expectedText) {
        return headerTitle.getText().contains(expectedText);
    }

    public String getPageTitle() {
        return headerTitle.getText();
    }
}