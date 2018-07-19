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

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.Customer;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * @author eguzman (2018.07.10 10:29 AM)
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean createCustomer(String firstName, String lastName, Long id, Long createUserId) {
        final String sql = "" +
            "INSERT INTO customer (" +
            "   customer_id," +
            "   first_name," +
            "   last_name," +
            "   create_user_id" +
            " ) VALUES (" +
            "   :customerId," +
            "   :firstName," +
            "   :lastName," +
            "   :createUserId" +
            " )";

        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("customerId", id)
            .addValue("firstName", firstName)
            .addValue("lastName", lastName)
            .addValue("createUserId", createUserId);
        final int rowsUpdated = namedParameterJdbcTemplate.update(sql, parameterSource);
        return rowsUpdated == 1;
    }

    @Override
    public Long getCustomerIdNextValue() {
        final String sql = "SELECT customer_id_seq.nextval AS nextval FROM dual";
        return namedParameterJdbcTemplate.query(sql, EmptySqlParameterSource.INSTANCE, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getLong("nextval") : null;
            }
        });
    }

    @Override
    public Customer getCustomerById(Long id) {
        final String sql = "" +
            "SELECT " +
            " cust.customer_id, " +
            " cust.first_name AS cust_first_name, " +
            " cust.middle_name AS cust_middle_name, " +
            " cust.last_name AS cust_last_name, " +
            " cust.create_user_id AS cust_create_user_id, " +
            " cust.create_date AS cust_create_date, " +
            " cust.expire_user_id AS cust_expire_user_id, " +
            " cust.expire_date AS cust_expire_date, " +
            " acct.account_id, " +
            " acct.first_name AS acct_first_name, " +
            " acct.middle_name AS acct_middle_name, " +
            " acct.last_name AS acct_last_name, " +
            " acct.balance, " +
            " acct.create_user_id AS acct_create_user_id, " +
            " acct.create_date AS acct_create_date, " +
            " acct.expire_user_id AS acct_expire_user_id, " +
            " acct.expire_date AS acct_expire_date " +
            "FROM customer cust " +
            "LEFT OUTER JOIN account acct ON cust.customer_id = acct.customer_id " +
            "WHERE cust.customer_id = :customerId";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("customerId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Customer customer = null;
                final List<Account> accountList = new ArrayList<>();
                while (rs.next()) {
                    if (customer == null) {
                        customer = new Customer()
                            .setId(rs.getLong("customer_id"))
                            .setFirstName(rs.getString("cust_first_name"))
                            .setMiddleName(rs.getString("cust_middle_name"))
                            .setLastName(rs.getString("cust_last_name"))
                            .setCreateUserId(rs.getLong("cust_create_user_id"))
                            .setCreateDate(rs.getTimestamp("cust_create_date"))
                            .setExpireUserId(rs.getLong("cust_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("cust_expire_date"));

                    }
                    final long accountId = rs.getLong("account_id");
                    if (accountId > 0) {
                        final Account account = new Account()
                            .setId(accountId)
                            .setFirstName(rs.getString("acct_first_name"))
                            .setMiddleName(rs.getString("acct_middle_name"))
                            .setLastName(rs.getString("acct_last_name"))
                            .setBalance(rs.getBigDecimal("balance"))
                            .setCreateUserId(rs.getLong("acct_create_user_id"))
                            .setCreateDate(rs.getTimestamp("acct_create_date"))
                            .setExpireUserId(rs.getLong("acct_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("acct_expire_date"));
                        accountList.add(account);

                    }

                }
                if (customer != null) {
                    customer.setAccountList(accountList);

                }
                return customer;

            }
        });
    }
}