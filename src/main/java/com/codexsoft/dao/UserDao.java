package com.codexsoft.dao;

import com.codexsoft.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

    public List<User> getSimpleUsers();
    public User getUserByName(String username);
}

