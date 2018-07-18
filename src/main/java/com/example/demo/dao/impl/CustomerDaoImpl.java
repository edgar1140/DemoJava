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

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.internal.Customer;

/**
 * @author eguzman (2018.07.10 10:29 AM)
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean createCustomer(String firstName, String lastName, Long id) {
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
            .addValue("createUserId", 1);
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
            "SELECT customer_id," +
            "  first_name," +
            "    middle_name," +
            "    last_name," +
            "    create_user_id," +
            "    create_date," +
            "    expire_user_id," +
            "    expire_date " +
            "FROM customer " +
            "WHERE customer_id  = :customerId";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("customerId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
                final Customer customer;
                if (rs.next()) {
                    customer = new Customer()
                        .setId(rs.getLong("customer_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setMiddleName(rs.getString("middle_name"))
                        .setLastName(rs.getString("last_name"))
                        .setCreateUserId(rs.getLong("create_user_id"))
                        .setCreateDate(rs.getDate("create_date"))
                        .setExpireUserId(rs.getLong("expire_user_id"))
                        .setExpirationDate(rs.getDate("expire_date"));

                } else {
                    customer = null;
                }
                return customer;
            }
        });
    }
}