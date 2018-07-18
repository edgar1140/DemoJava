package com.example.demo.model.internal.response;

//import com.example.demo.model.internal.request.GetUserRequest;

/**
 * @author eguzman (2018.07.03 3:11 PM)
 */
public class GetUserResponse {
    public static class User {
        private String firstName;
        private String lastName;
        private Long id;
        private Long locationId;
        private String jobTitle;

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

        public Long getLocationId() {
            return locationId;
        }

        public User setLocationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public User setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }
    }

    private User user;

    public User getUser() {
        return user;
    }

    public GetUserResponse setUser(User user) {
        this.user = user;
        return this;
    }
}
