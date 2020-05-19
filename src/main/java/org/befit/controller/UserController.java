package org.befit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.befit.model.Recipe;

public class UserController {

  private HashMap<Date, List<Recipe>> userDiet;

  public Recipe addMeal(Recipe recipe, Date date) {
    if (userDiet.containsKey(date)) {
      List<Recipe> tempDiet = userDiet.get(date);
      tempDiet.add(recipe);
      userDiet.replace(date, tempDiet);
    } else {
      userDiet.put(date, new ArrayList<>(Collections.singleton(recipe)));
    }
    return recipe;
  }

  public Recipe removeMeal(Recipe recipe, Date date) {
    List<Recipe> tempDiet = userDiet.get(date);
    tempDiet.remove(recipe);
    userDiet.replace(date, tempDiet);
    return recipe;
  }

}
