package com.sam.userbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sam.userbackend.model.domain.User;
import com.sam.userbackend.model.request.UserLoginRequest;
import com.sam.userbackend.model.request.UserRegisterRequest;
import com.sam.userbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sam.userbackend.constant.UserConstant.ADMIN_ROLE;
import static com.sam.userbackend.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求数据
     * @return 用户Id
     */
    @PostMapping("register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }

        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求数据
     * @param request Http请求，用于设置session
     * @return 用户信息脱敏后数据
     */
    @PostMapping("login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        return userService.userLogin(userAccount, userPassword, request);
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return 用户脱敏后信息
     */
    @GetMapping("get/login")
    public User getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return userService.getSafetyUser(loginUser);
    }

    /**
     * 查询用户
     * @param username 用户名
     * @param request Http请求
     * @return 符合要求的用户信息
     */
    @GetMapping("search")
    public List<User> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    /**
     * 删除用户
     * @param id 用户Id
     * @param request Http请求
     * @return 是否成功删除用户
     */
    @PostMapping("delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 用户鉴权
     * @param request Http请求
     * @return 用户是否为管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
