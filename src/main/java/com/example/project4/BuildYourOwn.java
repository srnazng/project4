package com.example.project4;

public class BuildYourOwn extends Pizza{
    public static final double SMALL_PRICE = 8.99;
    public static final double MED_PRICE = 10.99;
    public static final double LARGE_PRICE = 12.99;
    public BuildYourOwn(Crust crust, boolean isChicago) {
        super(crust, isChicago);
    }

    @Override
    public double price() {
        Size size = getSize();
        double topping_price = getToppings().size() * 1.59;
        if(size == Size.SMALL) return SMALL_PRICE + topping_price;
        if(size == Size.MEDIUM) return MED_PRICE + topping_price;
        return LARGE_PRICE + topping_price;
    }

    public static double calculatePrice(Size size, int numToppings) {
        double topping_price = numToppings * 1.59;
        if(size == Size.SMALL) return SMALL_PRICE + topping_price;
        if(size == Size.MEDIUM) return MED_PRICE + topping_price;
        return LARGE_PRICE + topping_price;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Build Your Own ").append(super.toString());
        return str.toString();
    }
}
