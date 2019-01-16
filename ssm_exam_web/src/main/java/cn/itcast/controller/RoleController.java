package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("roleList",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("roles",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        Role role = new Role();
        role.setId(id);
        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("pl",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId,@RequestParam(name = "ids",required = true) String[] ids ){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll";
    }
}
