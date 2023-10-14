package com.sam.userbackend.mapper;

import com.sam.userbackend.model.domain.User;
import com.sam.userbackend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserMapperTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("Lin");
        user.setUserAccount("200303");
        user.setuserAvatar("https://image-1309124269.cos.ap-nanjing.myqcloud.com/linbi/a90da89b-4e7a-4fc6-924b-0f5f41491f5c.jpg");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("123456");
        user.setEmail("123456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "12345678";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, l);
    }
}