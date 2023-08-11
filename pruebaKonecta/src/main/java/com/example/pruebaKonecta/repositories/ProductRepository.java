package com.example.pruebaKonecta.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebaKonecta.models.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long>{
    @Query("SELECT p FROM ProductModel p WHERE p.productStock = (SELECT MAX(p2.productStock) FROM ProductModel p2)")
    public ProductModel selectMoreStockProduct();
}
