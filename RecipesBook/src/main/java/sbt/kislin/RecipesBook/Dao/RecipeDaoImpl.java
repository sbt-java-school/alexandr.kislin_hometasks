package sbt.kislin.RecipesBook.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sbt.kislin.RecipesBook.model.Recipe;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository("RecipeDao")
public class RecipeDaoImpl implements RecipeDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RecipeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(recipe);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe recipe = session.load(Recipe.class, id);
        if (recipe != null){
            session.delete(recipe);
        }
    }

    @Override
    public Recipe getByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe recipe = session.load(Recipe.class, id);
        return recipe;
    }

    @Override
    public void update(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.update(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        Session session = sessionFactory.getCurrentSession();
        List recipesList = session.createQuery("from Recipe").list();
        return recipesList;
//          Реализация с критериа билдером
//        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> recipeRoot = query.from(Recipe.class);
//        return sessionFactory.getCurrentSession().createQuery(query.select(recipeRoot)).getResultList();
    }
}
