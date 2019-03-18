package com.springonly.springinit.BeanPostProcessorTest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/14 14:53
 * @Description:
 *
 *
 *
注意:接口中两个方法不能返回null，
如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean后置处理器
 * @author zss
 *
 */
public class PostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean,
                                                  String beanName) throws BeansException {
        if ("narCodeService".equals(beanName)) {//过滤掉bean实例ID为narCodeService
            return bean;
        }
        System.out.println("后置处理器处理bean=【"+beanName+"】开始");
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
        if ("narCodeService".equals(beanName)) {
            return bean;
        }
        System.out.println("后置处理器处理bean=【"+beanName+"】完毕!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
