package sbt.kislin.RecipesBook;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import sbt.kislin.RecipesBook.Dao.IngredientDao;
import sbt.kislin.RecipesBook.Dao.IngredientDaoImpl;
import sbt.kislin.RecipesBook.Dao.RecipeDao;
import sbt.kislin.RecipesBook.Dao.RecipeDaoImpl;
import sbt.kislin.RecipesBook.service.MainService;
import sbt.kislin.RecipesBook.service.MainServiceImpl;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("sbt.kislin.RecipesBook")
public class WebConfiguration {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:D:/sbt_training/HomeWorks/RecipesBook/database/app");
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("sbt.kislin.RecipesBook");
        final Properties property = new Properties();
        property.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        property.setProperty("hibernate.show_sql", "true");
        property.setProperty("hibernate.hbm2ddl", "validate");
        factoryBean.setHibernateProperties(property);
        return factoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager
            (DataSource dataSource, SessionFactory sessionFactory) {
        final HibernateTransactionManager tr =
                new HibernateTransactionManager();
        tr.setDataSource(dataSource);
        tr.setSessionFactory(sessionFactory);
        return tr;
    }
    @Bean
    public RecipeDao recipeDao(){
        return new RecipeDaoImpl(sessionFactory(dataSource())); // мне не нравится такая змейка вызовов, может можно иначе?
    }
    @Bean
    public IngredientDao ingredientDao(){
        return new IngredientDaoImpl(sessionFactory(dataSource())); // аналогично
    }

    @Bean
    public MainService mainService(){
         return new MainServiceImpl(); // а здесь нету, потому как внутри импла просто сеттеры с autowired
    }
}
