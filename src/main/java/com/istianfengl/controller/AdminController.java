package com.istianfengl.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.istianfengl.common.Result;
import com.istianfengl.entity.Admin;
import com.istianfengl.mapper.AdminMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class AdminController {

    @Resource
    private AdminMapper adminMapper;

    @PostMapping
    public Result<?> login(@RequestBody Admin admin) {
        Admin ad = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getName,
                admin.getName()).eq(Admin::getPassword, admin.getPassword()));

        if (ad == null) {
            return Result.error("-1", "用户名或密码错误");
        }

        return Result.success();

    }
}
