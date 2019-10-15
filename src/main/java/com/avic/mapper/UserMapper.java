package com.avic.mapper;

import com.avic.model.User;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserMapper {

    public List<User> getUser();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User findUserByUsername(String userName);
}
