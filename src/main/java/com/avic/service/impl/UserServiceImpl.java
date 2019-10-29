package com.avic.service.impl;

import com.avic.mapper.UserMapper;
import com.avic.model.User;
import com.avic.model.httovo.PaginationRequest;
import com.avic.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/14
 */
@Service
public class UserServiceImpl implements UserService {

    protected static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public int getUserCount() {
        logger.info("获取用户信息总条数");
        return userMapper.findUserCount();
    }

    @Override
    public List<Map<String, Object>> findUserPaginationRequest(PaginationRequest paginationRequest) {
        logger.info("按页获取用户信息");
        return userMapper.getUserPaginationRequest(paginationRequest);
    }

    @Override
    public int addUser(User user) {
        logger.info("添加用户信息");
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        logger.info("重置用户密码");
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserByName(String userName) {
        logger.info("删除用户信息");
        return userMapper.deleteUser(userName);
    }

    @Override
    public User findUserByUsername(User user) {
        User userInfo = userMapper.findUserByUsername(user);
        return userInfo;
    }


}
