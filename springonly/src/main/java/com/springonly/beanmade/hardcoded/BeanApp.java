package com.springonly.beanmade.hardcoded;

import com.springonly.beanmade.beanmodel.Apple;
import com.springonly.beanmade.beanmodel.Milk;
import com.springonly.beanmade.beanmodel.SuperMarket;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 19:37
 * @Description:
 */
public class BeanApp {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory beanFactory = bindViaCode(beanRegistry);
        System.out.println("getBean");
        SuperMarket superMarket = beanFactory.getBean(SuperMarket.class);
        System.out.println(superMarket);
    }

    private static BeanFactory bindViaCode(BeanDefinitionRegistry beanRegistry) {

        System.out.println("AbstractBeanDefinition");
        AbstractBeanDefinition fruit = new RootBeanDefinition(Apple.class);
        AbstractBeanDefinition drink = new RootBeanDefinition(Milk.class);
        AbstractBeanDefinition superMarket = new RootBeanDefinition(SuperMarket.class);

        System.out.println("registerBeanDefinition");
        beanRegistry.registerBeanDefinition("fruit", fruit);
        beanRegistry.registerBeanDefinition("drink", drink);
        beanRegistry.registerBeanDefinition("superMarket", superMarket);

        // 使用构造方法对属性进行设值
        System.out.println("ConstructorArgumentValues");
        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
        argumentValues.addIndexedArgumentValue(0, drink);
        argumentValues.addIndexedArgumentValue(1, fruit);
        superMarket.setConstructorArgumentValues(argumentValues);

        // 使用setter方法对属性进行设值
        System.out.println("MutablePropertyValues");
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("fruit", fruit);
        propertyValues.addPropertyValue("drink", drink);
        superMarket.setPropertyValues(propertyValues);

        return (BeanFactory) beanRegistry;
    }
}
