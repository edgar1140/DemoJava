package com.example.demo.model.internal.response;

/**
 * @author eguzman (2018.07.03 2:11 PM)
 */
public class CreateUserResponse {
    public static class User {
        private String firstName;
        private String lastName;
        private Long id;

        public String getFirstName() {
            return firstName;
        }

        public User setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public User setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Long getId() {
            return id;
        }

        public User setId(Long id) {
            this.id = id;
            return this;
        }
    }
    private User user;

    public User getUser() {
        return user;
    }

    public CreateUserResponse setUser(User user) {
        this.user = user;
        return this;
    }
}
