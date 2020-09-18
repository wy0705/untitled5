package controller;
import dao.OrderDao;
import entity.Orderr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.OrderrServiceImpl;

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
}
