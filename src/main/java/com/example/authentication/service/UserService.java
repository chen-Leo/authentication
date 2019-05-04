package com.example.authentication.service;
import com.example.authentication.entity.UserInfoEntity;
import com.example.authentication.entity.json.UserJsonEntity;
import com.example.authentication.exception.IllegalException;
import com.example.authentication.mapper.UserInfoMapper;
import com.example.authentication.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hello
 */
@Service
public class UserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    /**
     * 注册
     * @param userInfoEntity 用户表实体
     * @return UserJsonEntity 返回给前端的用户信息json实体
     * @throws Exception password加密可能产生的异常
     */
    public UserJsonEntity add(UserInfoEntity userInfoEntity) throws Exception {
          userInfoEntity.setUserPassword(EncryptUtils.Encrypt(userInfoEntity.getUserPassword()));
          userInfoMapper.add(userInfoEntity);
          return new UserJsonEntity(userInfoEntity);
    }
    /**
     * 正常登陆
     * @param userId 用户账号
     * @param password 密码
     * @return UserJsonEntity 返回给前端的用户信息json实体
     * @throws IllegalException "密码为空","密码错误"异常 及password加密可能产生的异常
     */
    public UserJsonEntity login(String userId,String password)  throws IllegalException {
        UserInfoEntity userInfoEntity =  userInfoMapper.select(Integer.valueOf(userId));
        if (userInfoEntity ==null){
            throw new IllegalException("403","用户不存在");
        }
        if(!password.equals(userInfoEntity.getUserPassword())){
            throw new IllegalException("403","密码错误");
        }
        else {
            return  new UserJsonEntity(userInfoEntity);
        }
    }
}
