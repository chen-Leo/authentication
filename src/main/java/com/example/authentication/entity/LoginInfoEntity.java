package com.example.authentication.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 登陆信息表
 * @author hello
 */
@Data
public class LoginInfoEntity {

    private int loginInfoId;
    /**
     * 拉取用户信息加密过的字符串
     */
    private String userCode;
    private String time;
    /**
     * 表示是否使用过（1->没用，
     */
    private int used;
    /**
     * 与用户账户对应
     */
    private int userId;
    public LoginInfoEntity(){}
    public LoginInfoEntity(String userCode,String time,int used,int userId){
        this.userCode = userCode;
        this.time = time;
        this.used = used;
        this.userId =userId;
    }
}
