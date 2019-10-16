package com.avic.service.impl;

import com.avic.mapper.UserMapper;
import com.avic.model.User;
import com.avic.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> findAllUser() {
        logger.info("获取所有用户信息");
        return userMapper.getUser();
    }

    @Override
    public void addUser(User user) {
        logger.info("添加用户信息");
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        logger.info("重置用户密码");
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        logger.info("删除用户信息");
        userMapper.deleteUser(user);
    }

    @Override
    public User findUserByUsername(User user) {
        User userInfo = userMapper.findUserByUsername(user);
        return userInfo;
    }


}
