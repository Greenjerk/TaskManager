package com.codexsoft.util;

import com.codexsoft.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public final class UserAuthorityUtils {
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN",
            "ROLE_USER");
    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    public static Collection<? extends GrantedAuthority> createAuthorities(User user) {
        if (user.getAuthority().equals("ROLE_ADMIN")) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }

    private UserAuthorityUtils() {
    }
}
