package com.example.project4;

public class NYPizza implements PizzaFactory{
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe(Crust.BROOKLYN, false);
        return pizza;
    }

    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(Crust.THIN, false);
        return pizza;
    }

    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(Crust.HAND_TOSSED, false);
        return pizza;
    }

    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(Crust.HAND_TOSSED, false);
        return pizza;
    }
}
