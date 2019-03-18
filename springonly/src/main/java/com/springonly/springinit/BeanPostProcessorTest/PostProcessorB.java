package com.springonly.springinit.BeanPostProcessorTest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 14:54
 * @Description:
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class PostProcessorB implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean,
                                                  String beanName) throws BeansException {
        System.out.println("后置处理器开始调用了");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean,
                                                 String beanName) throws BeansException {
        System.out.println("后置处理器调用结束了");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
