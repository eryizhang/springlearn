package com.springonly.springinit.BeanPostProcessorTest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 14:55
 * @Description:
 */
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostProcessorTest{
    AbstractApplicationContext applicationcontext=null;
    @Before
    public void before() {
        System.out.println("》》》Spring ApplicationContext容器开始初始化了......");
        applicationcontext= new ClassPathXmlApplicationContext(new String[]{"test1-service.xml"});
        System.out.println("》》》Spring ApplicationContext容器初始化完毕了......");
    }
    @Test
    public void  test() {
        applicationcontext.registerShutdownHook();
    }
}
