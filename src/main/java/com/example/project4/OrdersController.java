package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The OrdersController class is the controller for the orders-view.fxml file.
 * This class initializes UI components and handles user interaction events.
 * @author Serena Zeng, Jackson Lee
 */
public class OrdersController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    ComboBox orderNumber;
    @FXML
    TextField orderTotal;
    @FXML
    ListView pizzaList;

    /**
     * Initialize UI including order number, order details, and total.
     */
    @FXML
    public void initialize(){
        ArrayList<Integer> orderNumberList = StoreOrder.storeOrder.getOrderNumbers();
        orderNumber.setItems(FXCollections.observableArrayList(orderNumberList));
        if(orderNumberList.size() > 0){
            Integer orderNum = orderNumberList.get(0);
            orderNumber.setValue(orderNum);
            setOrder();
        }
        else{
            pizzaList.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            orderTotal.setText("0.00");
        }
    }

    /**
     * Update order information when new order number selected.
     */
    @FXML
    public void setOrder(){
        Integer orderNum = (Integer) orderNumber.getValue();
        if(orderNum != null){
            Order order = StoreOrder.storeOrder.getOrder(orderNum);
            pizzaList.setItems(FXCollections.observableArrayList(order.getPizzaList()));
            orderTotal.setText(df.format(order.getTotal()));
        }
        else{
            orderTotal.setText("0.00");
        }
    }

    /**
     * Delete selected order. Show popup on success and on failure.
     */
    @FXML
    public void cancelOrder(){
        if(orderNumber.getValue() == null){
            Popup.showPopup("Cancel Order Error", "No Order Selected", true);
            return;
        }

        Integer orderNum = (Integer) orderNumber.getValue();

        StoreOrder.storeOrder.cancelOrder(orderNum);
        initialize();
        Popup.showPopup("Cancel Order", "Order Successfully Cancelled!", false);
    }

    /**
     * Called when export button clicked. Export order details to a text file.
     */
    @FXML
    public void exportStoreOrders(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file

        StoreOrder.storeOrder.export(targetFile);
    }
}
