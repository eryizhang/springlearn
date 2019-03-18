package com.springonly.beanmade.annotation.beanmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 19:35
 * @Description:
 */
@Component
public class SuperMarket {
    @Autowired
    private DrinkProvider drink;
    @Autowired
    private FruitProvider fruit;

    public SuperMarket() {

        System.out.println("SuperMarket default constructor");
    }

    public SuperMarket(DrinkProvider drink, FruitProvider fruit) {
        this.drink = drink;
        this.fruit = fruit;

        System.out.println("SuperMarket constructor drink:"+drink+",fruit:"+fruit);
    }

    public void setDrink(DrinkProvider drink) {
        System.out.println("SuperMarket setDrink:"+drink);
        this.drink = drink;
    }

    public void setFruit(FruitProvider fruit) {
        System.out.println("SuperMarket setFruit:"+fruit);
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return "to String drink: " + drink + ", fruit: " + fruit;
    }
}
