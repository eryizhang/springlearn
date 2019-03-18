package com.springonly.aoplearn.aopimplementation;

import com.springonly.aoplearn.exception.CommonException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/18 15:18
 * @Description:
 */
@Component
@Aspect
public class ControllerAOPLog {
    private static final Log LOG= LogFactory.getLog(ControllerAOPLog.class);

    //设置切点
    @Pointcut("execution(public * com.springonly.aoplearn.controller..*.*(..))")
    public void executeMethond() {
    }

    @Before(value = "executeMethond()")
    public void beforeAdvice(JoinPoint proceedingJoinPoint) {
        System.out.println("Before");
        //获取目标类
        Object target = proceedingJoinPoint.getTarget();
        //将要执行的方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将要执行的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //日志对象
        Log LOG  = LogFactory.getLog(target.getClass());
        StringBuffer logs = new StringBuffer("---------" + target.getClass() + "/" + methodName + "---------start-parameter:[");
        for (Object obj : args) {
            logs.append(obj.toString());
        }
        LOG.info(logs.append("]").toString());
    }

    @AfterReturning(returning = "ret", pointcut = "executeMethond()")
    public void afterAdvice(JoinPoint proceedingJoinPoint, Object ret) {
        //获取目标类
        Object target = proceedingJoinPoint.getTarget();
        String methodName = proceedingJoinPoint.getSignature().getName();
        //日志对象
        Log LOG= LogFactory.getLog(target.getClass());
        LOG.info("---------" + target.getClass() + "/" + methodName + "---------end-result:" + ret);

    }

    @AfterThrowing(throwing = "e", pointcut = "executeMethond()")
    public void throwingAdvice(JoinPoint proceedingJoinPoint, Exception e) {
        Object target = proceedingJoinPoint.getTarget();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Log LOG= LogFactory.getLog(target.getClass());
        StringBuffer sb = new StringBuffer("---------" + target.getClass() + "/" + methodName + "---------end-exception:");
        if (e.getMessage() == null) {
            sb.append(((CommonException) e).getMessage());
        } else {
            sb.append(e.getMessage());
        }
        LOG.info(sb.toString());
    }
}
