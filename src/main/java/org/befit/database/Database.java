package org.befit.database;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.befit.model.Recipe;

public interface Database {

  Recipe getRecipeById(Long id) throws IOException;

  List<Recipe> getRecipes() throws IOException;

  Recipe removeRecipeById(Long id) throws IOException;

  Recipe saveRecipe(Recipe recipe) throws IOException;

  HashMap<Date, List<Recipe>> getUserDiet();

  HashMap<Date, List<Recipe>> removeUserDiet();

  HashMap<Date, List<Recipe>> saveUserDiet();

}
