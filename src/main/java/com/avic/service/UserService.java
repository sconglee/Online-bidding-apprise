package com.avic.service;


import com.avic.model.User;
import com.avic.model.httovo.PaginationRequest;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserService {


    int getUserCount();

    List<User> findUserPaginationRequest(PaginationRequest paginationRequest);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserByName(String userName);

    User findUserByUsername(User user);


}
