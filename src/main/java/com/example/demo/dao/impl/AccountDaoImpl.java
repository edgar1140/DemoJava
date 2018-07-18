package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.AccountDao;
import com.example.demo.model.internal.Account;

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
            "   :accounId, " +
            "   :customerId, " +
            "   :firstName, " +
            "   :middleName, " +
            "   :lastName, " +
            "   :createUserId " +
            " )";

        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("accounId", accountId)
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
            " SELECT" +
            "   account_id, " +
            "   customer_id, " +
            "   first_name, " +
            "   middle_name, " +
            "   last_name, " +
            "   balance, " +
            "   create_user_id, " +
            "   create_date, " +
            "   expire_user_id, " +
            "   expire_date " +
            " FROM account " +
            " WHERE account_id = :accountId ";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("accountId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Account>() {
            @Override
            public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
                final Account account;
                if (rs.next()) {
                    account = new Account()
                        .setId(rs.getLong("account_id"))
                        .setCustomerId(rs.getLong("customer_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setMiddleName(rs.getString("middle_name"))
                        .setLastName(rs.getString("last_name"))
                        .setBalance(rs.getBigDecimal("balance"))
                        .setCreateUserId(rs.getLong("create_user_id"))
                        .setCreateDate(rs.getTimestamp("create_date"))
                        .setExpireUserId(rs.getLong("expire_user_id"))
                        .setExpirationDate(rs.getTimestamp("expire_date"));
                } else {
                    account = null;
                }
                return account;
            }
        });
    }
}
