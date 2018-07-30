package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.AccountDao;
import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.Subscription;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * @author eguzman (2018.07.16 10:18 AM)
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean createAccount(String firstName, String middleName, String lastName, Long accountId, Long customerId, Long userId) {
        final String sql = "" +
            " INSERT INTO account ( " +
            "   account_id, " +
            "   customer_id, " +
            "   first_name, " +
            "   middle_name, " +
            "   last_name, " +
            "   create_user_id " +
            " ) VALUES ( " +
            "   :accountId, " +
            "   :customerId, " +
            "   :firstName, " +
            "   :middleName, " +
            "   :lastName, " +
            "   :createUserId " +
            " )";

        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("accountId", accountId)
            .addValue("customerId", customerId)
            .addValue("firstName", firstName)
            .addValue("middleName", middleName)
            .addValue("lastName", lastName)
            .addValue("createUserId", userId);
        final int rowsUpdate = namedParameterJdbcTemplate.update(sql, parameterSource);
        return rowsUpdate == 1;
    }

    @Override
    public Long getAccountIdNextValue() {
        final String sql = "SELECT account_id_seq.nextval AS nextval FROM dual";
        return namedParameterJdbcTemplate.query(sql, EmptySqlParameterSource.INSTANCE, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getLong("nextval") : null;
            }
        });
    }

    @Override
    public Account getAccountById(Long id) {
        final String sql = "" +
            " SELECT " +
            " acct.account_id, " +
            " acct.first_name AS acct_first_name, " +
            " acct.middle_name AS acct_middle_name, " +
            " acct.last_name AS acct_last_name, " +
            " acct.balance, " +
            " acct.create_user_id AS acct_create_user_id, " +
            " acct.create_date AS acct_create_date, " +
            " acct.expire_user_id AS acct_expire_user_id, " +
            " acct.expire_date AS acct_expire_date, " +
            " subs.subscription_id, " +
            " subs.first_name AS subs_first_name, " +
            " subs.middle_name AS subs_middle_name, " +
            " subs.last_name AS subs_last_name, " +
            " subs.create_user_id AS subs_create_user_id, " +
            " subs.create_date AS subs_create_date, " +
            " subs.expire_user_id AS subs_expire_user_id," +
            " subs.expire_date AS subs_expire_date " +
            " FROM account acct " +
            "LEFT JOIN subscription subs ON acct.account_id = subs.account_id " +
            " WHERE acct.account_id = :accountId ";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("accountId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Account>() {
            @Override
            public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
                Account account = null;
                final List<Subscription> subscriptionList = new ArrayList<>();
                while (rs.next()) {
                    if (account == null) {
                        account = new Account()
                            .setId(rs.getLong("account_id"))
                            .setFirstName(rs.getString("acct_first_name"))
                            .setMiddleName(rs.getString("acct_middle_name"))
                            .setLastName(rs.getString("acct_last_name"))
                            .setBalance(rs.getBigDecimal("balance"))
                            .setCreateUserId(rs.getLong("acct_create_user_id"))
                            .setCreateDate(rs.getTimestamp("acct_create_date"))
                            .setExpireUserId(rs.getLong("acct_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("acct_expire_date"));
                    }
                    final long subscriptionId = rs.getLong("subscription_id");
                    if (subscriptionId > 0) {
                        final Subscription subscription = new Subscription()
                            .setId(rs.getLong("subscription_id"))
                            .setFirstName(rs.getString("subs_first_name"))
                            .setLastName(rs.getString("subs_last_name"))
                            .setCreateUserId(rs.getLong("subs_create_user_id"))
                            .setCreateDate(rs.getTimestamp("subs_create_date"))
                            .setExpireUserId(rs.getLong("subs_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("subs_expire_date"));
                        subscriptionList.add(subscription);
                    }

                }
                if (account != null) {
                    account.setSubscriptionList(subscriptionList);
                }
                return account;
            }
        });
    }
}
