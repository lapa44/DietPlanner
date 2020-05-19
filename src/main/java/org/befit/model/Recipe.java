package org.befit.model;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe {

  private final Long id;
  private final String name;
  private final Difficulty difficulty;
  private final List<Ingredient> ingredients;
  private final List<String> instructions;

  @JsonCreator
  public Recipe(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("Difficulty") Difficulty difficulty,
                @JsonProperty("ingredients") List<Ingredient> ingredients,
                @JsonProperty("instructions") List<String> instructions) {
    this.id = id;
    this.name = name;
    this.difficulty = difficulty;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public List<String> getInstructions() {
    return instructions;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return Objects.equals(id, recipe.id) &&
        Objects.equals(name, recipe.name) &&
        difficulty == recipe.difficulty &&
        Objects.equals(ingredients, recipe.ingredients) &&
        Objects.equals(instructions, recipe.instructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, difficulty, ingredients, instructions);
  }

  public static class Builder {

    private Long id;
    private String name;
    private Difficulty difficulty;
    private List<Ingredient> ingredients;
    private List<String> instructions;

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setDifficulty(Difficulty difficulty) {
      this.difficulty = difficulty;
      return this;
    }

    public Builder setIngredients(List<Ingredient> ingredients) {
      this.ingredients = ingredients;
      return this;
    }

    public Builder setInstructions(List<String> instructions) {
      this.instructions = instructions;
      return this;
    }

    public Recipe build() {
      return new Recipe(id, name, difficulty, ingredients, instructions);
    }
  }
}
