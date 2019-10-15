package com.avic.controller;

import com.avic.model.User;
import com.avic.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author sconglee
 * @date 2019/10/14
 */
@Controller
@RequestMapping("/users")
public class UserController {
    protected static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String getAllUser(Map<String, Object> map) {
        logger.info("专家管理展示页");
        List<User> userList = userService.findAllUser();
        map.put("users", userList);
        return "/users";
    }


}
