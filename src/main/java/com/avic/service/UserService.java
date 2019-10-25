package com.avic.service;


import com.avic.model.User;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserService {

    List<User> findAllUser();

    int addUser(User user);

    int updateUser(User user);

    int deleteUserByName(String userName);

    User findUserByUsername(User user);


}
