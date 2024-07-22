package automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BaseMenuPage extends BasePage {

    public BaseMenuPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "withdraw-menu-item")
    protected WebElement withdrawBtn;

    @FindBy(xpath = "//*[@id='transactionTable']/tbody/tr")
    protected List<WebElement> transactions;

    @FindBy(id = "transfer-menu-item")
    protected WebElement transferBtn;

    @FindBy(id = "checking-menu")
    protected WebElement checkingBtn;

    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingBtn;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingBtn;

    @FindBy(id = "external-accounts-menu")
    protected WebElement externalBtn;

    @FindBy(id = "link-external-menu-item")
    protected WebElement linkExternalAccountBtn;

    @FindBy(id = "view-external-menu-item")
    protected WebElement viewExternalAccounts;

    @FindBy(id = "deposit-menu-item")
    protected WebElement depositBtn;

    @FindBy(id = "home-menu-item")
    protected WebElement homeBtn;

    public void goToHomePage() {
        homeBtn.click();
    }


}
