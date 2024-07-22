# new feature
# Tags: optional

Feature: Transfer Tests for checking functionality
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
        When The user creates checking two accounts with following data
            | checkingAccountType | accountOwnership | accountName                | initialDepositAmount | message                                                                             |
            | Standard Checking   | Individual       | Johns's checking account 1 | 100000.0             | Successfully created new Standard Checking account named Johns's checking account 1 |
            | Standard Checking   | Individual       | Johns's checking account 2 | 200000.0             | Successfully created new Standard Checking account named Johns's checking account 2 |

    Scenario: Positive Case. As a user, I want to make a transfer operation
         When The user is on Transfer page, fills all following information and click submit button
             | fromAccount                                    | toAccount                                      | transferAmount |
             | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) | 10000.0        |
        Then The user should see the following transaction on view checking page
            | date             | category | description                                         | amount     | balance    |
            | 2024-07-12 07:46 | Income   | 845328781 (TRN) - Transfer from Account (486136758) | $10000.00  | $210000.00 |
            | 2024-07-12 07:46 | Income   | 845325809 (DPT) - Deposit                           | $200000.00 | $200000.00 |


    Scenario Outline: Negative Case 1. As a user, I want to make a transfer operation with invalid data
            When The user is on Transfer page, fills all following information and click submit button
                | fromAccount   | toAccount   | transferAmount   |
                | <fromAccount> | <toAccount> | <transferAmount> |
            Then The user should see the following "<fieldWithError>" required field error message "<errorMessage>" on transfer page

        Examples:
            | fromAccount                                    | toAccount                                      | transferAmount | fieldWithError | errorMessage                       |
            | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) |                | transferAmount | Please fill out this field.        |
            | Johns's checking account 1 (Standard Checking) |                                                | 10000.0        | toAccount      | Please select an item in the list. |
            | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) | abc            | transferAmount | Please match the requested format. |
            | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) | -10000.0       | transferAmount | Please match the requested format. |
            | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) | 10!            | transferAmount | Please match the requested format. |


    Scenario: Negative Case 2. As a user, I want to make a transfer operation with invalid amount (more than current)
        When The user is on Transfer page, fills all following information and click submit button
            | fromAccount                                    | toAccount                                      | transferAmount |
            | Johns's checking account 1 (Standard Checking) | Johns's checking account 2 (Standard Checking) | 150000.00      |
        Then The user should see red message about transfer is more than the balance "The amount ($150000.00) requested for transfer is more than the available balance ($100000.00)."


