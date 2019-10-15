package com.avic.service;


import com.avic.model.User;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserService {

    public List<User> findAllUser();

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User findUserByUsername(String userName);


}
