package com.example.authentication.entity.json;

import com.example.authentication.entity.UserInfoEntity;
import lombok.Data;

@Data
public class UserJsonEntity {
    private int  userId;
    private String userName;
    private String sex;
    private int age;
    private String personalProfiles;


    UserJsonEntity(){}

   public UserJsonEntity(UserInfoEntity userInfoEntity){
        this.userId = userInfoEntity.getUserId();
        this.userName =userInfoEntity.getUserName();
        this.sex = userInfoEntity.getSex();
        this.age = userInfoEntity.getAge();
        this.personalProfiles = userInfoEntity.getPersonalProfiles();
    }
}
