package automation.ui.steps;

import automation.ui.pages.NewCheckingAccountPage;
import automation.ui.pages.TransferPage;
import automation.ui.utils.Driver;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class TransferSteps {
    WebDriver driver = Driver.getDriver();
    NewCheckingAccountPage newCheckingAccountPage = new NewCheckingAccountPage(driver);
    TransferPage transferPage = new TransferPage(driver);


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @When("The user creates checking two accounts with following data")
    public void theUserCreatesCheckingTwoAccountsWithFollowingData(List<Map<String, String >> checkingAccountInfo) {
        newCheckingAccountPage.createCheckingAccount(checkingAccountInfo);
        WebElement actualMessage1 = driver.findElement(By.id("new-account-msg"));

        Assertions.assertEquals(newCheckingAccountPage.getExpectedMessageForTheFirstAccount(checkingAccountInfo)
                ,actualMessage1.getText(), "Wrong green message 1");

        newCheckingAccountPage.createSecondRowCheckingAccount(checkingAccountInfo);
        WebElement actualMessage2 = driver.findElement(By.id("new-account-msg"));
        Assertions.assertEquals(newCheckingAccountPage.getExpectedMessageForTheSecondAccount(checkingAccountInfo)
                ,actualMessage2.getText(), "Wrong green message 2 ");

    }

    @When("The user is on Transfer page, fills all following information and click submit button")
    public void theUserIsOnTransferPageFillsAllFollowingInformationAndClicksSubmitButton(List<Map<String, String>> transferInfo) {
        transferPage.transferSubmit(transferInfo);
    }

    @Then("The user should see the following {string} required field error message {string} on transfer page")
    public void theUserShouldSeeTheFollowingRequiredFieldErrorMessageOnTransferPage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = transferPage.getRequiredFieldErrorMessage(fieldName);
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message of required " + fieldName
                +" is mismatch");
        System.out.println(actualErrorMessage);
    }

    @Then("The user should see red message about transfer is more than the balance {string}")
    public void theUserShouldSeeRedMessageAboutTransferIsMoreThanTheBalance(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, transferPage.getErrorMessageAboutInvalidAmount());
    }
}
