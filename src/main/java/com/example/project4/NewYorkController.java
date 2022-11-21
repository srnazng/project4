package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NewYorkController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ArrayList<Topping> availableToppings;
    private ArrayList<Topping> selectedToppings;
    private double price;
    private Size size;
    @FXML
    private ComboBox newYorkTypeSelect;
    @FXML
    private ListView newYorkAvailableToppings, newYorkSelectedToppings;
    @FXML
    private ImageView newYorkImageView;
    @FXML
    private TextField newYorkCrust, newYorkPrice;
    @FXML
    private ToggleGroup newYorkSize;

    @FXML
    public void addToOrder(){
        String pizzaType = newYorkTypeSelect.getValue().toString();
        NYPizza factory = new NYPizza();
        Pizza pizza;
        if(pizzaType.equals("Deluxe")){
            pizza = factory.createDeluxe();
        }
        else if(pizzaType.equals("BBQ Chicken")){
            pizza = factory.createBBQChicken();
        }
        else if(pizzaType.equals("Meatzza")){
            pizza = factory.createMeatzza();
        }
        else{
            pizza = factory.createBuildYourOwn();
        }
        pizza.setSize(size);
        pizza.add(selectedToppings);
        Order currentOrder = StoreOrder.storeOrder.getCurrentOrder();
        currentOrder.add(pizza);
        Popup.showPopup("Added Pizza", "Pizza Successfully Added!", false);
        reset();
    }

    @FXML
    public void updateSize(){
        String toggleSelection = newYorkSize.selectedToggleProperty()
                .getValue().toString().split("'")[1];
        if(toggleSelection.equals("Small")){
            size = Size.SMALL;
        }
        else if(toggleSelection.equals("Medium")){
            size = Size.MEDIUM;
        }
        else{
            size = Size.LARGE;
        }
        updatePrice();
    }

    @FXML
    public void updateView(){
        String pizzaType = newYorkTypeSelect.getValue().toString();
        availableToppings = new ArrayList<>(Topping.getAvailableToppings());
        if(pizzaType.equals("Deluxe") || pizzaType.equals("BBQ Chicken") || pizzaType.equals("Meatzza")){
            newYorkAvailableToppings.setDisable(true);
            newYorkSelectedToppings.setDisable(true);
            if(pizzaType.equals("BBQ Chicken")){
                selectedToppings = BBQChicken.getBBQChickenToppings();
                availableToppings.removeAll(BBQChicken.getBBQChickenToppings());
                newYorkImageView.setImage(new Image("file:./src/main/resources/assets/ny_bbq_chicken.jpg"));
                newYorkCrust.setText(Crust.THIN.toString());
            }
            else if(pizzaType.equals("Deluxe")){
                selectedToppings = Deluxe.getDeluxeToppings();
                availableToppings.removeAll(Deluxe.getDeluxeToppings());
                newYorkImageView.setImage(new Image("file:./src/main/resources/assets/ny_deluxe.jpg"));
                newYorkCrust.setText(Crust.BROOKLYN.toString());
            }
            else if(pizzaType.equals("Meatzza")) {
                selectedToppings = Meatzza.getMeatzzaToppings();
                availableToppings.removeAll(Meatzza.getMeatzzaToppings());
                newYorkImageView.setImage(new Image("file:./src/main/resources/assets/ny_meatzza.jpg"));
                newYorkCrust.setText(Crust.HAND_TOSSED.toString());
            }
        }
        else{
            selectedToppings = new ArrayList<>();
            newYorkAvailableToppings.setDisable(false);
            newYorkSelectedToppings.setDisable(false);
            newYorkImageView.setImage(new Image("file:./src/main/resources/assets/ny_pizza.jpeg"));
            newYorkCrust.setText(Crust.HAND_TOSSED.toString());
        }
        updateToppingLists();
        updatePrice();
    }

    @FXML
    public void addTopping(){
        Topping topping = (Topping) newYorkAvailableToppings.getSelectionModel().getSelectedItem();
        if(topping != null && selectedToppings.size() < 7){
            availableToppings.remove(topping);
            selectedToppings.add(topping);
            newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }
        updatePrice();
    }

    @FXML
    public void removeTopping(){
        Topping topping = (Topping) newYorkSelectedToppings.getSelectionModel().getSelectedItem();
        if(topping != null){
            availableToppings.add(topping);
            selectedToppings.remove(topping);
            newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }
        updatePrice();
    }

    @FXML
    public void initialize(){
        String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
        newYorkTypeSelect.setItems(FXCollections.observableArrayList(pizzaTypes));
        reset();
    }

    private void reset(){
        newYorkTypeSelect.setValue("Build Your Own");
        availableToppings = new ArrayList<>(Topping.getAvailableToppings());
        selectedToppings = new ArrayList<>();
        size = Size.MEDIUM;
        updateToppingLists();
        updatePrice();
    }

    private void updateToppingLists(){
        newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
        newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
    }

    private void updatePrice(){
        String pizzaType = newYorkTypeSelect.getValue().toString();
        if(pizzaType.equals("BBQ Chicken")){
            price = BBQChicken.calculatePrice(size);
        }
        else if(pizzaType.equals("Deluxe")){
            price = Deluxe.calculatePrice(size);
        }
        else if(pizzaType.equals("Meatzza")){
            price = Meatzza.calculatePrice(size);
        }
        else{
            price = BuildYourOwn.calculatePrice(size, selectedToppings.size());
        }
        newYorkPrice.setText("$" + df.format(price));
    }
}
