package com.avic.mapper;

import com.avic.model.User;
import com.avic.model.httovo.PaginationRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/14
 */
public interface UserMapper {

    int findUserCount();

    List<Map<String, Object>> getUserPaginationRequest(PaginationRequest paginationRequest);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("userName") String userName);

    User findUserByUsername(User user);
}
