package com.example.authentication.mapper;

import com.example.authentication.entity.LoginInfoEntity;
import com.example.authentication.entity.UserInfoEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginInfoMapper {


    /**
     * 在usercode登陆记录表中加入一条新的usercode记录
     * @param loginInfoEntity 登陆记录表实体
     * @return Integer型变量 受影响的行数
     */
    @Insert("INSERT INTO login_info(user_code,time,used,user_id) VALUES (#{userCode},#{time},#{used},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "loginInfoId", keyColumn = "loginInfoId")
    @Results(
            @Result(property = "loginInfoId", column = "login_info_id")
    )
    Integer add(LoginInfoEntity loginInfoEntity);


    /**
     * usercode登陆检测，是否存在或使用过
     * @param userCode  拉取用户信息加密后的String
     * @return used(1->表示还没用userCode来取用户信息,0->表示已经取了，null->表示未登陆过)
     */
    @Select("SELECT * FROM login_info WHERE user_code = #{userCode}")
    @Results({
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userId", column = "user_id")
    }
    )
    LoginInfoEntity codeLogin (String userCode);

    /**
     * 使用usercode拉取用户信息，将usercode失效
     * @param userCode  拉取用户信息加密后的String
     * @return Integer型变量 受影响的行数
     */
    @Update("UPDATE login_info SET used = -1  WHERE user_code = #{userCode}")
    Integer update(String userCode);


    // 以下操作的是user_info表
    /**
     *在user_info中查询用户信息
     * @param userId 用户id
     * @return 返回UserInfoEntity型变量
     */
    @Select("SELECT * from user_info WHERE user_Id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "personalProfiles", column = "personal_profiles")
    }
    )
    UserInfoEntity selectUser(Integer userId );

    /**
     * 查询用户的密码
     * @param userId 用户id
     * @return 返回String(password)型变量
     */
    @Select("SELECT user_password FROM user_info WHERE user_id = #{userId}")
    String loginCheck(Integer userId);

}
