package cn.itcast.controller;

import cn.itcast.domain.Syslog;
import cn.itcast.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    //开始的时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问的方法
    private Method method;

    //配置切点
    @Pointcut("execution(* cn.itcast.controller.*.*(..))")
    public void logPointcut() {

    }

    //前置通知
    @Before("logPointcut()")
    public void doBefore(JoinPoint jp) {
        try {
            visitTime = new Date();
            clazz = jp.getTarget().getClass();
            String methodName = jp.getSignature().getName();
            Object[] args = jp.getArgs();
            if (args == null || args.length == 0) {
                method = clazz.getMethod(methodName);
            } else {
                Class[] classArgs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Model) {
                        classArgs[i] = Model.class;
                        continue;
                    }
                    classArgs[i] = args[i].getClass();
                }
                method = clazz.getMethod(methodName, classArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //最终通知
    @After("logPointcut()")
    public void doAfter(JoinPoint jp) {
        try {
            Long time = System.currentTimeMillis() - visitTime.getTime();
            String url = "";
            if (clazz != null && clazz != LogAop.class && method != null) {
                RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                if (annotation != null) {
                    String[] value = annotation.value();
                    if (value == null || value.length == 0) {
                        value = annotation.path();
                    }
                    RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                    if (annotation1 != null) {
                        String[] value1 = annotation1.value();
                        if (value1 == null || value1.length == 0) {
                            value1 = annotation1.path();
                        }
                        url = value[0] + value1[0];

                        String ip = request.getRemoteAddr();

                        SecurityContext context = SecurityContextHolder.getContext();
                        User user = (User) context.getAuthentication().getPrincipal();
                        String username = user.getUsername();
                        Syslog syslog = new Syslog();
                        //执行时长
                        syslog.setExecutionTime(time);
                        syslog.setIp(ip);
                        syslog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                        syslog.setUrl(url);
                        syslog.setUsername(username);
                        syslog.setVisitTime(visitTime);

                        //调用Service完成操作
                        logService.saveSysLog(syslog);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
