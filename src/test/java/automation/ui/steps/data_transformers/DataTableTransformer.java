package automation.ui.steps.data_transformers;

import automation.ui.models.AccountCard;
import automation.ui.models.BankTransaction;
import automation.ui.models.NewCheckingAccountInfo;

import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownership = entry.get("ownership");
        long accountNumber = Long.parseLong(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));

        return new AccountCard(accountName, accountType, ownership,
                accountNumber, interestRate, balance);

    }

    @DataTableType
    public BankTransaction transactionEntry(Map<String, String> entry) {
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));

        return new BankTransaction(date, category, description, amount, balance);


    }

    @DataTableType
    public NewCheckingAccountInfo entryToCheckingAccountInfo(Map<String, String> entry) {
        String checkingAccountType = entry.get("checkingAccountType");
        String accountOwnership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));

        return new NewCheckingAccountInfo(checkingAccountType, accountOwnership, accountName, initialDepositAmount);
    }

//    @DataTableType
//    public RegistrationInfo entryToRegistrationInfo(Map<String, String> entry) {
//        String title = entry.get("title");
//        String firstName = entry.get("firstName");
//        String lastName = entry.get("lastName");
//        String gender = entry.get("gender");
//        String dateOfBirth = entry.get("dateOfBirth");
//        String password = entry.get("password");
//        String confirmPassword = entry.get("confirmPassword");
//        String address = entry.get("address");
//        String locality = entry.get("locality");
//        String region = entry.get("region");
//        String postalCode = entry.get("postalCode");
//        String country = entry.get("country");
//        String homePhone = entry.get("homePhone");
//        String mobilePhone = entry.get("mobilePhone");
//        String workPhone = entry.get("workPhone");
//
//        return new RegistrationInfo(title, firstName, lastName, gender, dateOfBirth, password, confirmPassword, address, locality, region,postalCode , country, homePhone, mobilePhone, workPhone);
//    }
}
