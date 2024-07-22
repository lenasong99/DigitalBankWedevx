package automation.ui.pages;

import automation.ui.utils.ConfigReader;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class WithdrawPage extends BaseMenuPage {

    public WithdrawPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "selectedAccount")
    private WebElement accountDropDown;

    @FindBy(id = "amount")
    private WebElement withdrawAmountTxtBox;

    @FindBy(xpath = "//button[text()=' Submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//button[text()=' Reset']")
    private WebElement resetBtn;

    @FindBy(xpath = "//span[contains(text(), 'The withdraw amount')]")
    private WebElement errorMessageAboutLimit;

    public String getOverdraftLimitMessage() {
        return errorMessageAboutLimit.getText();
    }


    public void withdrawSubmit(List<Map<String, String>> withdrawInfo) {
        withdrawBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("withdrawPage"), driver.getCurrentUrl());
        Map<String, String> firstRow = withdrawInfo.get(0);
        Select select = new Select(accountDropDown);
        if (firstRow.get("accountForWithdraw") != null) {
            select.selectByVisibleText(firstRow.get("accountForWithdraw"));
        }
        if (firstRow.get("withdrawAmount") != null) {
            withdrawAmountTxtBox.sendKeys(firstRow.get("withdrawAmount"));
        }
        submitBtn.click();
    }

    public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName) {
            case "accountForWithdraw":
                return accountDropDown.getAttribute("validationMessage");
            case "withdrawAmount":
                return withdrawAmountTxtBox.getAttribute("validationMessage");
            default:
                return null;
        }
    }
}
