package automation.ui.pages;

import automation.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class DepositPage extends BaseMenuPage {

    public DepositPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(id = "selectedAccount")
    private WebElement accountDropDown;

    @FindBy(id = "amount")
    private WebElement depositAmountTxtBox;

    @FindBy(xpath = "//button[text()=' Submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//button[text()=' Reset']")
    private WebElement resetBtn;



    public void depositSubmit(List<Map<String, String >> depositInfo) {
        depositBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("depositPageUrl"), driver.getCurrentUrl());
        Map<String, String> firstRow = depositInfo.get(0);
        Select select = new Select(accountDropDown);
        if (firstRow.get("accountForDeposit") != null) {
            select.selectByVisibleText(firstRow.get("accountForDeposit"));
        }
        if (firstRow.get("depositAmount") != null) {
        depositAmountTxtBox.sendKeys(firstRow.get("depositAmount"));
        }
        submitBtn.click();

    }

    public void depositReset(List<Map<String, String >> depositInfo) {
        depositBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("depositPageUrl"), driver.getCurrentUrl());
        Map<String, String> firstRow = depositInfo.get(0);
        Select select = new Select(accountDropDown);
        if (firstRow.get("accountForDeposit") != null) {
            select.selectByVisibleText(firstRow.get("accountForDeposit"));
        }
        if (firstRow.get("depositAmount") != null) {
            depositAmountTxtBox.sendKeys(firstRow.get("depositAmount"));
        }
        resetBtn.click();
    }

     public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName) {
            case "accountForDeposit":
                return accountDropDown.getAttribute("validationMessage");
            case "depositAmount":
                return depositAmountTxtBox.getAttribute("validationMessage");
            default:
                return null;
        }
     }
}
