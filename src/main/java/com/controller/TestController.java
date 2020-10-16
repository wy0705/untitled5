package com.controller;


import com.annotation.LogRecord;
import com.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @LogRecord(operation = "打招呼", type = "测试")
    public String hello(@RequestParam(name = "a") String num1, @RequestParam(name = "b") String num2) {
        return "cccdddd" + num1 + num2;
    }


    @RequestMapping(value = "/hello2/{a}/{b}", method = RequestMethod.GET)
    @ResponseBody
    @LogRecord(operation = "打招呼2", type = "测试")
    public String hello2(@PathVariable(name = "a") int num1, @PathVariable(name = "b") int num2) {
        return "cccdddd" + num1 + num2;
    }


    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(operation = "打招呼3", type = "测试")
    public String hello3(@RequestBody User user) {
        return user.getUname();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloPage() {
        return "index";
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public User userInfo() {
        User user = new User();
        user.setAge(11);
        user.setUname("abc");
        return user;
    }
}
