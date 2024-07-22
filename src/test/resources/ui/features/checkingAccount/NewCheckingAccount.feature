# new feature
# Tags: optional

Feature: Create a new checking account
    Background:
      Given  The user with email "test@test.co" is not in db
      Given The user go to dbank sign up page
      When the user creates account with the following data
        | title |  | firstName | lastName | gender | dateOfBirth | ssn         | email        | password  | confirmPassword | address         | locality | region | postalCode | country | homePhone     | mobilePhone   | workPhone     | termsCheckMark |
        | Mr.   |  | John      | Smith    | M      | 03/24/1998  | 123-45-1111 | test@test.co | Test12345 | Test12345       | Main Street 123 | New York | NY     | 12345      | USA     | (111)222-4455 | (111)222-4455 | (111)222-4455 | true           |
      Then The user should see green message "Registration Successful. Please Login."
      And The following user info should be saved in the db
        | title |  | firstName | lastName | gender | dateOfBirth | ssn         | email        | password  | confirmPassword | address         | locality | region | postalCode | country | homePhone     | mobilePhone   | workPhone     | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
        | Mr.   |  | John      | Smith    | M      | 03/24/1998  | 123-45-1111 | test@test.co | Test12345 | Test12345       | Main Street 123 | New York | NY     | 12345      | USA     | (111)222-4455 | (111)222-4455 | (111)222-4455 | true              | true             | true                  | true    |

  Scenario: Positive Case. As a user, I want successfully create Checking account
    Given The user login with password "Test12345"
    When The user creates checking account with following data
      | checkingAccountType | accountOwnership | accountName              | initialDepositAmount |
      | Standard Checking   | Individual       | Johns's checking account | 100000.0             |
    Then the user should see the green "Successfully created new Standard Checking account named Johns's checking account" message