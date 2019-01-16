package cn.itcast.controller;

import cn.itcast.domain.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request,@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize){
        List<Orders> orders = ordersService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(orders);
        request.getSession().setAttribute("ordersList",pageInfo);
        return "redirect:/pages/orders-list-page.jsp";
    }

    @RequestMapping("/findById")
    public String findById(HttpServletRequest request,@RequestParam(name = "id",required = true) String id){
        Orders orders = ordersService.findById(id);
        request.getSession().setAttribute("orders",orders);
        return "redirect:/pages/orders-show.jsp";
    }
}
