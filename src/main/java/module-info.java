module org.befit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

  opens org.befit.controller to javafx.fxml;
  opens org.befit.model to com.fasterxml.jackson.databind;
    exports org.befit;
}