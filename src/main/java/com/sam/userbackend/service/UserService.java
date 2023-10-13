package com.sam.userbackend.service;

import com.sam.userbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author 森哥
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-10-12 12:36:18
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


     /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);
}
