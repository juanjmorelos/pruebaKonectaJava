package com.example.pruebaKonecta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebaKonecta.models.SellProductModel;
import com.example.pruebaKonecta.repositories.SellRepository;

@Service
public class SellService {
    @Autowired
    SellRepository repository;

    public SellProductModel sellProducts(SellProductModel model) {
        return repository.save(model);
    }

    public int getMoreProductSelt() {
        return repository.selectMoreSeltProduct();
    }
}
