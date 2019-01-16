package cn.itcast.controller;

import cn.itcast.domain.Syslog;
import cn.itcast.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize) {
        List<Syslog> syslogList = logService.findAll(page,pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(syslogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
