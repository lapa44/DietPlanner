package org.befit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.befit.App;
import org.befit.model.Difficulty;
import org.befit.model.Ingredient;
import org.befit.model.Recipe;

public class DietController implements Initializable {

  @FXML
  ListView<Recipe> recipesList;

  @FXML
  GridPane calendarGrid;

  private final UserController userController;
  private final ObservableList<Recipe> recipesObservableList;

  public DietController() {
    this.userController = new UserController();
    recipesObservableList = FXCollections.observableArrayList(testRecipes());
  }

  @FXML
  private void switchToMenuStart() throws IOException {
    App.setRoot("menuStart");
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    configPanes();
    configRecipesList();
  }

  private void configPanes() {
    for (int i=0; i<calendarGrid.getColumnCount(); i++) {
      for (int y=1; y<calendarGrid.getRowCount(); y++) {
        Pane pane = new Pane();
        configSinglePane(pane);
        if ((i%2 == 0 && y%2 != 0) || (i%2 != 0 && y%2 == 0)) {
          pane.setStyle("-fx-background-color: aliceblue");
        } else {
          pane.setStyle("-fx-background-color: aquamarine");
        }
        calendarGrid.add(pane, i, y);
      }
      calendarGrid.gridLinesVisibleProperty();
    }
  }

  private Pane configSinglePane(Pane pane) {
    pane.setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.getButton() == MouseButton.PRIMARY) {
        if (!recipesList.getSelectionModel().isEmpty()) {
          pane.getChildren().add(new Label(recipesList.getSelectionModel().getSelectedItem().getName()));
          // userController add selected
        }
      } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
        if (!pane.getChildren().isEmpty()) {
          pane.getChildren().clear();
          // userController delete selected
        }
      }
    });
    return pane;
  }

  private void configRecipesList() {
    recipesList.setItems(recipesObservableList);
    recipesList.setCellFactory(recipeListView -> new ListCell<>() {
      @Override
      public void updateItem(Recipe recipe, boolean empty) {
        super.updateItem(recipe, empty);
        if (empty) {
          setText(null);
        } else {
          setText(recipe.getName());
        }
      }
    });
  }


  public ArrayList<Recipe> testRecipes() { // just test recipes to see how it looks in view
    return new ArrayList<>(Arrays.asList(
        new Recipe(1L,"Name1", Difficulty.EASY, Arrays.asList(
            new Ingredient("Cabbage", 10, "Pieces"),
            new Ingredient( "Potato", 5, "Kg")),
            Arrays.asList("1", "2")),
        new Recipe(2L,"Name2",Difficulty.MEDIUM, Arrays.asList(
            new Ingredient("Fish", 12, "Pieces"),
            new Ingredient("Chips", 3, "Kg")),
            Arrays.asList("3", "4"))
    ));
  }
}
