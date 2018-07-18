package com.example.demo.dao;
import com.example.demo.model.internal.User;

/**
 * @author eguzman (2018.07.03 3:20 PM)
 */
public interface UserDao {
    boolean createUser (String firstName, String lastName, Long id);

    Long getUserIdNextValue();

    User getUserById(Long id);
}
