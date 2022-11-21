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
        if(StoreOrder.storeOrder.getCurrentOrder().getPizzaList().size() < 1){
            Popup.showPopup("Empty Order", "No Pizzas Added to Order", true);
            return;
        }
        StoreOrder.storeOrder.completeCurrentOrder();
        initialize();
        Popup.showPopup("Order Placed", "Order Successfully Placed!", false);
    }

    @FXML
    public void removePizza(){
        if(StoreOrder.storeOrder.getCurrentOrder().getPizzaList().size() < 1 || pizzaList.getSelectionModel().getSelectedItem() == null){
            Popup.showPopup("Error Removing Pizza", "No Pizza Selected", true);
            return;
        }
        Pizza pizza = (Pizza) pizzaList.getSelectionModel().getSelectedItem();
        StoreOrder.storeOrder.getCurrentOrder().remove(pizza);
        initialize();
        Popup.showPopup("Pizza Removed", "Pizza Successfully Removed!", false);
    }

    @FXML
    public void clearOrder(){
        StoreOrder.storeOrder.getCurrentOrder().clear();
        initialize();
        Popup.showPopup("Clear Order", "Order Successfully Cleared!", false);
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
