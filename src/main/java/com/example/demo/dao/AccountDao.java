package com.example.demo.dao;

import java.math.BigDecimal;

import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.response.GetAccountResponse;

/**
 * @author eguzman (2018.07.16 10:18 AM)
 */
public interface AccountDao {
    boolean createAccount(String firstName, String middleName, String lastName,
                             Long accountId, Long customerId, Long userId);

    Long getAccountIdNextValue();

    Account getAccountById(Long id);
}
