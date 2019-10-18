package com.avic.service;


import com.avic.model.User;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserService {

    public List<User> findAllUser();

    public int addUser(User user);

    public int updateUser(User user);

    public int deleteUserByName(String userName);

    public User findUserByUsername(User user);


}
