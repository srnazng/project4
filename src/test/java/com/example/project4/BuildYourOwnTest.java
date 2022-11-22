package com.example.project4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {
    private static final double SMALL_NO_TOPPING = 8.99;
    private static final double SMALL_ONE_TOPPING = 10.58;
    private static final double SMALL_TWO_TOPPINGS = 12.17;
    private static final double SMALL_THREE_TOPPINGS = 13.76;
    private static final double SMALL_FOUR_TOPPINGS = 15.35;
    private static final double SMALL_FIVE_TOPPINGS = 16.94;
    private static final double SMALL_SIX_TOPPINGS = 18.53;
    private static final double SMALL_SEVEN_TOPPINGS = 20.12;

    private static final double MEDIUM_NO_TOPPING = 10.99;
    private static final double MEDIUM_ONE_TOPPING = 12.58;
    private static final double MEDIUM_TWO_TOPPINGS = 14.17;
    private static final double MEDIUM_THREE_TOPPINGS = 15.76;
    private static final double MEDIUM_FOUR_TOPPINGS = 17.35;
    private static final double MEDIUM_FIVE_TOPPINGS = 18.94;
    private static final double MEDIUM_SIX_TOPPINGS = 20.53;
    private static final double MEDIUM_SEVEN_TOPPINGS = 22.12;

    private static final double LARGE_NO_TOPPING = 12.99;
    private static final double LARGE_ONE_TOPPING = 14.58;
    private static final double LARGE_TWO_TOPPINGS = 16.17;
    private static final double LARGE_THREE_TOPPINGS = 17.76;
    private static final double LARGE_FOUR_TOPPINGS = 19.35;
    private static final double LARGE_FIVE_TOPPINGS = 20.94;
    private static final double LARGE_SIX_TOPPINGS = 22.53;
    private static final double LARGE_SEVEN_TOPPINGS = 24.12;

    private static final double TOPPING_LIMIT_ERROR = -1;

    private static final double FUZZ_FACTOR = 0.0001; //how close doubles can be before they are considered equal
    @Test
    void testSmall(){
        //Small pizzas should have a 8.99 base price, and add 1.59 per topping.
        //Prices should be given by constants above
        //Price should return -1 if there are more than 7 toppings
        NYPizza factory = new NYPizza();
        Pizza pizza = factory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(pizza.price(), SMALL_NO_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), SMALL_ONE_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), SMALL_TWO_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), SMALL_THREE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), smallFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), SMALL_FIVE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), SMALL_SIX_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), SMALL_SEVEN_TOPPINGS, FUZZ_FACTOR);

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
        assertEquals(pizza.price(), MEDIUM_NO_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), MEDIUM_ONE_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), MEDIUM_TWO_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), MEDIUM_THREE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), mediumFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), MEDIUM_FIVE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), MEDIUM_SIX_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), MEDIUM_SEVEN_TOPPINGS, FUZZ_FACTOR);

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
        assertEquals(pizza.price(), LARGE_NO_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.PEPPERONI);
        assertEquals(pizza.price(), LARGE_ONE_TOPPING, FUZZ_FACTOR);

        pizza.add(Topping.SAUSAGE);
        assertEquals(pizza.price(), LARGE_TWO_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.PINEAPPLE);
        assertEquals(pizza.price(), LARGE_THREE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.MUSHROOMS);
        assertEquals(pizza.price(), largeFourToppings, fuzzFactor);

        pizza.add(Topping.BROCCOLI);
        assertEquals(pizza.price(), LARGE_FIVE_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BBQ_CHICKEN);
        assertEquals(pizza.price(), LARGE_SIX_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.BEEF);
        assertEquals(pizza.price(), LARGE_SEVEN_TOPPINGS, FUZZ_FACTOR);

        pizza.add(Topping.PEPPERS);
        assertEquals(pizza.price(), toppingLimitError, fuzzFactor);
    }
}