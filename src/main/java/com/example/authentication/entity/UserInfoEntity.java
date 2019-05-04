package com.example.authentication.entity;

import lombok.Data;

@Data
public class UserInfoEntity {

    private int  userId;
    private String userName;
    private String userPassword;
    private String sex;
    private int age;
    private String personalProfiles;

    public UserInfoEntity() {}
    public UserInfoEntity(String userName,String userPassword,String sex,int age,String personalProfiles){
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
        this.age = age;
        this.personalProfiles = personalProfiles;
    }
}
