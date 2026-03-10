package dao;

import entities.Product;

import java.util.List;

public interface IDao<T> {
    Product save(Product p);

    boolean create(T o);
    boolean delete(T o);
    boolean update(T o);
    T findById(int id);
    List<T> findAll();

    Product findById(Long id);

    void delete(Long id);
}