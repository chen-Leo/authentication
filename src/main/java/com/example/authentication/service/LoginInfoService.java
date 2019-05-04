package com.example.authentication.service;

import com.example.authentication.entity.LoginInfoEntity;
import com.example.authentication.entity.json.UserJsonEntity;
import com.example.authentication.exception.IllegalException;
import com.example.authentication.mapper.LoginInfoMapper;
import com.example.authentication.utils.InputManageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author hello
 */
@Service
public class LoginInfoService {
    @Autowired
    LoginInfoMapper loginInfoMapper;

    /**
     * 接受转发的登陆请求，返回userCode，可根据userCode拉取用户信息
     * @param request 传入用户请求
     * @return userCode
     * @throws Exception "密码错误" 和 非法的密码输入" 及 "非法的用户名输入"异常
     */
    public String addUserCode(HttpServletRequest request) throws Exception {
        ConcurrentHashMap<String, String> concurrentHashMap = InputManageUtils.getUserCodeCheck(request);
        Integer userId = Integer.parseInt(concurrentHashMap.get("userid"));
        String password = concurrentHashMap.get("password");
        if (!password.equals(loginInfoMapper.loginCheck(userId))) {
            throw new IllegalException("403", "密码错误");
        } else {
            loginInfoMapper.add(new LoginInfoEntity(concurrentHashMap.get("usercode"), concurrentHashMap.get("time"), 1,userId));
            return concurrentHashMap.get("usercode");
        }
    }



    /**
     * 根据userCode拉取用户信息
     * @param userCode 用于拉取用户信息String
     * @return UserJsonEntity
     * @throws IllegalException  "未登陆"和"登陆过时"异常
     */
    public UserJsonEntity loginByUserCode(String userCode) throws IllegalException {
        LoginInfoEntity reLoginInfoEntity = loginInfoMapper.codeLogin(userCode);
        if (reLoginInfoEntity == null) {
            throw new IllegalException("403", "你还未登陆");
        }
        int recode = reLoginInfoEntity.getUsed();
        if (recode == -1) {
            throw new IllegalException("403", "登陆过时");
        } else if (recode == 1) {
            loginInfoMapper.update(userCode);
            return new UserJsonEntity(loginInfoMapper.selectUser(reLoginInfoEntity.getUserId()));
        }
        return null;
    }
}








