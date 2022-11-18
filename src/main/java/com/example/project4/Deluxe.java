package com.example.project4;

import java.util.ArrayList;
import java.util.List;

public class Deluxe extends Pizza{
    public static final double SMALL_PRICE = 14.99;
    public static final double MED_PRICE = 16.99;
    public static final double LARGE_PRICE = 18.99;

    public Deluxe(Crust crust, boolean isChicago){
        super(crust, isChicago);
    }

    public static ArrayList<Topping> getDeluxeToppings(){
        return new ArrayList<>(List.of(new Topping[]{Topping.PEPPERONI, Topping.SAUSAGE,
                Topping.MUSHROOMS, Topping.ONIONS, Topping.PEPPERS}));
    }

    @Override
    public double price() {
        Size size = getSize();
        if(size == Size.SMALL) return SMALL_PRICE;
        if(size == Size.MEDIUM) return MED_PRICE;
        return LARGE_PRICE;
    }

    public static double calculatePrice(Size size) {
        if(size == Size.SMALL) return SMALL_PRICE;
        if(size == Size.MEDIUM) return MED_PRICE;
        return LARGE_PRICE;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Deluxe ").append(super.toString());
        return str.toString();
    }
}
