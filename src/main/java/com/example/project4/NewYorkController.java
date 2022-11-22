package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The NewYorkController class is the controller for the ny-view.fxml file.
 * This class initializes UI components and handles user interaction events.
 * @author Serena Zeng, Jackson Lee
 */
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

    /**
     * Create a NY pizza object with the user-selected
     * modifications and add to current order.
     */
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

    /**
     * Called when new Size radio button clicked.
     * Update prices based on pizza type and newly selected size.
     */
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

    /**
     * Update view (price, toppings, image) when a new pizza type is selected.
     */
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

    /**
     * Called when the button to add topping is pressed and the Build Your Own pizza type is selected.
     * Price is recalculated and topping lists are updated if topping successfully added.
     * Displays error if user attempts to add more than 7 toppings.
     */
    @FXML
    public void addTopping(){
        Topping topping = (Topping) newYorkAvailableToppings.getSelectionModel().getSelectedItem();
        if (topping == null){
            Popup.showPopup("Build Your Own", "No Topping Selected", true);
        }
        else if (selectedToppings.size() >= Pizza.MAX_TOPPINGS){
            Popup.showPopup("Build Your Own", "Maximum Number of Toppings (7) Exceeded", true);
        }
        else {
            availableToppings.remove(topping);
            selectedToppings.add(topping);
            newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }

        updatePrice();
    }

    /**
     * Called when the button to remove selected topping is pressed and the Build Your Own pizza type is selected.
     * Price is recalculated and topping lists are updated if topping successfully removed.
     */
    @FXML
    public void removeTopping(){
        Topping topping = (Topping) newYorkSelectedToppings.getSelectionModel().getSelectedItem();
        if (topping == null){
            Popup.showPopup("Build Your Own", "No Topping Selected", true);
        }
        else {
            availableToppings.add(topping);
            selectedToppings.remove(topping);
            newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
            newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
        }
        updatePrice();
    }

    /**
     * Initialize UI components
     */
    @FXML
    public void initialize(){
        String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
        newYorkTypeSelect.setItems(FXCollections.observableArrayList(pizzaTypes));
        reset();
    }

    /**
     * Reset view. Called after pizza is successfully ordered.
     */
    private void reset(){
        newYorkTypeSelect.setValue("Build Your Own");
        availableToppings = new ArrayList<>(Topping.getAvailableToppings());
        selectedToppings = new ArrayList<>();
        size = Size.MEDIUM;
        updateToppingLists();
        updatePrice();
    }

    /**
     * Update ListView UI component containing available toppings and list of selected toppings
     */
    private void updateToppingLists(){
        newYorkAvailableToppings.setItems(FXCollections.observableArrayList(availableToppings));
        newYorkSelectedToppings.setItems(FXCollections.observableArrayList(selectedToppings));
    }

    /**
     * Show price on TextField when pizza object is updated.
     */
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
