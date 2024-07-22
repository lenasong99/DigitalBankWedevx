package automation.ui.pages;

import automation.ui.utils.MockData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(id = "title")
    private WebElement titleSelect;

    @FindBy(id = "firstName")
    private WebElement firstNameTxtBox;

    @FindBy(id = "lastName")
    private WebElement lastNameTxtBox;

    @FindBy(id = "gender")
    private WebElement genderCheckBox;

    @FindBy(id = "dob")
    private WebElement dateOfBirthTxtBox;

    @FindBy(id = "ssn")
    private WebElement socialSecurityNumTxtBox;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressTXtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTxtBox;

    @FindBy(xpath = "//button")
    private WebElement nextBtn;

    //additional

    @FindBy(id = "address")
    private WebElement addressTxtBox;

    @FindBy(id = "locality")
    private WebElement localityTxtBox;

    @FindBy(id = "region")
    private WebElement regionTxtBox;

    @FindBy(id = "postalCode")
    private WebElement postalCodeTxtBox;

    @FindBy(id = "country")
    private WebElement countryTxtBox;

    @FindBy(id = "homePhone")
    private WebElement homePhoneTxtBox;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxtBox;

    @FindBy(id = "workPhone")
    private WebElement workPhoneTxtBox;

    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckBox;

    @FindBy(xpath = "//button")
    private WebElement registerBtn;
    @FindBy(id = "submit")
    private WebElement submitBtn;

    MockData mockData = new MockData();
    Map<String, String> emailAndSsn = mockData.generateRandomSsnAndEmail();

    public void signUp(List<Map<String, String>> regInfo) {
        Map<String, String> firstRow = regInfo.get(0);

        Select select = new Select(titleSelect);

        if (firstRow.get("title") != null) {
            select.selectByVisibleText(firstRow.get("title"));
        }

        if (firstRow.get("firstName") != null) {
            firstNameTxtBox.sendKeys(firstRow.get("firstName"));
        }
        if (firstRow.get("lastName") != null) {
            lastNameTxtBox.sendKeys( firstRow.get("lastName"));
        }
        if (firstRow.get("gender") != null) {
            genderCheckBox.findElement(By.xpath("//input[@value='"+firstRow.get("gender").toUpperCase()+"']")).click();
        }
        if (firstRow.get("dateOfBirth") != null) {
            dateOfBirthTxtBox.sendKeys(firstRow.get("dateOfBirth"));

        }
        if (firstRow.get("ssn") != null) {
            socialSecurityNumTxtBox.sendKeys(firstRow.get("ssn"));
        }
        if (firstRow.get("email") != null) {
            emailAddressTXtBox.sendKeys(firstRow.get("email"));
        }

        if (firstRow.get("password") != null) {
            passwordTxtBox.sendKeys(firstRow.get("password"));
        }
        if (firstRow.get("confirmPassword") != null) {
            confirmPasswordTxtBox.sendKeys(firstRow.get("confirmPassword"));
        }


        nextBtn.click();

        if (addressTxtBox.isDisplayed()) {
            if (firstRow.get("address") != null) {
                addressTxtBox.sendKeys(firstRow.get("address"));
            }if (firstRow.get("locality") != null) {
                localityTxtBox.sendKeys(firstRow.get("locality"));
            }if (firstRow.get("region") != null) {
                regionTxtBox.sendKeys(firstRow.get("region"));
            }if (firstRow.get("postalCode") != null) {
                postalCodeTxtBox.sendKeys(firstRow.get("postalCode"));
            }if (firstRow.get("country") != null) {
                countryTxtBox.sendKeys(firstRow.get("country"));
            }if (firstRow.get("homePhone") != null) {
                homePhoneTxtBox.sendKeys(firstRow.get("homePhone"));
            }if (firstRow.get("mobilePhone") != null) {
                mobilePhoneTxtBox.sendKeys(firstRow.get("mobilePhone"));
            }if (firstRow.get("workPhone") != null) {
                workPhoneTxtBox.sendKeys(firstRow.get("workPhone"));
            }if (firstRow.get("termsCheckMark") != null) {
                if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsCheckBox.click();
                }
            }
            registerBtn.click();
        }

    }


    public void login(String password) {
        passwordTxtBox.sendKeys(password);
        submitBtn.click();
    }

}
