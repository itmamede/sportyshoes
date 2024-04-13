package com.company.sporty.repository;

import com.company.sporty.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p.name, p.category, p.price, o.id, o.ldt, o.username " +
            "from Product p, Orders o " +
            "where p.id = o.pid")
    public List<Object[]> getOrderDetails();

    @Query("select p.name, p.category, p.price, o.id, o.ldt, o.username " +
            "from Product p, Orders o " +
            "where p.id = o.pid and o.username = :username")
    public List<Object[]> getUserOrderDetails(@Param("username") String username);
}
