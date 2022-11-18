package com.example.project4;

public class ChicagoPizza implements PizzaFactory{
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe(Crust.DEEP_DISH, true);
        return pizza;
    }

    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(Crust.STUFFED, true);
        return pizza;
    }

    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(Crust.PAN, true);
        return pizza;
    }

    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, true);
        return pizza;
    }
}
