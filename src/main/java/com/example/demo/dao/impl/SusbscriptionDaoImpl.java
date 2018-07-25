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

import com.example.demo.dao.SubscriptionDao;
import com.example.demo.model.internal.Subscription;

/**
 * @author eguzman (2018.07.23 3:04 PM)
 */
@Repository
public class SusbscriptionDaoImpl implements SubscriptionDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean createSubscription(Long accountId, Long subscriptionId, Long createUserId, String firstName, String lastName, String serialNumber, Boolean active, Long phoneNumber) {
        final String sql = "" +
            " INSERT INTO subscription ( " +
            "   account_id, " +
            "   subscription_id, " +
            "   create_user_id, " +
            "   first_name, " +
            "   last_name, " +
            "   active, " +
            "   phone_number " +
            " ) VALUES ( " +
            "   :accountId, " +
            "   :subscriptionId, " +
            "   :createUserId, " +
            "   :firstName, " +
            "   :lastName, " +
            "   :active, " +
            "   :phoneNumber " +
            " )";

        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("accountId", accountId)
            .addValue("subscriptionId", subscriptionId)
            .addValue("createUserId", createUserId)
            .addValue("firstName", firstName)
            .addValue("lastName", lastName)
            .addValue("active", active)
            .addValue("phoneNumber", phoneNumber);
        final int rowsUpdate = namedParameterJdbcTemplate.update(sql, parameterSource);
        return rowsUpdate == 1;
    }

    @Override
    public Long getSubscriptionIdNextValue() {
        final String sql = "SELECT subscription_id_seq.nextval AS nextval FROM dual";
        return namedParameterJdbcTemplate.query(sql, EmptySqlParameterSource.INSTANCE, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getLong("nextval") : null;
            }
        });
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        final String sql = "" +
            " SELECT " +
            " subscription_id, " +
            " account_id, " +
            " create_user_id, " +
            " first_name, " +
            " last_name, " +
            " phone_number, " +
            " serial_number, " +
            " active, " +
            " plan_name, " +

            " create_date, " +
            " expire_user_id, " +
            " expire_date " +
            " FROM subscription " +
            " WHERE subscription_id = :subscriptionId ";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("subscriptionId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<Subscription>() {
            @Override
            public Subscription extractData(ResultSet rs) throws SQLException, DataAccessException {
                final Subscription subscription;
                if (rs.next()) {
                    subscription = new Subscription()
                        .setId(rs.getLong("subscription_id"))
                        .setAccountId(rs.getLong("account_id"))
                        .setCreateUserId(rs.getLong("create_user_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setActive(rs.getBoolean("active"))
                        .setCreateDate(rs.getTimestamp("create_date"))
                        .setExpireUserId(rs.getLong("expire_user_id"))
                        .setExpirationDate(rs.getTimestamp("expire_date"));
                } else {
                    subscription = null;
                }
                return subscription;
            }
        });

    }
}








