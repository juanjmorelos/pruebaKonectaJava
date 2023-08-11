package com.example.pruebaKonecta.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebaKonecta.models.SellProductModel;

@Repository
public interface SellRepository extends CrudRepository<SellProductModel, Long> {   
    @Query(value = "SELECT * FROM producto p JOIN (SELECT sp.producto, SUM(sp.cantidad) AS total_vendido FROM venta sp GROUP BY sp.producto ORDER BY total_vendido DESC) AS subquery WHERE p.idProducto = subquery.producto ORDER BY subquery.total_vendido desc LIMIT 1", 
    nativeQuery = true )
    public int selectMoreSeltProduct();
}

