package org.befit.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import org.befit.model.Difficulty;
import org.befit.model.Ingredient;
import org.befit.model.Recipe;
import org.junit.jupiter.api.Test;

class InFileDatabaseTest {

  private final String TEST_DIR_PATH = "src/test/java/org/befit/database/";

  @Test
  public void shouldSaveRecipe() throws IOException {
    InFileDatabase inFileDatabase = new InFileDatabase(TEST_DIR_PATH);

    Recipe recipeToSave = Recipe.builder()
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
        .setInstructions(Arrays.asList("Step1", "Step2")).build();

    inFileDatabase.saveRecipe(recipeToSave);

    assertEquals(recipeToSave, inFileDatabase.getRecipeById(recipeToSave.getId()));
  }

}