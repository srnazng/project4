package com.example.project4;

import java.util.ArrayList;

public class StoreOrder implements Customizable{
    private static ArrayList<Order> orderList;
    private static Order currentOrder;

    public static void completeCurrentOrder(){
        orderList.add(currentOrder);
        currentOrder.finishOrder();
        currentOrder = new Order();
    }

    public static Order getCurrentOrder(){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        if(currentOrder == null){
            currentOrder = new Order();
        }
        return currentOrder;
    }

    public static ArrayList<Integer> getOrderNumbers(){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(Order o : orderList){
            if(o.isValid()){
                list.add(o.getOrderNumber());
            }
        }
        return list;
    }

    public static int generateOrderId(){
        return orderList.size() + 1;
    }

    public static Order getOrder(int id){
        for(Order o : orderList){
            if(o.getOrderNumber() == id){
                return o;
            }
        }
        return null;
    }

    public static boolean cancelOrder(int id){
        Order o = getOrder(id);
        return orderList.remove(o);
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * Marks order as invalid (to keep id numbers unique)
     * @param obj   Order
     * @return      true if success, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        Order o = (Order) obj;
        if(o.isValid()){
            o.setInvalid();
            return true;
        }

        return false;
    }
}
