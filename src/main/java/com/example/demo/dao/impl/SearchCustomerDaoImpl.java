package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.SearchCustomerDao;
import com.example.demo.model.internal.SearchCustomer;

/**
 * @author eguzman (2018.08.02 8:13 AM)
 */
@Repository
public class SearchCustomerDaoImpl implements SearchCustomerDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<SearchCustomer> getCustomerByFirstAndLastName(String firstName, String lastName) {
        final String sql = "" +
            " SELECT cust.customer_id, cust.first_name, cust.last_name " +
            " FROM customer cust " +
            " WHERE UPPER(cust.first_name) LIKE UPPER('%'||:firstName ||'%')" +
            " And UPPER(cust.last_name)LIKE UPPER('%'||:lastName||'%') ";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("firstName", firstName)
            .addValue("lastName", lastName);

        return namedParameterJdbcTemplate.query(sql, parameterSource, new RowMapper<SearchCustomer>() {
            @Override
            public SearchCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SearchCustomer()
                    .setId(rs.getLong("customer_id"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    ;
            }
        });
    }

    @Override
    public List<SearchCustomer> getCustomerByPhoneNumber(String phoneNumber) {
        final String sql = "" +
            "SELECT cust.customer_id, cust.first_name, cust.last_name, cust.phone_number " +
            " from customer cust " +
            " INNER JOIN account acct ON cust.customer_id = acct.customer_id " +
            " INNER JOIN subscription sub ON acct.account_id = sub.account_id " +
            " WHERE sub.phone_number = :phoneNumber ";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("phoneNumber", phoneNumber);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new RowMapper<SearchCustomer>() {
            @Override
            public SearchCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SearchCustomer()
                    .setId(rs.getLong("customer_id"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setPhoneNumber(rs.getString("phone_number"))
                    ;
            }
        });
    }

}
