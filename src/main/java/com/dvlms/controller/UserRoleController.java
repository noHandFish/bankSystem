package com.dvlms.controller;


import com.dvlms.common.dto.PassDto;
import com.dvlms.common.lang.Result;
import com.dvlms.entity.LoanInfo;
import com.dvlms.entity.UserRole;
import com.dvlms.service.UserRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhb
 * @since 2022-03-07
 */
@RestController
@RequestMapping("/user-role")
@Api(tags = {"用户登录、修改密码、更新模块"})
public class UserRoleController<T> {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(userRoleService.list());
    }

    //查询id
    @GetMapping("/info/{username}")
    public Object info(@PathVariable("username") String username) {
        return new Result<>(userRoleService.getById(username));
    }

    //注册
    @PostMapping("/saveRegister")
    public Object saveRegister(@Validated @RequestBody UserRole userRole) {
        if (userRoleService.getByUserName(userRole.getUserName()) != null) {
            //已经存在用户名
            return new Result<>("", 400, "用户名已存在");
        } else {
            userRole.setPassword(bCryptPasswordEncoder.encode(userRole.getPassword())); //对密码加密
            userRoleService.save(userRole);
            return new Result<>(userRole);
        }
    }

    //修改密码
    @PostMapping("/updatePassword")// 有问题需要调整
    public Object updatePassword(@Validated @RequestBody PassDto passDto) {
        UserRole userRole = userRoleService.getByUserName(passDto.getUsername());
        if (userRoleService.getByUserName(userRole.getUserName()) != null) {
            //已经存在用户名
            boolean matches = bCryptPasswordEncoder.matches(passDto.getCurrentPass(), userRole.getPassword());
            if (matches == false) return new Result<>(null, 400, "旧密码不正确！");
            if (!passDto.getPassword().equals(passDto.getPasswordAgainInput()))return new Result<>(null, 400, "两次输入密码不相同！");
            userRole.setPassword(bCryptPasswordEncoder.encode(passDto.getPassword())); //设置修改后的密码
            userRoleService.updateById(userRole);
            return new Result<>(userRole);
        } else {
            //不存在用户名
            return new Result<>("", 400, "用户名不存在");

        }
    }

    //删除 根据id
    @PostMapping("/delete/{username}")
    public Object deleteUsers(@PathVariable("username") String username) {
        userRoleService.removeById(username);
        return new Result<>(userRoleService.list());
    }
}
