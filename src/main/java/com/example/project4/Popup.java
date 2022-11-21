package com.example.project4;

import javafx.scene.control.Alert;

/**
 * The Popup class creates alert dialogs for confirmation and warning messages.
 * @author Serena Zeng, Jackson Lee
 */
public class Popup {

    /**
     * Show a dialog with given information
     * @param title     Title of alert
     * @param message   Alert message
     * @param isError   true if is error message, false otherwise
     */
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
