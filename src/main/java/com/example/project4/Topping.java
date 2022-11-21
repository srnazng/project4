package com.example.project4;

import java.util.ArrayList;
import java.util.List;

/**
 * The Topping enum contains all possible pizza toppings
 * @author Serena Zeng, Jackson Lee
 */
public enum Topping {
    PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOMS, SPINACH, BBQ_CHICKEN, OLIVES, PEPPERS, BROCCOLI, HAM, TOMATOES, PENNE, ONIONS, CILANTRO, CHEDDAR;

    /**
     * Get list of all toppings
     * @return  ArrayList of Topping objects
     */
    public static ArrayList<Topping> getAvailableToppings(){
        return new ArrayList<>(List.of(PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOMS,
                SPINACH, BBQ_CHICKEN, OLIVES, PEPPERS, BROCCOLI, HAM, TOMATOES, PENNE, ONIONS, CILANTRO, CHEDDAR));
    }

    /**
     * Convert Topping to formatted String
     * @return  String of Topping
     */
    @Override
    public String toString(){
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase()).replace("_", " ");
    }
}
