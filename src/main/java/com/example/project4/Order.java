package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order implements Customizable{
    private boolean valid;
    private ArrayList<Pizza> pizzaList;
    private int orderNumber;
    private static final double SALES_TAX_RATE = 0.06625;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public int getOrderNumber() {
        return orderNumber;
    }

    public Order(){
        valid = true;
        orderNumber = StoreOrder.storeOrder.generateOrderId();
        pizzaList = new ArrayList<>();
    }

    public void clear(){
        pizzaList = new ArrayList<>();
    }

    public ArrayList<Pizza> getPizzaList(){
        return pizzaList;
    }

    public ArrayList<String> getPizzaDescriptionList(){
        ArrayList<String> list = new ArrayList<>();
        for(Pizza p : pizzaList){
            list.add(p.toString());
        }
        return list;
    }

    public double getSubtotal(){
        double amount = 0;
        for(Pizza p : pizzaList){
            amount += p.price();
        }
        return amount;
    }

    public double getSalesTax(){
        return getSubtotal() * SALES_TAX_RATE;
    }

    public double getTotal(){
        return getSubtotal() + getSalesTax();
    }

    public void setInvalid(){
        valid = false;
    }

    public boolean isValid(){
        return valid;
    }

    public void finishOrder(){
    }

    @Override
    public boolean add(Object obj) {
        Pizza pizza = (Pizza) obj;
        return pizzaList.add(pizza);
    }

    @Override
    public boolean remove(Object obj) {
        Pizza pizza = (Pizza) obj;
        return pizzaList.remove(pizza);
    }

    @Override
    public String toString(){
        String returnString = "Order Number " + orderNumber + "\n";
        for(Pizza p : pizzaList){
            returnString += p.toString() + "\n";
        }
        returnString += "Order total: " + df.format(getTotal()) + "\n";
        return returnString;
    }
}
