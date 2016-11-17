package sbt.kislin.RecipesBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sbt.kislin.RecipesBook.Dao.IngredientDao;
import sbt.kislin.RecipesBook.Dao.RecipeDao;
import sbt.kislin.RecipesBook.model.Ingredient;
import sbt.kislin.RecipesBook.model.Recipe;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {
        ingredientDao.add(ingredient);
    }

    @Override
    @Transactional
    public void deleteIngredient(long id) {
        ingredientDao.delete(id);
    }

    @Override
    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        ingredientDao.update(ingredient);
    }

    @Override
    @Transactional
    public Ingredient getIngredientByID(long id) {
        return ingredientDao.getByID(id);
    }

    @Override
    @Transactional
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }

    @Override
    @Transactional
    public void addRecipe(Recipe recipe) {
        recipeDao.add(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(long id) {
        recipeDao.delete(id);
    }

    @Override
    @Transactional
    public Recipe getRecipeByID(long id) {
        return recipeDao.getByID(id);
    }

    @Override
    @Transactional
    public void updateRecipe(Recipe recipe) {
        recipeDao.update(recipe);
    }

    @Override
    @Transactional
    public List<Recipe> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }
}
