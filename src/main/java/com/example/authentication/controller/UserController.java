package com.example.authentication.controller;


import com.example.authentication.entity.json.UserJsonEntity;
import com.example.authentication.service.UserService;

import com.example.authentication.utils.InputManageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author hello
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 注册
     *
     * @param request 注册请求，包含用户名(username)，密码(password)，性别(sex)，年龄(age)，个人简介(personalProfiles)
     * @return UserJsonEntity 成功注册的用户信息
     * @throws Exception 用户名或密码为空，非法用户名，密码，年龄异常及对password加密可能产生的NoSuchAlgorithmException, UnsupportedEncodingException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public UserJsonEntity add(HttpServletRequest request) throws Exception {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String personalProfiles = request.getParameter("personalProfiles");
        return userService.add(InputManageUtils.userAddCheck(userName, password, sex, age, personalProfiles));
    }

    /**
     * 通过id的正常登陆
     *
     * @param request 登陆请求

     * @return UserJsonEntity 用户信息
     * @throws Exception 密码为空","密码错误"异常 及password加密可能产生的异常
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserJsonEntity login(HttpServletRequest request) throws Exception {
        return userService.login(InputManageUtils.userLoginCheck(request).get("userid"),InputManageUtils.userLoginCheck(request).get("password"));
    }

    /**
     * 从授权的登陆验证接口接受usercode并重定向到usercode拉取用户信息接口
     *
     * @param usercode 用于拉取用户信息加密后的String
     */
    @RequestMapping(value = "/usercodeLogin", method = RequestMethod.GET)
    public String loginByUserCode(String usercode, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("usercode", usercode);
        return "redirect:http://127.0.0.1:8080/userlogin/loginByUsercode";
    }

}
