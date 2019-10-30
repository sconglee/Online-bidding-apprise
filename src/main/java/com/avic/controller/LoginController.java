package com.avic.controller;

import com.avic.common.constant.BidConstant;
import com.avic.common.utils.MD5;
import com.avic.model.User;
import com.avic.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 用户登录相关
 * @Author xulei
 * @Date 2019/10/15/015 10:44
 * @Version 1.0
 **/
@Controller
public class LoginController {

    private static Log logger = LogFactory.getLog(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * @Author xulei
     * @Description 跳转到登录首页
     * @Date 9:51 2019/10/14/014
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }

    /**
     * @Author xulei
     * @Description 判断用户名和密码是否存在
     * @Date 9:52 2019/10/14/014
     * @Param []
     * @return boolean
     **/
    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody User user, HttpSession session) {
        String userName = user.getUserName();
        String password = user.getPassWord();
        logger.info("用户登录，用户是：" + userName + "， 密码是： " + password);

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (userName != null && password != null) {
            user.setPassWord(MD5.getMD5(password));
            User userInfo = userService.findUserByUsername(user);

            if (userInfo != null && userInfo.getAccountStatus() != BidConstant.USER_STATUS_CANCEL) {
                // 把用户登录信息保存在session中
                session.setAttribute("userName", userName);
                session.setAttribute("password", password);
                session.setAttribute("role", userInfo.getAccountType());
                logger.info("保存用户登录信息，用户名是：" + userName + ", 密码是：" + password + ", 角色是：" + userInfo.getAccountType());

                modelMap.put("success", "true");
                modelMap.put("role", userInfo.getAccountType());
                modelMap.put("msg", "用户登录成功");
            } else {
                modelMap.put("success", "false");
                modelMap.put("msg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", "false");
            modelMap.put("msg", "用户名和密码均不能为空");
        }
        return modelMap;
    }


}
