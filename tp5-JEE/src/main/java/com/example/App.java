package com.example;

import dao.IDao;
import entities.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        IDao<Product> dao = (IDao<Product>) context.getBean("productDaoImpl");

        dao.save(new Product("PC", 8000));
        dao.save(new Product("Souris", 200));

        dao.findAll().forEach(p -> {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
        });

    }
}