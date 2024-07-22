package automation.ui.steps;

import automation.ui.pages.DepositPage;
import automation.ui.pages.ViewCheckingAccountPage;
import automation.ui.utils.ConfigReader;
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

public class DepositSteps {
    WebDriver driver = Driver.getDriver();
    DepositPage depositPage = new DepositPage(driver);
    ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }


    @When("The user enter following data and click submit")
    public void the_user_enter_following_data_and_click_submit(List<Map<String, String >> depositInfo) {
        depositPage.depositSubmit(depositInfo);
    }

    @Then("The user should see the following transaction on view checking page")
    public void the_user_should_see_the_following_transaction(List<Map<String, String>> list) throws InterruptedException {
        Map<String, String > map1 = list.get(0);
        Map<String, String > map2 = list.get(1);

        List<WebElement> transactions = viewCheckingAccountPage.getTransactions();

        List<WebElement> firstRow = transactions.get(0).findElements(By.tagName("td"));
        List<WebElement> lastRow = transactions.get(1).findElements(By.tagName("td"));

//        List<WebElement> lastRow = driver.findElements(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[2]/td"));
//        List<WebElement> firstRow = driver.findElements(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td"));

        WebElement date2 = lastRow.get(0);
        WebElement category2 = lastRow.get(1);
        WebElement description2 = lastRow.get(2);
        WebElement amount2 = lastRow.get(3);
        WebElement balance2 = lastRow.get(4);


        WebElement date1 = firstRow.get(0);
        WebElement category1 = firstRow.get(1);
        WebElement description1 = firstRow.get(2);
        WebElement amount1 = firstRow.get(3);
        WebElement balance1 = firstRow.get(4);

        System.out.println(category1.getText());
        System.out.println(amount1.getText());
        System.out.println(balance1.getText());
        System.out.println("------------------");
        System.out.println(category2.getText());
        System.out.println(amount2.getText());
        System.out.println(balance2.getText());


        Assertions.assertEquals(category2.getText(), map2.get("category"));
        Assertions.assertEquals(amount2.getText(), map2.get("amount"));
        Assertions.assertEquals(balance2.getText(), map2.get("balance"));

        Assertions.assertEquals(category1.getText(), map1.get("category"));
        Assertions.assertEquals(amount1.getText(), map1.get("amount"));
        Assertions.assertEquals(balance1.getText(), map1.get("balance"));

    }


    @Then("The user should see the following {string} required field error message {string}")
    public void theUserShouldSeeTheFollowingRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = depositPage.getRequiredFieldErrorMessage(fieldName);
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message of required " + fieldName
                +" is mismatch");
        System.out.println(actualErrorMessage);
    }

    @When("The user enter following data and click reset")
    public void theUserEnterFollowingDataAndClickReset(List<Map<String, String >> depositInfo) {
        depositPage.depositReset(depositInfo);
    }

    @Then("The user should stay on deposit page")
    public void theUserShouldStayOnDepositPage() {
        Assertions.assertEquals(ConfigReader.getPropertiesValue("depositPageUrl"), driver.getCurrentUrl());
    }


}
