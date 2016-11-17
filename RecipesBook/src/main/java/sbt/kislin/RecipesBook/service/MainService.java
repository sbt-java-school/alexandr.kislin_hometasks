package sbt.kislin.RecipesBook.service;

import sbt.kislin.RecipesBook.model.Ingredient;
import sbt.kislin.RecipesBook.model.Recipe;

import java.util.List;

public interface MainService {
    void addIngredient(Ingredient ingredient);

    void deleteIngredient(long id);

    void updateIngredient(Ingredient ingredient);

    Ingredient getIngredientByID(long id);

    List<Ingredient> getAllIngredients();

    void addRecipe(Recipe recipe);

    void deleteRecipe(long id);

    Recipe getRecipeByID(long id);

    void updateRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();
}
