package com.example.pruebaKonecta.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebaKonecta.models.ProductModel;
import com.example.pruebaKonecta.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductModel> getAllProducts() {
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public Optional<ProductModel> getProductsById(Long id) {
        return (Optional<ProductModel>) productRepository.findById(id);
    }

    public ProductModel insertProducts(ProductModel product) {
        return productRepository.save(product);
    }

    public void deleteProducts(Long id) {
        productRepository.deleteById(id);
    }

    public ProductModel getMoreStock(){
        return productRepository.selectMoreStockProduct();
    }
    
}
