package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChicagoController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ArrayList<Topping> availableToppings;
    private ArrayList<Topping> selectedToppings;
    private double price;
    private Size size;
    @FXML
    private ComboBox chicagoTypeSelect;
    @FXML
    private ListView chicagoAvailableToppings, chicagoSelectedToppings;
    @FXML
    private ImageView chicagoImageView;
    @FXML
    private TextField chicagoCrust, chicagoPrice;
    @FXML
    private ToggleGroup chicagoSize;

    @FXML
    public void addToOrder(){
        String pizzaType = chicagoTypeSelect.getValue().toString();
        ChicagoPizza factory = new ChicagoPizza();
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
        Order currentOrder = StoreOrder.getCurrentOrder();
        currentOrder.add(pizza);
        reset();
    }

    @FXML
    public void updateSize(){
        String toggleSelection = chicagoSize.selectedToggleProperty()
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
        String pizzaType = chicagoTypeSelect.getValue().toString();
        availableToppings = new ArrayList<>(Topping.getAvailableToppings());
        if(pizzaType.equals("Deluxe") || pizzaType.equals("BBQ Chicken") || pizzaType.equals("Meatzza")){
            chicagoAvailableToppings.setDisable(true);
            chicagoSelectedToppings.setDisable(true);
            if(pizzaType.equals("BBQ Chicken")){
                selectedToppings = BBQChicken.getBBQChickenToppings();
                availableToppings.removeAll(BBQChicken.getBBQChickenToppings());
                chicagoImageView.setImage(new Image("file:./src/main/resources/assets/chicago_bbq_chicken.jpg"));
                chicagoCrust.setText(Crust.PAN.toString());
            }
            else if(pizzaType.equals("Deluxe")){
                selectedToppings = Deluxe.getDeluxeToppings();
                availableToppings.removeAll(Deluxe.getDeluxeToppings());
                chicagoImageView.setImage(new Image("file:./src/main/resources/assets/chicago_deluxe.jpeg"));
                chicagoCrust.setText(Crust.DEEP_DISH.toString());
            }
            else if(pizzaType.equals("Meatzza")) {
                selectedToppings = Meatzza.getMeatzzaToppings();
                availableToppings.removeAll(Meatzza.getMeatzzaToppings());
                chicagoImageView.setImage(new Image("file:./src/main/resources/assets/chicago_meatzza.jpeg"));
                chicagoCrust.setText(Crust.STUFFED.toString());
            }
        }
        else{
            selectedToppings = new ArrayList<>();
            chicagoAvailableToppings.setDisable(false);
            chicagoSelectedToppings.setDisable(false);
            chicagoImageView.setImage(new Image("file:./src/main/resources/assets/chicago_pizza.jpeg"));
            chicagoCrust.setText(Crust.PAN.toString());
        }
        updateToppingLists();
        updatePrice();
    }

    @FXML
    public void addTopping(){
        Topping topping = (Topping) chicagoAvailableToppings.getSelectionModel().getSelectedItem();
        if(topping != null && selectedToppings.size() < 7){
            availableToppings.remove(topping);
            selectedToppings.add(topping);
            chicagoAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            chicagoSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }
        updatePrice();
    }

    @FXML
    public void removeTopping(){
        Topping topping = (Topping) chicagoSelectedToppings.getSelectionModel().getSelectedItem();
        if(topping != null){
            availableToppings.add(topping);
            selectedToppings.remove(topping);
            chicagoAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            chicagoSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }
        updatePrice();
    }

    @FXML
    public void initialize(){
        String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
        chicagoTypeSelect.setItems(FXCollections.observableArrayList(pizzaTypes));
        reset();
    }

    private void reset(){
        chicagoTypeSelect.setValue("Build Your Own");
        availableToppings = new ArrayList<>(Topping.getAvailableToppings());
        selectedToppings = new ArrayList<>();
        size = Size.MEDIUM;
        updateToppingLists();
        updatePrice();
    }

    private void updateToppingLists(){
        chicagoAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
        chicagoSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
    }

    private void updatePrice(){
        String pizzaType = chicagoTypeSelect.getValue().toString();
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
        chicagoPrice.setText("$" + df.format(price));
    }
}
