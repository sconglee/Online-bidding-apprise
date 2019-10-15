package com.avic.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @ClassName LoginController
 * @Description 用户登录相关
 * @Author xulei
 * @Date 2019/10/15/015 10:44
 * @Version 1.0
 **/
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);
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
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public boolean userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {


        logger.info("用户名和密码匹配失败！");
        return false;
    }


}
