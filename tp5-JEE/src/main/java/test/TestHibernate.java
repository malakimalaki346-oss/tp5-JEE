package test;

import util.HibernateConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHibernate {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        System.out.println("Configuration Spring et Hibernate chargée avec succès !");
    }
}