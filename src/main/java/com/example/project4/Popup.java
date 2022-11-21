package com.example.project4;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Popup {
    public static void showPopup(String title, String message, boolean isError){
        Alert alert;
        if (isError){
            alert = new Alert(Alert.AlertType.WARNING);
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
