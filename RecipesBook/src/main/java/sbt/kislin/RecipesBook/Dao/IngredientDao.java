package sbt.kislin.RecipesBook.Dao;

import sbt.kislin.RecipesBook.model.Ingredient;

import java.util.List;

public interface IngredientDao {
    void add(Ingredient ingredient);

    void delete(long id);

    void update(Ingredient ingredient);

    Ingredient getByID(long id);

    List<Ingredient> getAllIngredients();
}
