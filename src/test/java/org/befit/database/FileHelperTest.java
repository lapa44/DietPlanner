package org.befit.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileHelperTest {

  private final String TEST_DIR_PATH = "src/test/java/org/befit/database/";

  @BeforeEach
  public void clearFile() throws IOException {
    if (Files.exists(Paths.get(TEST_DIR_PATH + "Recipes.db"))) {
      Files.delete(Paths.get(TEST_DIR_PATH + "RecipesDB.db"));
    }
  }

  @Test
  public void shouldAddLineToFileAndReadIt() throws IOException {
    FileHelper fileHelper = new FileHelper(TEST_DIR_PATH);

    assertTrue(fileHelper.readLinesFromFile("RecipesDB.db").isEmpty());

    fileHelper.writeLineToFile("Some Line", "RecipesDB.db");

    assertTrue(fileHelper.readLinesFromFile("RecipesDB.db").contains("Some Line"));
    assertEquals(1, fileHelper.readLinesFromFile("RecipesDB.db").size());
  }

  @Test
  public void shouldRemoveLineFromFile() throws IOException {
    FileHelper fileHelper = new FileHelper(TEST_DIR_PATH);

    fileHelper.writeLineToFile("Some line", "RecipesDB.db");

    assertEquals(1, fileHelper.readLinesFromFile("RecipesDB.db").size());
    fileHelper.deleteLineFromFile("Some line", "RecipesDB.db");
    assertTrue(fileHelper.readLinesFromFile("RecipesDB.db").isEmpty());
    assertEquals(0, fileHelper.readLinesFromFile("RecipesDB.db").size());
  }

}