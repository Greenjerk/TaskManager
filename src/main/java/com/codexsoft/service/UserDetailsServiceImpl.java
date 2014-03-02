package com.codexsoft.service;

import com.codexsoft.util.UserAuthorityUtils;
import com.codexsoft.dao.UserDao;
import com.codexsoft.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        if (userDao == null) {
            throw new IllegalArgumentException("userDao cannot be null");
        }
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        return new UserDetailsImpl(user);
    }

    private final class UserDetailsImpl extends User implements UserDetails {
        UserDetailsImpl(User user) {
            setId(user.getId());
            setEmail(user.getEmail());
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setAuthority(user.getAuthority());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return UserAuthorityUtils.createAuthorities(this);
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        private static final long serialVersionUID = 3384436451564509032L;
    }
}
