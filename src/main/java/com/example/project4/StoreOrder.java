package com.example.project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StoreOrder implements Customizable{
    private ArrayList<Order> orderList;
    private Order currentOrder;

    public static StoreOrder storeOrder = new StoreOrder();

    public void completeCurrentOrder(){
        orderList.add(currentOrder);
        currentOrder.finishOrder();
        currentOrder = new Order();
    }

    public Order getCurrentOrder(){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        if(currentOrder == null){
            currentOrder = new Order();
        }
        return currentOrder;
    }

    public ArrayList<Integer> getOrderNumbers(){
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

    public int generateOrderId(){
        return orderList.size() + 1;
    }

    public Order getOrder(int id){
        for(Order o : orderList){
            if(o.getOrderNumber() == id){
                return o;
            }
        }
        return null;
    }

    public boolean cancelOrder(int id){
        Order o = getOrder(id);
        return orderList.remove(o);
    }

    public void export(File file){
        try{
            PrintWriter pw = new PrintWriter(file);
            for (Order order : orderList){
                pw.println(order);
            }

            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
