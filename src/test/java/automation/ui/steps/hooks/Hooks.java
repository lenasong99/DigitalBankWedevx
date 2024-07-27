package automation.ui.steps.hooks;

import automation.ui.utils.DBUtils;
import automation.ui.utils.Driver;
import io.cucumber.java.*;

import static automation.ui.utils.Driver.getDriver;

public class Hooks {

    @BeforeAll()
    public static void clearTheDBForRegistration() {
        DBUtils.establishConnections();
    }

    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage() {
        getDriver().get("http://3.254.171.199/bank/login");
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }

    @AfterAll()
    public static void closeConnectionToDB() {
        DBUtils.closeConnection();
    }
}
