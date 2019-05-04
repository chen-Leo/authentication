package com.example.authentication.mapper;

import com.example.authentication.entity.UserInfoEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {
    /**
     * 加入新的一条用户记录
     * @param userInfoEntity 用户信息表
     * @return Integer型变量
     */
    @Insert("INSERT INTO user_info(user_name,user_password,sex,age,personal_profiles) VALUES(#{userName},#{userPassword},#{sex},#{age},#{personalProfiles})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    @Results(
            @Result(property = "userId", column = "user_id")
    )
    Integer add(UserInfoEntity userInfoEntity);


    /**
     * 查询用户信息
     * @param userId 用户账号id
     * @return UserInfoEntity 用户数据表实体
     */
     @Select("SELECT * FROM user_info where user_id = #{userId}")
     @Results({
             @Result(property = "userId", column = "user_id"),
             @Result(property = "userName", column = "user_name"),
             @Result(property = "userPassword", column = "user_password"),
             @Result(property = "personalProfiles", column = "personal_profiles")
     }
     )
     UserInfoEntity select(Integer userId);


}
