package org.befit.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.befit.model.Recipe;

public class InFileDatabase implements Database {

  private final FileHelper fileHelper;
  private final ObjectMapper objectMapper;

  public InFileDatabase(String path) throws IOException {
    this.fileHelper = new FileHelper(path);
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Recipe getRecipeById(Long id) throws IOException {
    List<Recipe> recipes = getRecipes();
    for (Recipe recipe : recipes) {
      if (recipe.getId().equals(id)) {
        return recipe;
      }
    }
    throw new NoSuchElementException("Recipe of given ID was not found in database.");
  }

  @Override
  public List<Recipe> getRecipes() throws IOException {
    List<String> recipesFromFile = fileHelper.readLinesFromFile("RecipesDB.db");
    List<Recipe> recipes = new ArrayList<>();
    for (String line : recipesFromFile) {
      recipes.add(objectMapper.readValue(line, Recipe.class));
    }
    return recipes;
  }

  @Override
  public Recipe removeRecipeById(Long id) throws IOException {
    Recipe recipeToRemove = getRecipeById(id);
    fileHelper.deleteLineFromFile(objectMapper.writeValueAsString(recipeToRemove),
        "RecipesDB.db");
    return recipeToRemove;
  }

  @Override
  public Recipe saveRecipe(Recipe recipe) throws IOException {
    fileHelper.writeLineToFile(objectMapper.writeValueAsString(recipe), "RecipesDB.db");
    return recipe;
  }

  @Override
  public HashMap<Date, List<Recipe>> getUserDiet() {
    return null;
  }

  @Override
  public HashMap<Date, List<Recipe>> removeUserDiet() {
    return null;
  }

  @Override
  public HashMap<Date, List<Recipe>> saveUserDiet() {
    return null;
  }
}
