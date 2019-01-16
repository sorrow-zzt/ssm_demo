package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("permissionList",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }
}
