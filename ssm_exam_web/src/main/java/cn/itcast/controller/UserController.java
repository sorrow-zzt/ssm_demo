package cn.itcast.controller;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list-page");
        return mv;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo){

        userService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        UserInfo userInfo = userService.findById(id);
        List<Role> roleList = userService.findUserByIdAndAllRole(userInfo.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,@RequestParam(name = "ids",required = true) String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }

}
