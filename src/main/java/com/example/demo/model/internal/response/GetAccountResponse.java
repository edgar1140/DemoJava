package com.example.demo.model.internal.response;

import java.math.BigDecimal;

/**
 * @author eguzman (2018.07.12 9:26 PM)
 */
public class GetAccountResponse {
    public static class Account{
        private String Account_name;
        private Long Account_id;
        private BigDecimal Account_Amount;

        public String getAccount_name() {
            return Account_name;
        }

        public Account setAccount_name(String account_name) {
            Account_name = account_name;
            return this;
        }

        public Long getAccount_id() {
            return Account_id;
        }

        public Account setAccount_id(Long account_id) {
            Account_id = account_id;
            return this;
        }

        public BigDecimal getAccount_Amount() {
            return Account_Amount;
        }

        public Account setAccount_Amount(BigDecimal account_Amount) {
            Account_Amount = account_Amount;
            return this;
        }
    }
    public Account account;

    public Account getAccount(){
        return account;
    }

    public GetAccountResponse setAccount(Account account) {
        this.account = account;
        return this;
    }
}
