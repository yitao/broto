package com.broto.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yitao on 2016/7/26.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String toIndex(){
        return "/test/index";
    }

    @RequestMapping("/index2")
    public String toIndex2(){
        return "/test/index2";
    }


    @RequestMapping("/index3")
    public String toIndex3(){
        return "/test/test";
    }



    @RequestMapping("/data")
    @ResponseBody
    public String doData(){
        return "/test/index";
    }

}
