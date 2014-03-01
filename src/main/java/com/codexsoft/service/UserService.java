package com.codexsoft.service;

import com.codexsoft.form.RegisterForm;
import com.codexsoft.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService extends GenericManager<User, Long> {

    public void addUser(RegisterForm form);
    public void blockUser(String username);
    public List<User> getSimpleUsers();
    public User getUserByName(String username);
    public Set<User> getUserSet(String users);
    public void setAvatar(User user, MultipartFile file) throws IOException;
    public byte[] getCurrentAvatar(User user) throws IOException;
}
