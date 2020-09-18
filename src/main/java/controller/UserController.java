package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import service.UserServiceImpl;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        User user = userService.findByNameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user == null || user.getUname() == null) {
            return "用户不存在或用户名、密码错误";
        }
        return "hello" + user.getUname();
    }

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    @ResponseBody
    public String registry(@RequestBody User user) {
        boolean register = userService.register(user);
        if (register) {
            return "hello" + user.getUname();
        }
        return "注册失败";
    }


    @RequestMapping(value = "find_name", method = RequestMethod.GET)
    @ResponseBody
    public String registry(@RequestParam String username) {
        User userByName = userService.findByName(username);
        if (userByName != null) {
            return userByName.getUname();
        }
        return "未找到该用户";
    }


    @RequestMapping(value = "find_id", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@RequestParam int id) {
        User userById = userService.findByUserId(id);
        if (userById != null) {
            return userById.getUname();
        }
        return "未找到该用户";
    }


    @RequestMapping(value = "update_name", method = RequestMethod.POST)
    @ResponseBody
    public String updateName(@RequestBody User user) {
        int count = userService.updateUserName(user);
        if (count != 0) {
            return "更新成功";
        }
        return "未找到该用户";
    }

}
