package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class CartController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    ListView pizzaList;
    @FXML
    TextField orderNumber, subtotal, salesTax, orderTotal;

    @FXML
    public void placeOrder(){
        StoreOrder.storeOrder.completeCurrentOrder();
        initialize();
    }

    @FXML
    public void removePizza(){
        Pizza pizza = (Pizza) pizzaList.getSelectionModel().getSelectedItem();
        StoreOrder.storeOrder.getCurrentOrder().remove(pizza);
        initialize();
    }

    @FXML
    public void clearOrder(){
        StoreOrder.storeOrder.getCurrentOrder().clear();
        initialize();
    }

    @FXML
    public void initialize(){
        pizzaList.setItems(FXCollections.observableArrayList(StoreOrder.storeOrder.getCurrentOrder().getPizzaList()));
        subtotal.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getSubtotal()));
        salesTax.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getSalesTax()));
        orderTotal.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getTotal()));
        orderNumber.setText(StoreOrder.storeOrder.generateOrderId() + "");
    }
}
