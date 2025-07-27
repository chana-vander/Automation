package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class BranchesPage extends BasePage {

    public BranchesPage(WebDriver driver) {
        super(driver);
    }
    //סניפים
    @FindBy(id="ctl00_Topmneu_BranchesHyperLink") // ← זה צריך להיות ה-ID של כפתור "סניפים"
    private WebElement branchesButton;

    //כל הסניפים ברשימה
    @FindBy(className = "SnifName") // כל הסניפים ברשימה
    private List<WebElement> branchesList;

    public void clickOnBranches() {
        branchesButton.click();
    }

    public BranchesPage clickOnBranchesAndRe() {
        branchesButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("סניפים וערוצי שירות"));
        return this;
    }


    public boolean isAt() {
        return driver.getTitle().contains("סניפים וערוצי שירות");
    }

//    public void clickFirstBranch() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement firstBranch = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".branch-item:first-child a")));
//        firstBranch.click();
//    }

    public void clickFirstBranch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(branchesList));

        if (!branchesList.isEmpty()) {
            branchesList.get(0).click();
        } else {
            throw new RuntimeException("לא נמצאו סניפים ברשימה");
        }
    }


    public boolean hasBranchInfo() {
        return driver.getPageSource().contains("כתובת") &&
                driver.getPageSource().contains("קבלת קהל") &&
                driver.getPageSource().contains("מענה טלפוני");
    }

}