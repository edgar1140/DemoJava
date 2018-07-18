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

import com.example.demo.dao.UserDao;
import com.example.demo.model.internal.User;

/**
 * @author eguzman (2018.07.03 3:23 PM)
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean createUser(String firstName, String lastName, Long id) {
        final String sql = "" +
            "INSERT INTO user (" +
            "   user_id," +
            "   first_name," +
            "   last_name," +
            "   create_user_id" +
            " ) VALUES (" +
            "   :userId," +
            "   :firstName," +
            "   :lastName," +
            "   :createUserId" +
            " )";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userId", id)
            .addValue("firstName", firstName)
            .addValue("lastName", lastName)
            .addValue("createUserId", 1);
        final int rowsUpdated = namedParameterJdbcTemplate.update(sql, parameterSource);
        return rowsUpdated == 1;
    }

    @Override
    public Long getUserIdNextValue() {
        final String sql = "SELECT user_id_seq.nextval AS nextval FROM dual";
        return namedParameterJdbcTemplate.query(sql, EmptySqlParameterSource.INSTANCE, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getLong("nextval") : null;
            }
        });
    }

    @Override
    public User getUserById(Long id) {
        final String sql = "" +
            "SELECT user_id," +
            "  first_name," +
            "    middle_name," +
            "    last_name," +
            "    job_title," +
            "    location_id," +
            "    create_user_id," +
            "    create_date," +
            "    expire_user_id," +
            "    expire_date " +
            "FROM user " +
            "WHERE user_id  = :userId";
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userId", id);
        return namedParameterJdbcTemplate.query(sql, parameterSource, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                final User user;
                if (rs.next()) {
                    user = new User()
                        .setId(rs.getLong("user_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setMiddleName(rs.getString("middle_name"))
                        .setLastName(rs.getString("last_name"))
                        .setJobTitle(rs.getString("job_title"))
                        .setLocationId(rs.getLong("location_id"))
                        .setCreateUserId(rs.getLong("create_user_id"))
                        .setCreateDate(rs.getDate("create_date"))
                        .setExpireUserId(rs.getLong("expire_user_id"))
                        .setExpirationDate(rs.getDate("expire_date"));

                } else {
                    user = null;
                }
                return user;
            }
        });
    }
}
