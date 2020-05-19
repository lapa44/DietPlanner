package org.befit.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import org.befit.App;

public class MenuStartController {

  @FXML
  private void switchToDiet() throws IOException {
      App.setRoot("diet");
  }

  @FXML
  private void switchToRecipes() throws IOException {
      App.setRoot("recipes");
  }

  @FXML
  private void switchToMyRecipes() {

  }

  @FXML
  private void generateShoppingList() {

  }

  @FXML
  private void openGithub() throws URISyntaxException, IOException {
    URI url = new URI("https://github.com/lapa44");
    java.awt.Desktop.getDesktop().browse(url);
  }

  @FXML
  private void quit() {
    Platform.exit();
  }

}
