package com.springonly.beanmade.annotation.beanmodel;

import org.springframework.stereotype.Component;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 19:37
 * @Description:
 */
@Component
public class Apple implements FruitProvider {
    @Override
    public String toString() {
        return "this is an apple";
    }

    public Apple()
    {
        System.out.println("Apple default");
    }
}
