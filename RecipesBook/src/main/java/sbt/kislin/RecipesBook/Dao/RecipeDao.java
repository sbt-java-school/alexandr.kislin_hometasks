package sbt.kislin.RecipesBook.Dao;

import sbt.kislin.RecipesBook.model.Recipe;

import java.util.List;

public interface RecipeDao {
    void add(Recipe recipe);

    void delete(long id);

    Recipe getByID(long id);

    void update(Recipe recipe);

    List<Recipe> getAllRecipes();
}
