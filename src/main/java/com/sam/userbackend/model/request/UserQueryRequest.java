package com.sam.userbackend.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class UserQueryRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 性别
     */
    private int gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private int userStatus;

    /**
     * 用户角色：user/admin/ban
     */
    private int userRole;
}