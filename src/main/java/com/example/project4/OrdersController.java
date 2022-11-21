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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    ComboBox orderNumber;
    @FXML
    TextField orderTotal;
    @FXML
    ListView pizzaList;

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

    @FXML
    public void cancelOrder(){
        Integer orderNum = (Integer) orderNumber.getValue();
        StoreOrder.storeOrder.cancelOrder(orderNum);
        initialize();
    }

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
