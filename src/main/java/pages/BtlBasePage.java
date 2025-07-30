package pages;

import enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BtlBasePage extends BasePage {


    public BtlBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickMenu(MainMenu menuItem) {
        WebElement menu = driver.findElement(By.linkText(menuItem.getText()));
        wait.until(d -> menu.isDisplayed());
        menu.click();
    }

    public void clickSubMenu(String subMenuText) {
      WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(subMenuText)));
      subMenu.click();
    }

    // מעבר לעמוד דמי ביטוח לאומי
    public void navigateToNationalInsurance() {
        System.out.println("Trying to click on menu: " +MainMenu.דמי_ביטוח.getText());
        clickMenu(MainMenu.דמי_ביטוח); // דמי ביטוח
        clickSubMenu("דמי ביטוח לאומי"); // תפריט משנה
    }

    // מעבר לעמוד קצבאות והטבות
    public void navigateToBenefits() {
        System.out.println("Trying to click on menu: " +MainMenu.קצבאות.getText());
        clickMenu(MainMenu.קצבאות); // דמי ביטוח
        clickSubMenu("אבטלה"); // תפריט משנה
    }

    public BranchesPage goToBranchesPage() {
        WebElement branchesLink = driver.findElement(By.linkText("סניפים"));
        branchesLink.click();
        return new BranchesPage(driver);
    }

    //חיפוש
    @FindBy(id = "TopQuestions")
    private WebElement searchBox;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch")
    private WebElement searchButton;

    //פונקציית החיפוש
    public void searchFor(String query) {
        searchBox.clear();
        searchBox.sendKeys(query);
        searchButton.click();
    }

}