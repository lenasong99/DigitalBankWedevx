package automation.ui.pages;

import automation.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class TransferPage extends BaseMenuPage {

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "fromAccount")
    private WebElement fromAccountDropDown;

    @FindBy(id = "toAccount")
    private WebElement toAccountDropDown;

    @FindBy(id = "amount")
    private WebElement amountTxtBox;

    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    private WebElement submitBtn;

    @FindBy(xpath = "//button[contains(text(), 'Reset')]")
    private WebElement resetBtn;

    @FindBy(xpath = "//span[contains(text(), 'The amount')]")
    private WebElement errorMessageAboutInvalidAmount;

    public void transferSubmit(List<Map<String, String>> transferInfo) {
        transferBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("transferPage"), driver.getCurrentUrl());
        Map<String, String> firstRow = transferInfo.get(0);
        Select selectFrom = new Select(fromAccountDropDown);
        Select selectTo = new Select(toAccountDropDown);
        if (firstRow.get("fromAccount") != null) {
            selectFrom.selectByVisibleText(firstRow.get("fromAccount"));
        }
        if (firstRow.get("toAccount") != null) {
            selectTo.selectByVisibleText(firstRow.get("toAccount"));
        }
        if (firstRow.get("transferAmount") != null) {
            amountTxtBox.sendKeys(firstRow.get("transferAmount"));
        }
        submitBtn.click();
    }

    public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName) {
            case "fromAccount":
                return fromAccountDropDown.getAttribute("validationMessage");
            case "toAccount":
                return toAccountDropDown.getAttribute("validationMessage");
            case "transferAmount":
                return amountTxtBox.getAttribute("validationMessage");
            default:
                return null;
        }
    }

    public String getErrorMessageAboutInvalidAmount() {
        return  errorMessageAboutInvalidAmount.getText();
    }


}
