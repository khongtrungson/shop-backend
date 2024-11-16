package com.khongtrungson.shopapp.criteria;

import com.khongtrungson.shopapp.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CiteriaQuery {
    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        Parameter<Double> pr1 = cb.parameter(Double.class, "name");
        Predicate condition1 = cb.equal(root.get("name"), pr1);
        Predicate pr = cb.and(condition1);
    }

}
