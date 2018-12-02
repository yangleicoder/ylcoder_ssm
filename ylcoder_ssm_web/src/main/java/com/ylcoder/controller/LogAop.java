package com.ylcoder.controller;

import com.ylcdoer.domain.SysLog;
import com.ylcoder.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
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
    private SysLogService sysLogService;

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法


    //前置通知主要获取访问时间、访问的类、访问的方法
    @Before("execution(* com.ylcoder.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取登录时间
        startTime = new Date();
        //获取访问的类  对象
        executionClass = jp.getTarget().getClass();
        //获取方法名
        String methodName = jp.getSignature().getName();

        //获取方参数
        Object[] args = jp.getArgs();
        //没有参数
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);
        } else {
            //有参数
            Class[] classes = new Class[args.length];
            //遍历数组存入classes数组
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            //获取有参数的方法
            executionMethod = executionClass.getMethod(methodName, classes);
        }

    }


    //后置通知
    @After("execution(* com.ylcoder.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {

        // 获取访问时长
        Long executionTime = System.currentTimeMillis() - startTime.getTime();
        //url
        String url="";
        //获取url
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class) {
            //获取上的注解
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                //获取方法上的注解
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if(methodAnnotation!= null){
                    url=classAnnotation.value()[0]+methodAnnotation.value()[0];
                }
            }
        }

        //获取ip
        String ip = request.getRemoteAddr();
        //获取登录者
        // 可以通过securityContext获取，也可以从request.getSession中获取
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

        SysLog sysLog=new SysLog();
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);
        sysLog.setExecutionTime(executionTime);
        sysLog.setMethod(("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName()));

        sysLogService.save(sysLog);

    }
}
