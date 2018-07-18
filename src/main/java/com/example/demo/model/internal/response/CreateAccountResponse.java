package com.example.demo.model.internal.response;

import java.math.BigDecimal;

/**
 * @author eguzman (2018.07.12 9:08 PM)
 */
public class CreateAccountResponse {
    public static class Account {
        private String firstName;
         private String middleName;
         private String lastName;
         private Long customerId;
         private Long accountId;

        public String getFirstName() {
            return firstName;
        }

        public Account setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getMiddleName() {
            return middleName;
        }

        public Account setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Account setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public Account setCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Long getAccountId() {
            return accountId;
        }

        public Account setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }
    }
    public Account account;

    public Account getAccount () {
        return account;
    }
    public CreateAccountResponse setAccount(Account account) {
       this.account = account;
       return this;
    }
}
