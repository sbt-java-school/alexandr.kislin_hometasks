package sbt.kislin.RecipesBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sbt.kislin.RecipesBook.model.Recipe;
import sbt.kislin.RecipesBook.service.MainService;

@Controller
public class MainController {
    private MainService mainService;

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String listRecipes(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("listRecipes", this.mainService.getAllRecipes());

        return "main";
    }

    @RequestMapping(value = "/main/addRecipe", method = RequestMethod.POST)
    public String addRecipe(@ModelAttribute("recipe") Recipe recipe) {
        if (recipe.getId() == 0) {
            this.mainService.addRecipe(recipe);
        } else {
            this.mainService.updateRecipe(recipe);
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/main/deleteRecipe")
    public String deleteRecipe(@PathVariable("id") long id) {
        this.mainService.deleteRecipe(id);

        return "redirect:/main";
    }

    @RequestMapping(value = "editRecipe/{id}")
    public String editRecipe(@PathVariable("id") long id, Model model) {
        model.addAttribute("recipe", this.mainService.getRecipeByID(id));
        model.addAttribute("listRecipes", this.mainService.getAllRecipes());

        return "main";
    }

    @RequestMapping(value = "recipeInfo/{id}")
    public String recipeInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("recipe", this.mainService.getRecipeByID(id));

        return "recipeInfo";
    }


}
