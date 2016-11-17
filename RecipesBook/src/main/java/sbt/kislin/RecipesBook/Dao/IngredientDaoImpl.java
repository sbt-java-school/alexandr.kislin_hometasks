package sbt.kislin.RecipesBook.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sbt.kislin.RecipesBook.model.Ingredient;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository("IngredientDao")
public class IngredientDaoImpl implements IngredientDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public IngredientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ingredient);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Ingredient ingredient = session.load(Ingredient.class, id); // здесь был боксинг в new Long(id);

        if (ingredient != null){
            session.delete(ingredient);
        }


    }

    @Override
    public void update(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ingredient);

    }

    @Override
    public Ingredient getByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Ingredient ingredient = session.load(Ingredient.class, id);
        return ingredient;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Ingredient> query = criteriaBuilder.createQuery(Ingredient.class);
        Root<Ingredient> ingredientRoot = query.from(Ingredient.class);
        return sessionFactory.getCurrentSession().createQuery(query.select(ingredientRoot)).getResultList();

        //реализация без критерия билдера
//        Session session = sessionFactory.getCurrentSession();
//        List ingredientList = session.createQuery("from Ingredient").list();
//        return  ingredientList;
    }
}
