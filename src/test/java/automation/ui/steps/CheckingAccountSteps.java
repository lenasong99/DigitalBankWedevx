package automation.ui.steps;


import automation.ui.pages.LoginPage;
import automation.ui.pages.NewCheckingAccountPage;
import automation.ui.pages.RegistrationPage;
import automation.ui.utils.ConfigReader;
import automation.ui.utils.Driver;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private RegistrationPage registrationPage = new RegistrationPage(driver);
    NewCheckingAccountPage newCheckingAccountPage = new NewCheckingAccountPage(driver);


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();

    }

    @Given("The user login with password {string}")
    public void the_user_login_with_password(String password) {
        registrationPage.login(password);
        Assertions.assertEquals(ConfigReader.getPropertiesValue("homePage"), driver.getCurrentUrl());
    }

    @When("The user creates checking account with following data")
    public void the_user_creates_checking_account_with_following_data(List<Map<String, String >> newCheckingInfo) {
        newCheckingAccountPage.createCheckingAccount(newCheckingInfo);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String message) {
        WebElement newAccountConfAlertDiv = driver.findElement(By.id("new-account-msg"));
        assertEquals(message, newAccountConfAlertDiv.getText());
    }


}
