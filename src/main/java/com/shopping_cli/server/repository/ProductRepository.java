package com.shopping_cli.server.repository;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {
    List<Product> findByCategory(Category category);

//    List<Product> findAllSortedByName();
//
//    List<Product> findByKeyword(String keyword);

}
