package util;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"dao","metier","entities","util"})
@PropertySource("classpath:application.properties")

public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(env.getProperty("jdbc.driver"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));

        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setDataSource(dataSource());
        factory.setPackagesToScan("entities"); // uniquement les entités
        factory.setHibernateProperties(hibernateProperties());

        return factory;
    }

    private Properties hibernateProperties() {

        Properties prop = new Properties();

        prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        return prop;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory);

        return tx;
    }
}