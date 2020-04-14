package com.oce.app.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    
    public static final String ADMIN_ORGANISATION = "ROLE_ADMIN_ORGANISATION";
    
    public static final String USER_ORGANISATION = "ROLE_USER_ORGANISATION";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
