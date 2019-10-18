package com.avic.controller;

import com.avic.common.utils.MD5;
import com.avic.model.User;
import com.avic.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
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

    @RequestMapping("/add")
    public Map<String, Object> addUser(@ModelAttribute("user") User user) {
        logger.info("新增专家用户");
        Map<String, Object> map = new HashMap<>();
        if (user != null & user.getUserName() != null & user.getPassWord() != null & Integer.valueOf(user.getAccountType()) != null) {
            User user1 = new User();
            user1.setUserName(user.getUserName());
            user1.setPassWord(MD5.getMD5(user.getPassWord()));
            user1.setAccountType(user.getAccountType());
            user1.setAccountStatus(0);

            int isAdd = userService.addUser(user1);
            if (isAdd == 1) {
                map.put("msg", "账号信息创建成功！");
            } else {
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("msg", "请输入账号信息！");
        }
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> updateUserPassWord(@ModelAttribute("user") User user) {
        Map map = new HashMap();
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassWord(MD5.getMD5(user.getPassWord()));
        int isUpdate = userService.updateUser(user1);
        if (isUpdate == 1) {
            map.put("msg", "重置密码成功！");
        } else {
            map.put("msg", "重置密码失败！");
        }
        return map;
    }


    @RequestMapping("/delete")
    public Map<String, Object> deleteUser(@ModelAttribute("user") User user) {
        Map map = new HashMap();
        int isDelete = userService.deleteUserByName(user.getUserName());
        if (isDelete == 1) {
            map.put("msg", "删除账号信息成功！");
        } else {
            map.put("msg", "删除账号信息失败！");
        }
        return map;
    }


}
