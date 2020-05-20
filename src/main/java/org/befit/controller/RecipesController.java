package org.befit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.befit.App;
import org.befit.model.Difficulty;
import org.befit.model.Ingredient;
import org.befit.model.Recipe;

public class RecipesController implements Initializable {

  @FXML
  ListView<Recipe> recipesList;

  @FXML
  TextArea details;

  @FXML
  TextArea ingredientsList;

  private final ObservableList<Recipe> recipesObservableList;

  public RecipesController() {
    recipesObservableList = FXCollections.observableArrayList(testRecipes());
  }

  @FXML
  private void switchToMenuStart() throws IOException {
    App.setRoot("menuStart");
  }

  public ArrayList<Recipe> testRecipes() { // just test recipes to see how it looks in view
    return new ArrayList<>(Arrays.asList(
        new Recipe(1L,"Name1",Difficulty.EASY, Arrays.asList(
              new Ingredient("Cabbage", 10, "Pieces"),
              new Ingredient( "Potato", 5, "Kg")),
            Arrays.asList("1", "2")),
        new Recipe(2L,"Name2",Difficulty.MEDIUM, Arrays.asList(
              new Ingredient("Fish", 12, "Pieces"),
              new Ingredient("Chips", 3, "Kg")),
            Arrays.asList("3", "4"))
    ));
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Label placeholderLabel = new Label("Recipes MIA");
    recipesList.setItems(recipesObservableList);
    recipesList.setPlaceholder(placeholderLabel);
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
    ingredientsList.setEditable(false);
    details.setEditable(false);
  }

  @FXML
  private void selectRecipe() {
    Recipe selectedRecipe = recipesList.getSelectionModel().getSelectedItem();
    formatDetails(selectedRecipe);
    formatIngredients(selectedRecipe.getIngredients());
  }

  private void formatDetails(Recipe recipe) {
    details.setText("Difficulty: " + recipe.getDifficulty()
        + "\n\nInstructions:\n" + String.join("\n", recipe.getInstructions())
    );
  }

  private void formatIngredients(List<Ingredient> ingredients) {
    StringBuilder text = new StringBuilder();
    ingredients.forEach(
        ingredient -> text.append(
            String.format("%s %" + (22 - ingredient.getName().length())
                + "d %5s\n", ingredient.getName(),
                ingredient.getQuantity(), ingredient.getUnit())
    ));
    ingredientsList.setText(
        text.toString()
    );
  }
}
