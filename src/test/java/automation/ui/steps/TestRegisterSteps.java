//package automation.ui.steps;
//
//
//import automation.ui.pages.LoginPage;
//import automation.ui.pages.RegistrationPage;
//import automation.ui.utils.Driver;
//import automation.ui.utils.MockData;
//import io.cucumber.java.BeforeAll;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.Assertions;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestRegisterSteps {
//    WebDriver driver = Driver.getDriver();
//    private LoginPage loginPage = new LoginPage(driver);
//    private RegistrationPage registrationPage = new RegistrationPage(driver);
//    MockData mockData = new MockData();
//    Map<String, String> registrationInfo = mockData.generateRegistrationInfo();
//    Map<String, String> registrationInfoAdditional = mockData.generateRegistrationInfoAdditional();
//
//    @BeforeAll
//    public static void setUp() {
//        WebDriverManager.chromedriver().clearDriverCache().setup();
//    }
//
//
//    @Given("The user go to sign up page")
//    public void the_user_go_to_sign_up_page() {
//        driver.get("https://dbank-qa.wedevx.co/bank/login");
//        loginPage.goToSignUpPage();
//        assertEquals("https://dbank-qa.wedevx.co/bank/signup", driver.getCurrentUrl());
//    }
//
//    @When("The user fills all valid information")
//    public void the_user_fills_all_valid_information() {
//
//        registrationPage.signUp(registrationInfo.get("title"), registrationInfo.get("firstName"), registrationInfo.get("lastName"), registrationInfo.get("gender"), registrationInfo.get("dateOfBirth"), registrationInfo.get("socialSecurityNum"), registrationInfo.get("email"), registrationInfo.get("password"), registrationInfo.get("confirmPassword"));
//
//    }
//    @When("The user fills additional information")
//    public void the_user_fills_additional_information() {
//
//        registrationPage.additionalInfoWithHomePhone(registrationInfoAdditional.get("address"), registrationInfoAdditional.get("locality"), registrationInfoAdditional.get("region"), registrationInfoAdditional.get("postalCode"), registrationInfoAdditional.get("country"), registrationInfoAdditional.get("homePhone"));
//        Assertions.assertEquals("https://dbank-qa.wedevx.co/bank/register", driver.getCurrentUrl());
//    }
//    @Then("The user should see a message {string}")
//    public void the_user_should_see_a_message(String message) {
//        WebElement messageElement = driver.findElement(By.xpath("//span[contains(text(),'Registration Successful')]"));
//        String actualMessage = messageElement.getText();
//        Assertions.assertEquals(message, actualMessage);
//        registrationPage.login(registrationInfo.get("password"));
//    }
//}
