package automation.ui.pages;

import automation.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;
import java.util.Map;

import static automation.ui.utils.Driver.getDriver;

public class NewCheckingAccountPage extends BaseMenuPage {

    public NewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingRadio;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingRadio;

    @FindBy(id = "Individual")
    private WebElement individualRadio;

    @FindBy(id = "Joint")
    private WebElement jointRadio;

    @FindBy(id = "name")
    private WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    private WebElement initialDepositAmountTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;

    @FindBy(id = "newCheckingReset")
    private WebElement resetBtn;

    public void createCheckingAccount(List<Map<String, String >> newCheckingInfo) {
        checkingBtn.click();
        newCheckingBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("createNewChecking"), getDriver().getCurrentUrl());
        Map<String, String > firstRow = newCheckingInfo.get(0);

        if (firstRow.get("checkingAccountType").equalsIgnoreCase("Standard Checking")) {
            standardCheckingRadio.click();
        } else if (firstRow.get("checkingAccountType").equalsIgnoreCase("Interest Checking")) {
            interestCheckingRadio.click();
        }

        if (firstRow.get("accountOwnership").equalsIgnoreCase("Individual")) {
            individualRadio.click();
        } else if (firstRow.get("accountOwnership").equalsIgnoreCase("Joint")) {
            jointRadio.click();
        }
        accountNameTxtBox.sendKeys(firstRow.get("accountName"));
        initialDepositAmountTxtBox.sendKeys(firstRow.get("initialDepositAmount"));
        submitBtn.click();
    }

    public void createSecondRowCheckingAccount(List<Map<String, String >> newCheckingInfo) {
        checkingBtn.click();
        newCheckingBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("createNewChecking"), getDriver().getCurrentUrl(), "The user is not on Create new checking account page");
        Map<String, String > secondRow = newCheckingInfo.get(1);

        if (secondRow.get("checkingAccountType").equalsIgnoreCase("Standard Checking")) {
            standardCheckingRadio.click();
        } else if (secondRow.get("checkingAccountType").equalsIgnoreCase("Interest Checking")) {
            interestCheckingRadio.click();
        }

        if (secondRow.get("accountOwnership").equalsIgnoreCase("Individual")) {
            individualRadio.click();
        } else if (secondRow.get("accountOwnership").equalsIgnoreCase("Joint")) {
            jointRadio.click();
        }
        accountNameTxtBox.sendKeys(secondRow.get("accountName"));
        initialDepositAmountTxtBox.sendKeys(secondRow.get("initialDepositAmount"));
        submitBtn.click();
    }

    public String getExpectedMessageForTheFirstAccount(List<Map<String, String >> newCheckingInfo) {
        Map<String, String > firstRow = newCheckingInfo.get(0);
        return firstRow.get("message");
    }

    public String getExpectedMessageForTheSecondAccount(List<Map<String, String >> newCheckingInfo) {
        Map<String, String > secondRow = newCheckingInfo.get(1);
        return secondRow.get("message");
    }


}
