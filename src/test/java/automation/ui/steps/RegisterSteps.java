package automation.ui.steps;
import automation.ui.pages.RegistrationPage;
import automation.ui.utils.ConfigReader;
import automation.ui.utils.DBUtils;
import automation.ui.utils.Driver;
import automation.ui.utils.MockData;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static automation.ui.utils.Driver.getDriver;

public class RegisterSteps {
    WebDriver driver = Driver.getDriver();
    private RegistrationPage registrationPage = new RegistrationPage(driver);
    MockData mockData = new MockData();

    List<Map<String, Object>> nextValList = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @Given("The user go to dbank sign up page")
    public void the_user_go_to_dbank_sign_up_page() {
       getDriver().get(ConfigReader.getPropertiesValue("registrationPageUrl"));
    }
    @When("the user creates account with the following data")
    public void the_user_creates_account_with_the_following_data(List<Map<String, String>> regInfo) {
        registrationPage.signUp(regInfo);
    }
    @Then("The user should see green message {string}")
    public void the_user_should_see_green_message(String message) {
        WebElement messageElement = driver.findElement(By.xpath("//span[text()='Registration Successful. Please Login.']"));
        String actualMessage = messageElement.getText();
        Assertions.assertEquals(message, actualMessage);
    }

    @Then("The following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileInfoInDB) {
        Map<String, String> expectedUserInfoMap = expectedUserProfileInfoInDB.get(0);

        String queryUserTable = String.format("select * from users where username = '%s'", expectedUserInfoMap.get("email"));
        String queryProfileTable = String.format("select * from user_profile where email_address = '%s'", expectedUserInfoMap.get("email"));


        List<Map<String, Object>> actualUserInfo = DBUtils.runSqlSelectQuery(queryUserTable);
        List<Map<String, Object>> actualProfileInfo = DBUtils.runSqlSelectQuery(queryProfileTable);

        Map<String, Object> actualUserInfoMap = actualUserInfo.get(0);
        Map<String, Object> actualProfilinfoMap = actualProfileInfo.get(0);

        Assertions.assertEquals(1, actualUserInfo.size(), "registration generated unexpected numbers of users");
        Assertions.assertEquals(1, actualProfileInfo.size(), "registration generated unexpected numbers of users' profiles");

        Assertions.assertEquals(expectedUserInfoMap.get("title"), actualProfilinfoMap.get("title"));


        Assertions.assertEquals(expectedUserInfoMap.get("title"), actualProfilinfoMap.get("title"), "registration generated wrong title" );
        Assertions.assertEquals(expectedUserInfoMap.get("firstName"), actualProfilinfoMap.get("first_name"), "registration generated wrong firstName" );
        Assertions.assertEquals(expectedUserInfoMap.get("lastName"), actualProfilinfoMap.get("last_name"), "registration generated wrong lastName" );
        Assertions.assertEquals(expectedUserInfoMap.get("gender"), actualProfilinfoMap.get("gender"), "registration generated wrong gender" );
     //   Assertions.assertEquals(expectedUserInfoMap.get("dateOfBirth"), actualProfilinfoMap.get("dob"), "registration generated wrong dateOfBirth" );
        Assertions.assertEquals(expectedUserInfoMap.get("ssn"), actualProfilinfoMap.get("ssn"), "registration generated wrong ssn" );
        Assertions.assertEquals(expectedUserInfoMap.get("email"), actualProfilinfoMap.get("email_address"), "registration generated wrong email" );
        Assertions.assertEquals(expectedUserInfoMap.get("address"), actualProfilinfoMap.get("address"), "registration generated wrong address" );
        Assertions.assertEquals(expectedUserInfoMap.get("locality"), actualProfilinfoMap.get("locality"), "registration generated wrong locality" );
        Assertions.assertEquals(expectedUserInfoMap.get("region"), actualProfilinfoMap.get("region"), "registration generated wrong region" );
        Assertions.assertEquals(expectedUserInfoMap.get("postalCode"), actualProfilinfoMap.get("postal_code"), "registration generated wrong postalCode" );
        Assertions.assertEquals(expectedUserInfoMap.get("country"), actualProfilinfoMap.get("country"), "registration generated wrong country" );
        Assertions.assertEquals(expectedUserInfoMap.get("homePhone"), actualProfilinfoMap.get("home_phone"), "registration generated wrong homePhone" );
        Assertions.assertEquals(expectedUserInfoMap.get("mobilePhone"), actualProfilinfoMap.get("mobile_phone"), "registration generated wrong mobilePhone" );
        Assertions.assertEquals(expectedUserInfoMap.get("workPhone"), actualProfilinfoMap.get("work_phone"), "registration generated wrong workPhone" );


        Assertions.assertEquals(expectedUserInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "account_non_expired mismatch upon registration" );
        Assertions.assertEquals(expectedUserInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "account_non_locked mismatch upon registration" );
        Assertions.assertEquals(expectedUserInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "credentials_non_expired mismatch upon registration" );
        Assertions.assertEquals(expectedUserInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "enabled mismatch upon registration");

        Assertions.assertEquals(nextValList.get(0).get("next_val"), actualUserInfoMap.get("id"), "Id user mismatch");

        long expectedUserProfileId = Integer.parseInt(String.valueOf(nextValList.get(0).get("next_val")));
        Assertions.assertEquals(++expectedUserProfileId, actualProfilinfoMap.get("id"), "Id user profile mismatch");


    }


    @Given("The user with email {string} is not in db")
    public void theUserWithEmailIsNotInDb(String email) {
        String queryForProfile = String.format("delete from user_profile where email_address = '%s'", email);
        String queryForUsers = String.format("delete from users where username = '%s'", email);

        DBUtils.runSqlUpdateQuery(queryForProfile);
        DBUtils.runSqlUpdateQuery(queryForUsers);

        String queryToGetNextValInHibernateSeqTable = String.format("select * from hibernate_sequence");
        nextValList =  DBUtils.runSqlSelectQuery(queryToGetNextValInHibernateSeqTable);
    }
}
