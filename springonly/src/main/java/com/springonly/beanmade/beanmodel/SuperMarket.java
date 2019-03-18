package com.springonly.beanmade.beanmodel;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/12 19:35
 * @Description:
 */
public class SuperMarket {
    private DrinkProvider drink;
    private FruitProvider fruit;


    public void init() {
        System.out.println("SuperMarket init");
    }

    public void close() {
        System.out.println("SuperMarket close");
    }

    public SuperMarket() {

        System.out.println("SuperMarket default");
    }

    public SuperMarket(DrinkProvider drink, FruitProvider fruit) {
        this.drink = drink;
        this.fruit = fruit;

        System.out.println("SuperMarket drink:" + drink + ",fruit:" + fruit);
    }

    public void setDrink(DrinkProvider drink) {
        System.out.println("SuperMarket setDrink");
        this.drink = drink;
    }

    public void setFruit(FruitProvider fruit) {
        System.out.println("SuperMarket setFruit");
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return "to String drink: " + drink + ", fruit: " + fruit;
    }
}
