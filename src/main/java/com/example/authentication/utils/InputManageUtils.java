package com.example.authentication.utils;

import com.example.authentication.entity.UserInfoEntity;
import com.example.authentication.exception.IllegalException;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入合法性管理工具类
 *
 * @author hello
 */
public class InputManageUtils {
    private static final String REGEX = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
    private static final String REGEX_PASSWORD = "^[A-Za-z0-9]{5,17}$";
    private static final Pattern P = Pattern.compile(REGEX);
    private static final Pattern P_PASS = Pattern.compile(REGEX_PASSWORD);

    /**
     * @param userName         用户名
     * @param password         密码
     * @param sex              性别
     * @param ageInput         年龄
     * @param personalProfiles 个人简介
     * @return UserInfoEntity  用户信息数据库表
     * @throws IllegalException 用户名或密码为空，非法用户名，密码，年龄异常
     */
    public static UserInfoEntity userAddCheck(String userName, String password, String sex, String ageInput, String personalProfiles) throws IllegalException {
        int age;
        if (userName == null || password == null) {
            throw new IllegalException("403", "用户名或密码为空");
        }
        Matcher m = P.matcher(userName);
        //对用户名和密码的合法性判断
        if (m.matches()) {
            m = P_PASS.matcher(password);
            if (!m.matches()) {
                throw new IllegalException("403", "非法的密码输入");
            }
        } else {
            throw new IllegalException("403", "非法的用户名输入");
        }
        //对年龄的合法性判断
        if (ageInput == null) {
            age = 0;
        } else if (Integer.parseInt(ageInput) < 0) {
            throw new IllegalException("403", "非法的年龄输入");
        } else {
            age = Integer.parseInt(ageInput);
        }
        return new UserInfoEntity(userName, password, sex, age, personalProfiles);
    }

    /**
     * @param request 获取usercode请求
     * @return concurrentHashMap(userid, 已加密的password, usercode, time, ip)
     * @throws Exception "非法的密码输入" 及 "非法的用户名输入"异常 和加密password 可能产生的异常
     */
    public static ConcurrentHashMap<String, String> getUserCodeCheck(HttpServletRequest request) throws Exception {

        int timestamp = TimeUtils.getTimestamp(new Date());
        String ip = IpUtils.getIpAddr(request);
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");
        if (userId == null || password == null) {
            throw new IllegalException("403", "账号或密码为空");
        }
        Matcher m = P.matcher(userId);
        //对用户名和密码的合法性判断
        if (m.matches()) {
            m = P_PASS.matcher(password);
            if (!m.matches()) {
                throw new IllegalException("403", "非法的密码输入");
            }
        } else {
            throw new IllegalException("403", "非法的账号输入");
        }
        String userCode = EncryptUtils.Encrypt(ip + timestamp + userId + Math.random());
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(5);
        concurrentHashMap.put("userid", userId);
        concurrentHashMap.put("password", EncryptUtils.Encrypt(password));
        concurrentHashMap.put("usercode", userCode);
        concurrentHashMap.put("time", String.valueOf(timestamp));
        concurrentHashMap.put("ip", ip);
        return concurrentHashMap;
    }

    /**
     * @param request 登陆请求
     * @return concurrentHashMap(userid, 已加密的password)
     * @throws Exception "非法的密码输入" 及 "非法的用户名输入"异常 和加密password 可能产生的异常
     */
    public static ConcurrentHashMap<String, String> userLoginCheck(HttpServletRequest request) throws Exception {
        String password = request.getParameter("password");
        String userId = request.getParameter("userid");
        if (password == null || userId == null) {
            throw new IllegalException("403", "账号或密码为空");
        }
        Matcher m = P.matcher(userId);
        if (m.matches()) {
            m = P_PASS.matcher(password);
            if (!m.matches()) {
                throw new IllegalException("403", "非法的密码输入");
            }
        } else {
            throw new IllegalException("403", "非法的账号输入");
        }
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(2);
        concurrentHashMap.put("userid", userId);
        concurrentHashMap.put("password", EncryptUtils.Encrypt(password));
        return concurrentHashMap;
    }
}
