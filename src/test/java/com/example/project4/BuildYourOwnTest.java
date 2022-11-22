package com.example.project4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {
    private static final double smallNoTopping = 8.99;
    private static final double smallOneTopping = 10.58;
    private static final double smallTwoToppings = 12.17;
    private static final double smallThreeToppings = 13.76;
    private static final double smallFourToppings = 15.35;
    private static final double smallFiveToppings = 16.94;
    private static final double smallSixToppings = 18.53;
    private static final double smallSevenToppings = 20.12;

    private static final double mediumNoTopping = 10.99;
    private static final double mediumOneTopping = 12.58;
    private static final double mediumTwoToppings = 14.17;
    private static final double mediumThreeToppings = 15.76;
    private static final double mediumFourToppings = 17.35;
    private static final double mediumFiveToppings = 18.94;
    private static final double mediumSixToppings = 20.53;
    private static final double mediumSevenToppings = 22.12;

    private static final double largeNoTopping = 12.99;
    private static final double largeOneTopping = 14.58;
    private static final double largeTwoToppings = 16.17;
    private static final double largeThreeToppings = 17.76;
    private static final double largeFourToppings = 19.35;
    private static final double largeFiveToppings = 20.94;
    private static final double largeSixToppings = 22.53;
    private static final double largeSevenToppings = 24.12;

    private static final double toppingLimitError = -1;

    private static final double fuzzFactor = 0.0001; //how close doubles can be before they are considered equal
    @Test
    void testSmall(){
        //Small pizzas should have a 8.99 base price, and add 1.59 per topping.
        //Prices should be given by constants above
        //Price should return -1 if there are more than 7 toppings
        NYPizza factory = new NYPizza();
        Pizza pizza = factory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(pizza.price(), smallNoTopping, fuzzFactor);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), smallOneTopping, fuzzFactor);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), smallTwoToppings, fuzzFactor);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), smallThreeToppings, fuzzFactor);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), smallFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), smallFiveToppings, fuzzFactor);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), smallSixToppings, fuzzFactor);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), smallSevenToppings, fuzzFactor);

        pizza.add(Topping.PEPPERS);
        assertEquals(pizza.price(), toppingLimitError, fuzzFactor);
    }

    @Test
    void testMedium(){
        //Medium pizzas should have a 10.99 base price, and add 1.59 per topping.
        //Prices should be given by constants above
        NYPizza factory = new NYPizza();
        Pizza pizza = factory.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        assertEquals(pizza.price(), mediumNoTopping, fuzzFactor);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), mediumOneTopping, fuzzFactor);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), mediumTwoToppings, fuzzFactor);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), mediumThreeToppings, fuzzFactor);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), mediumFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), mediumFiveToppings, fuzzFactor);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), mediumSixToppings, fuzzFactor);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), mediumSevenToppings, fuzzFactor);

        pizza.add(Topping.PEPPERS);
        assertEquals(pizza.price(), toppingLimitError, fuzzFactor);
    }

    @Test
    void testLarge(){
        //Small pizzas should have a 12.99 base price, and add 1.59 per topping.
        //Prices should be given by constants above
        NYPizza factory = new NYPizza();
        Pizza pizza = factory.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        assertEquals(pizza.price(), largeNoTopping, fuzzFactor);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), largeOneTopping, fuzzFactor);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), largeTwoToppings, fuzzFactor);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), largeThreeToppings, fuzzFactor);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), largeFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), largeFiveToppings, fuzzFactor);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), largeSixToppings, fuzzFactor);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), largeSevenToppings, fuzzFactor);

        pizza.add(Topping.PEPPERS);
        assertEquals(pizza.price(), toppingLimitError, fuzzFactor);
    }
}