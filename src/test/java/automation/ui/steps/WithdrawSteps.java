package automation.ui.steps;

import automation.ui.pages.DepositPage;
import automation.ui.pages.ViewCheckingAccountPage;
import automation.ui.pages.WithdrawPage;
import automation.ui.utils.Driver;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class WithdrawSteps {
    WebDriver driver = Driver.getDriver();
    WithdrawPage withdrawPage = new WithdrawPage(driver);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }


    @When("The user enter following data and click submit to withdraw")
    public void theUserEnterFollowingDataAndClickSubmitToWithdraw(List<Map<String, String >> withdrawInfo) {
        withdrawPage.withdrawSubmit(withdrawInfo);
    }

    @Then("The user should see red error message about overdraft limit {string}")
    public void theUserShouldSeeRedErrorMessageAboutOverdraftLimit(String expectedErrorMessage) {
        Assertions.assertEquals("https://dbank-qa.wedevx.co/bank/account/withdraw", driver.getCurrentUrl());
        Assertions.assertEquals(expectedErrorMessage, withdrawPage.getOverdraftLimitMessage());
    }

    @Then("The user should see the following {string} required field error message {string} on withdraw page")
    public void theUserShouldSeeTheFollowingRequiredFieldErrorMessageOnWithdrawPage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = withdrawPage.getRequiredFieldErrorMessage(fieldName);
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message of required " + fieldName
                +" is mismatch");
        System.out.println(actualErrorMessage);
    }


}
