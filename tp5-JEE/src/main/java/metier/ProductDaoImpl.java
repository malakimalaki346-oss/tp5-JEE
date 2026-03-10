package metier;

import dao.IDao;
import entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements IDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product save(Product p) {
        sessionFactory.getCurrentSession().save(p);
        return p;
    }

    @Override
    public boolean create(Product o) {
        return false;
    }

    @Override
    public boolean delete(Product o) {
        return false;
    }

    @Override
    public boolean update(Product o) {
        return false;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Product.class)
                .list();
    }

    @Override
    public Product findById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void delete(Long id) {
        Product p = findById(id);
        if (p != null) {
            sessionFactory.getCurrentSession().delete(p);
        }
    }
}