package com.example.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The MainController class is the controller for the main-view.fxml file.
 * This class initializes UI components and handles user interaction events.
 * @author Serena Zeng, Jackson Lee
 */
public class MainController {

    /**
     * Load Chicago Pizza view (create pizza)
     */
    @FXML
    protected void onChicagoClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chicago-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Chicago Style Pizza");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load New York Pizza view (create pizza)
     */
    @FXML
    protected void onNewYorkClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ny-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New York Style Pizza");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load store orders view (see all orders)
     */
    @FXML
    protected void onOrdersClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orders-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Store Orders");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load shopping cart view (see current order)
     */
    @FXML
    protected void onCartClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Shopping Cart");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}