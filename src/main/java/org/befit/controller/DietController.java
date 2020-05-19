package org.befit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.befit.App;
import org.befit.model.Recipe;

public class DietController implements Initializable {

  @FXML
  ListView<Recipe> recipesList;

  @FXML
  GridPane calendarGrid;

//  private final UserController userController;
//
//  public DietController(UserController userController) {
//    this.userController = userController;
//  }

  @FXML
  private void switchToMenuStart() throws IOException {
    App.setRoot("menuStart");
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    for (int i=0; i<calendarGrid.getColumnCount(); i++) {
      for (int y=1; y<calendarGrid.getRowCount(); y++) {
        Pane pane = new Pane();
        if ((i%2 == 0 && y%2 != 0) || (i%2 != 0 && y%2 == 0)) {
          pane.setStyle("-fx-background-color: aliceblue");
          calendarGrid.add(pane, i, y);
        } else {
          pane.setStyle("-fx-background-color: aquamarine");
          calendarGrid.add(pane, i, y);
        }
      }
    }
  }
}
