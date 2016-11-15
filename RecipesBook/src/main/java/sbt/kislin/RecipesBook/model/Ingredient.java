package sbt.kislin.RecipesBook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String label;
    @Column(nullable = false)
    private String units;
}
