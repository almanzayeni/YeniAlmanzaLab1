package edu.westga.cs1302.foodpantry.view;

import edu.westga.cs1302.foodpantry.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * The codebehind class for the food pantry
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class MainWindow {

    @FXML
    private TextField foodNameField;

    @FXML
    private ComboBox<String> foodTypeComboBox;

    @FXML
    private ListView<Food> pantryListView;
    
    private ObservableList<Food> pantryItems;
    
    @FXML
    void initialize() {
        assert this.foodNameField != null : "fx:id=\"foodNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.foodTypeComboBox != null : "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryListView != null : "fx:id=\"pantryListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        
        this.foodTypeComboBox.setItems(FXCollections.observableArrayList(
        		"Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));
        this.pantryItems = FXCollections.observableArrayList();
        this.pantryListView.setItems(this.pantryItems);

    }
    
    @FXML
    void addFood(ActionEvent event) {
    	String foodName = this.foodNameField.getText();
    	String foodType = this.foodTypeComboBox.getValue();
    	
        if (foodName == null || foodName.trim().isEmpty()) {
            this.showAlert("Invalid Input", "Please enter a valid food name.");
            return;
        }

        if (foodType == null) {
            this.showAlert("Invalid Input", "Please select a food type.");
            return;
        }

        Food newFood = new Food(foodName, foodType);

        this.pantryItems.add(newFood);

        this.foodNameField.clear();
        this.foodTypeComboBox.setValue(null);
    }
   
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

