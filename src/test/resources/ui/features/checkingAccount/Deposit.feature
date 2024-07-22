# new feature
# Tags: optional

Feature: Deposit tests
    Background:
        Given  The user with email "test@test.co" is not in db
        And The user go to dbank sign up page
        When the user creates account with the following data
            | title |  | firstName | lastName | gender | dateOfBirth | ssn         | email        | password  | confirmPassword | address         | locality | region | postalCode | country | homePhone     | mobilePhone   | workPhone     | termsCheckMark |
            | Mr.   |  | John      | Smith    | M      | 03/24/1998  | 123-45-1111 | test@test.co | Test12345 | Test12345       | Main Street 123 | New York | NY     | 12345      | USA     | (111)222-4455 | (111)222-4455 | (111)222-4455 | true           |
        Then The user should see green message "Registration Successful. Please Login."
        And The following user info should be saved in the db
            | title |  | firstName | lastName | gender | dateOfBirth | ssn         | email        | password  | confirmPassword | address         | locality | region | postalCode | country | homePhone     | mobilePhone   | workPhone     | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
            | Mr.   |  | John      | Smith    | M      | 03/24/1998  | 123-45-1111 | test@test.co | Test12345 | Test12345       | Main Street 123 | New York | NY     | 12345      | USA     | (111)222-4455 | (111)222-4455 | (111)222-4455 | true              | true             | true                  | true    |

        Given The user login with password "Test12345"
        When The user creates checking account with following data
            | checkingAccountType | accountOwnership | accountName              | initialDepositAmount |
            | Standard Checking   | Individual       | Johns's checking account | 100000.0             |
        Then the user should see the green "Successfully created new Standard Checking account named Johns's checking account" message

  Scenario: Positive Case. Deposit with submit
      When The user enter following data and click submit
          | accountForDeposit                            | depositAmount |
          | Johns's checking account (Standard Checking) | 1000          |
      Then The user should see the following transaction on view checking page
          | date             | category | description                      | amount     | balance    |
          | 2024-01-30 05:30 | Income   | 845328681 (DPT) - Online Deposit | $1000.00   | $101000.00 |
          | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit        | $100000.00 | $100000.00 |

    Scenario: Positive Case. Deposit with reset
        When The user enter following data and click reset
            | accountForDeposit                            | depositAmount |
            | Johns's checking account (Standard Checking) | 1000          |
        Then The user should stay on deposit page

    Scenario Outline: Negative Case 1. Deposit with unselected account
        When The user enter following data and click submit
            | accountForDeposit   | depositAmount   |
            | <accountForDeposit> | <depositAmount> |
        Then The user should see the following "<fieldWithError>" required field error message "<errorMessage>"

        Examples:
            | accountForDeposit                            | depositAmount | fieldWithError    | errorMessage                       |
            |                                              | 1000          | accountForDeposit | Please select an item in the list. |
            | Johns's checking account (Standard Checking) |               | depositAmount     | Please fill out this field.        |
            | Johns's checking account (Standard Checking) | abc           | depositAmount     | Please match the requested format. |
            | Johns's checking account (Standard Checking) | 10!           | depositAmount     | Please match the requested format. |
            | Johns's checking account (Standard Checking) | -1000         | depositAmount     | Please match the requested format. |


