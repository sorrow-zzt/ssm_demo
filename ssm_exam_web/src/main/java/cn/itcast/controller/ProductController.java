package cn.itcast.controller;

import cn.itcast.domain.Ids;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*@RequestMapping("/findAll")
    public String findAll(Model model){
        List<Product> product = productService.findAll();
        model.addAttribute("productList",product);
        return "forward:/pages/product-list.jsp";
    }*/

    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll")
    public String findAll(Model model,@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize){
        List<Product> product = productService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(product);
        model.addAttribute("productList",pageInfo);
        return "forward:/pages/product-list-page.jsp";
    }

    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }


    @RequestMapping("/delete")
    public String delete(Product product){
        List<Ids> idsList = product.getList();
        productService.delete(idsList);
        return "redirect:findAll";
    }
}
