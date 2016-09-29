package com.broto.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yitao on 2016/9/29.
 */
@Controller
@RequestMapping("/bs/permission")
public class PermissionController {
    //模块功能管理页面
    @RequestMapping("/toma")
    public String toMA(HttpServletRequest req){
        return "/admin/permission/ma";
    }

    //角色管理页面
    @RequestMapping("/torole")
    public String toRole(HttpServletRequest req){
        return "/admin/permission/role";
    }

    //账号管理页面
    @RequestMapping("/toaccount")
    public String toAccount(HttpServletRequest req){
        return "/admin/permission/account";
    }
    //角色模块功能管理页面
    @RequestMapping("/torma")
    public String toRMA(HttpServletRequest req){
        return "/admin/permission/rma";
    }

    //账号角色管理页面
    @RequestMapping("/toar")
    public String toAR(HttpServletRequest req){
        return "/admin/permission/ar";
    }

    //角色可见性管理页面
    @RequestMapping("/tor2r")
    public String toR2R(HttpServletRequest req){
        return "/admin/permission/r2r";
    }

    //角色功能模块可见性管理页面
    @RequestMapping("/tor2ma")
    public String toR2MA(HttpServletRequest req){
        return "/admin/permission/r2ma";
    }
}
