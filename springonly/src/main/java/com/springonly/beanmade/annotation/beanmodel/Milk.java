package com.springonly.beanmade.annotation.beanmodel;

import org.springframework.stereotype.Component;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 19:36
 * @Description:
 */
@Component
public class Milk implements DrinkProvider {
    @Override
    public String toString() {
        return "this is milk";
    }

    public Milk()
    {
        System.out.println("Milk default");
    }
}
