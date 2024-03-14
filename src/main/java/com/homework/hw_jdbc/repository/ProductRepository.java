package com.homework.hw_jdbc.repository;

import com.homework.hw_jdbc.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private String select = "select Customer_order.product_name\n" +
            "from Customer join Customer_order on Customer.id = Customer_order.customer_id\n" +
            "where LOWER(Customer.name) = :name";

    @PersistenceContext
    private EntityManager entityManager;

    public String getProductName(String name){
        System.out.println(select);
        String result = entityManager.createNativeQuery(select)
                .setParameter("name", name)
                .getSingleResult().toString();
        return result;
    }
}
