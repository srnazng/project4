package com.example.project4;

import java.util.ArrayList;
import java.util.List;

public enum Topping {
    PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOMS, SPINACH, BBQ_CHICKEN, OLIVES, PEPPERS, BROCCOLI, HAM, TOMATOES, PENNE, ONIONS, CILANTRO, CHEDDAR;

    public static ArrayList<Topping> getAvailableToppings(){
        return new ArrayList<>(List.of(new Topping[]{PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOMS,
                SPINACH, BBQ_CHICKEN, OLIVES, PEPPERS, BROCCOLI, HAM, TOMATOES, PENNE, ONIONS, CILANTRO, CHEDDAR}));
    }

    @Override
    public String toString(){
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase()).replace("_", " ");
    }
}
