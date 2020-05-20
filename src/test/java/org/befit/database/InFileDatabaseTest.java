package org.befit.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import org.befit.model.Difficulty;
import org.befit.model.Ingredient;
import org.befit.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InFileDatabaseTest {

  private final String TEST_DIR_PATH = "src/test/java/org/befit/database/";

  @BeforeEach
  void clearDatabase() throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(TEST_DIR_PATH + "RecipesDB.db")) {
      writer.print("");
    }
  }

  @Test
  public void shouldSaveRecipe() throws IOException {
    InFileDatabase inFileDatabase = new InFileDatabase(TEST_DIR_PATH);

    Recipe recipeToSave = getTestRecipe().build();

    inFileDatabase.saveRecipe(recipeToSave);
    assertNotNull(recipeToSave);
    assertEquals(recipeToSave, inFileDatabase.getRecipeById(recipeToSave.getId()));
    assertEquals(1, inFileDatabase.getRecipes().size());
  }

  @Test
  public void shouldRemoveRecipe() throws IOException {
    InFileDatabase inFileDatabase = new InFileDatabase(TEST_DIR_PATH);

    Recipe savedRecipe = inFileDatabase.saveRecipe(getTestRecipe().build());

    assertEquals(1, inFileDatabase.getRecipes().size());
    assertEquals(savedRecipe, inFileDatabase.getRecipeById(savedRecipe.getId()));

    inFileDatabase.removeRecipeById(savedRecipe.getId());
    assertEquals(0, inFileDatabase.getRecipes().size());
    assertFalse(inFileDatabase.getRecipes().contains(savedRecipe));
  }

  @Test
  public void shouldThrowExceptionWhileGettingRecipeById() throws IOException {
    InFileDatabase inFileDatabase = new InFileDatabase(TEST_DIR_PATH);

    assertThrows(NoSuchElementException.class, () ->
        inFileDatabase.getRecipeById(new Random().nextLong()));
  }


  private Recipe.Builder getTestRecipe() {
    return Recipe.builder()
        .setId(1L)
        .setName("Some name")
        .setDifficulty(Difficulty.EASY)
        .setIngredients(Arrays.asList(
            Ingredient.builder()
                .setName("Cabbage")
                .setQuantity(10)
                .setUnit("Pieces").build(),
            Ingredient.builder()
                .setName("Potato")
                .setQuantity(5)
                .setUnit("Kg").build()
        ))
        .setInstructions(Arrays.asList("Step1", "Step2"));
  }
}