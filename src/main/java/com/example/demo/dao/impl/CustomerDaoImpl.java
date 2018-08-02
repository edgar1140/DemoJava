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
import com.example.demo.model.internal.Subscription;

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
            " acct.expire_date AS acct_expire_date, " +
            " subs.subscription_id, " +
            " subs.first_name AS subs_first_name, " +
            " subs.middle_name AS subs_middle_name, " +
            " subs.last_name AS subs_last_name, " +
            " subs.create_user_id AS subs_create_user_id, " +
            " subs.create_date AS subs_create_date, " +
            " subs.expire_user_id AS subs_expire_user_id," +
            " subs.expire_date AS subs_expire_date " +
            "FROM customer cust " +
            "LEFT OUTER JOIN account acct ON cust.customer_id = acct.customer_id " +
            "LEFT OUTER JOIN subscription subs ON acct.account_id = subs.account_id " +
            " WHERE cust.customer_id = :customerId" +
            " ORDER BY cust.customer_id, acct.account_id , subs.subscription_id";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("customerId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Customer customer = null;
                Account account = null;
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
                    final boolean addAccount = accountId > 0 && (account == null || account.getId() == null || account.getId() != accountId);
                    if (addAccount) {
                        account = new Account()
                            .setId(accountId)
                            .setFirstName(rs.getString("acct_first_name"))
                            .setMiddleName(rs.getString("acct_middle_name"))
                            .setLastName(rs.getString("acct_last_name"))
                            .setBalance(rs.getBigDecimal("balance"))
                            .setCreateUserId(rs.getLong("acct_create_user_id"))
                            .setCreateDate(rs.getTimestamp("acct_create_date"))
                            .setExpireUserId(rs.getLong("acct_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("acct_expire_date"));
                        final List<Account> accountList = customer.getAccountList() != null ? customer.getAccountList() : new ArrayList<>();
                        customer.setAccountList(accountList);
                        accountList.add(account);

                    }
                    final long subscriptionId = rs.getLong("subscription_id");
                    final boolean addSubscription = account != null && subscriptionId > 0;
                    if (addSubscription) {
                        final Subscription subscription = new Subscription()
                            .setId(rs.getLong("subscription_id"))
                            .setFirstName(rs.getString("subs_first_name"))
                            .setLastName(rs.getString("subs_last_name"))
                            .setCreateUserId(rs.getLong("subs_create_user_id"))
                            .setCreateDate(rs.getTimestamp("subs_create_date"))
                            .setExpireUserId(rs.getLong("subs_expire_user_id"))
                            .setExpirationDate(rs.getTimestamp("subs_expire_date"));
                        final List<Subscription> subscriptionList = account.getSubscriptionList() != null ? account.getSubscriptionList() : new ArrayList<>();
                        account.setSubscriptionList(subscriptionList);
                        subscriptionList.add(subscription);

                    }

                }
                return customer;

            }

        });
    }
}

//    @Override
//    public List<SearchCustomer> getCustomerByFirstAndLastName(String firstName, String lastName) {
//        final String sql = "" +
//            " SELECT cust.customer_id, cust.first_name, cust.last_name " +
//            " FROM customer cust " +
//            " WHERE UPPER(cust.first_name) LIKE UPPER('%'||:firstName ||'%')" +
//            " And UPPER(cust.last_name)LIKE UPPER('%'||:lastName||'%') ";
//        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
//            .addValue("firstName", firstName)
//            .addValue("lastName", lastName);
//
//        return namedParameterJdbcTemplate.query(sql, parameterSource, new RowMapper<SearchCustomer>() {
//            @Override
//            public SearchCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new SearchCustomer()
//                    .setId(rs.getLong("customer_id"))
//                    .setFirstName(rs.getString("first_name"))
//                    .setLastName(rs.getString("last_name"))
//                    ;
//            }
//        });
//    }
//
//    @Override
//    public List<SearchCustomer> getCustomerByPhoneNumber(String phoneNumber) {
//        final String sql = "" +
//            "SELECT cust.customer_id, cust.first_name, cust.last_name, cust.phone_number" +
//            "from customer cust " +
//            "INNER JOIN account acct ON cust.customer_id = acct.customer_id " +
//            "INNER JOIN subscription sub ON acct.account_id = sub.account_id " +
//            "WHERE sub.phone_number = :phoneNumber";
//        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
//            .addValue("phoneNumber", phoneNumber);
//        return namedParameterJdbcTemplate.query(sql, parameterSource, new RowMapper<SearchCustomer>() {
//            @Override
//            public SearchCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new SearchCustomer()
//                    .setId(rs.getLong("customer_id"))
//                    .setFirstName(rs.getString("first_name"))
//                    .setLastName(rs.getString("last_name"))
//                    .setPhoneNumber(rs.getString("phone_number"))
//                    ;
//            }
//        });
//    }
