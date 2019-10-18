package com.avic.mapper;

import com.avic.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserMapper {

    public List<User> getUser();

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(@Param("userName") String userName);

    public User findUserByUsername(User user);
}
