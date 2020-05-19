package org.befit.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {

  private final String name;
  private final Integer quantity;
  private final String unit;

  @JsonCreator
  public Ingredient(@JsonProperty("name") String name,
                    @JsonProperty("quantity") Integer quantity,
                    @JsonProperty("unit") String unit) {
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
  }

  public String getName() {
    return name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getUnit() {
    return unit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient that = (Ingredient) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(quantity, that.quantity) &&
        Objects.equals(unit, that.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, unit);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String name;
    private Integer quantity;
    private String unit;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setQuantity(Integer quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder setUnit(String unit) {
      this.unit = unit;
      return this;
    }

    public Ingredient build() {
      return new Ingredient(name, quantity, unit);
    }

  }
}
