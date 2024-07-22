package automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.*;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public String generateRandomEmail(){
        String email = fakeValuesService.bothify(new Faker().name().firstName()+ "####gmail.com");
        return email;
    }

    private String  generateSnn(){
        String ssn = fakeValuesService.bothify(String.format("%09d", new Random().nextInt(1000000000)));
        return ssn;
    }

    public Map<String, String> generateRegistrationInfo() {
        Faker faker = new Faker();
        String title = faker.options().option("Mr.", "Ms.", "Mrs.");
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String gender = faker.options().option("M", "F");
        Date dateOfBirthInDAteFormat = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateOfBirth = dateFormat.format(dateOfBirthInDAteFormat);
        String socialSecurityNum = String.format(faker.idNumber().ssnValid(), "XXX-XX-XXXX");
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 20, true, false,true);
        String confirmPassword = password;

        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("gender", gender);
        map.put("dateOfBirth", dateOfBirth);
        map.put("socialSecurityNum", socialSecurityNum);
        map.put("email", email);
        map.put("password", password);
        map.put("confirmPassword", confirmPassword);
        return map;
    }

    public Map<String, String> generateRegistrationInfoAdditional() {
        Faker faker = new Faker();
        String address = faker.address().streetAddress();
        String locality = faker.address().city();
        String region = faker.address().stateAbbr();
        String postalCode = faker.address().zipCode();
        String country = faker.address().countryCode();
        String homePhone = faker.phoneNumber().cellPhone();

        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("locality", locality);
        map.put("region", region);
        map.put("postalCode", postalCode);
        map.put("country", country);
        map.put("homePhone", homePhone);
        return map;
    }


    public static void main(String[] args) {
        MockData mockData = new MockData();

        Map<String, String> map = mockData.generateRegistrationInfoAdditional();
        System.out.println(map.toString());
    }

    public Map<String, String> generateRandomSsnAndEmail() {
        Map<String, String> map = new HashMap<>();
        String email = fakeValuesService.bothify(new Faker().internet().emailAddress());
        String socialSecurityNum = fakeValuesService.bothify(String.format(new Faker().idNumber().ssnValid(), "XXX-XX-XXXX"));
        map.put("email", email);
        map.put("socialSecurityNum", socialSecurityNum);
        return map;
    }
}
