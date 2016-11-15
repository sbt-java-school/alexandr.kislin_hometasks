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
}
