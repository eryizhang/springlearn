package com.springonly.springinit.BeanFactoryPostProcessorTest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 16:32
 * @Description:
 */
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCase {
    ApplicationContext applicationcontext=null;
    @Before
    public void before() {
        System.out.println("》》》Spring ApplicationContext容器开始初始化了......");
        applicationcontext= new ClassPathXmlApplicationContext(new String[]{"BeanFactoryPostProcessor-service.xml"});
        System.out.println("》》》Spring ApplicationContext容器初始化完毕了......");
    }
    @Test
    public void  test() {
        //BeanLifecycle beanLifecycle =applicationcontext.getBean("beanLifecycle",BeanLifecycle.class);
        BeanFactoryPostProcessorTest beanFactoryPostProcessorTest=applicationcontext.getBean(BeanFactoryPostProcessorTest.class);
        System.out.println(beanFactoryPostProcessorTest.toString());
    }
}