package automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ViewCheckingAccountPage extends BaseMenuPage {

    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }


    public List<WebElement> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WebElement> transactions) {
        this.transactions = transactions;
    }
}
