package com.controller;

import com.entity.Orderr;
import com.service.OrderrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderrController {
    @Autowired
    private OrderrServiceImpl orderrService;

    @RequestMapping(value = "find_order_byUserId", method = RequestMethod.GET)
    @ResponseBody
    public List<Orderr> registry(@RequestParam int uid) {
        return orderrService.findByUserId(uid);

    }
    @RequestMapping(value = "delect_order", method = RequestMethod.GET)
    @ResponseBody
    public int insertOrderr(Orderr orderr){
        return orderrService.delectOrderr(orderr);
    }
}
