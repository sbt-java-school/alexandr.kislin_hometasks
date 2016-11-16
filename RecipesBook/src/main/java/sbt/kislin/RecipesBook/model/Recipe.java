package sbt.kislin.RecipesBook.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelk on 15.11.2016.
 */
@Entity
public class Recipe implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String recipeName;
    private String author;
    @ManyToMany
    @OrderBy("id ASC") // не факт что верно, проверить внимательнее
    private List<Ingredient> ingredients = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
