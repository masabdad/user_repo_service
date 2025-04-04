package com.mysample.Sample.constants;

public final class ApiConstants {
    private ApiConstants() {}

    // Role names
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // Messages
    public static final String USER_ALREADY_EXISTS = "User with this email already exists.";
    public static final String USER_SIGNUP_SUCCESS = "User signed up successfully";
    public static final String USER_LOGOUT_SUCCESS = "Logout successful";
    public static final String USER_LOGOUT_FAIL = "Logout failed";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    public static final String USER_NOT_FOUND = "User not found with email: ";
    public static final String USER_UPDATED = "User updated successfully";

    // Endpoints (if needed for dynamic use)
    public static final String USERS_ENDPOINT = "/users";

    // Other constants
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
}
