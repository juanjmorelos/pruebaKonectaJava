package com.example.pruebaKonecta.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebaKonecta.models.ProductModel;
import com.example.pruebaKonecta.models.SellProductModel;
import com.example.pruebaKonecta.services.ProductService;
import com.example.pruebaKonecta.services.SellService;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class ProductSellController {
    @Autowired
    SellService service;
    @Autowired
    ProductService productService;

    @PostMapping(path = "/sellProduct")
    public ResponseEntity<Map<String, Object>> sellProduct(@RequestBody SellProductModel model) {
        Map<String, Object> response = new HashMap<>();

        if(!isValidateRequest(model)) {
            response.put("result", "fail");
            response.put("cause", "Campos incompletos");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<ProductModel>product = productService.getProductsById(model.getProductId());
        
        if(product.isPresent()) {
            ProductModel request = product.get();

            if(request.getProductStock() == 0) {
                response.put("result", "fail");
                response.put("msg", "El producto no tiene stock");
                return ResponseEntity.badRequest().body(response);
            }

            if(model.getAmount() > request.getProductStock()) {
                response.put("result", "fail");
                response.put("msg", "El producto no tiene stock suficiente");
                return ResponseEntity.badRequest().body(response);
            }

            ProductModel insertedProduct = productService.insertProducts(productToInsert(product.get(), request, model.getAmount()));
            SellProductModel insertedSell = service.sellProducts(model);
            response.put("result", "OK");
            response.put("ventaId", insertedSell.getIdVenta());
            response.put("nuevoStock", insertedProduct.getProductStock());
            return ResponseEntity.ok(response);
        } else {
            response.put("Result", "Fail");
            response.put("msg", "El producto que desea vender no existe");
            return ResponseEntity.badRequest().body(response);
        }

    }

    private ProductModel productToInsert(ProductModel productsToSave, ProductModel request, int StockToRest) {
        ProductModel product = productsToSave;
        int stock = product.getProductStock() - StockToRest;

        product.setProductName(request.getProductName());
        product.setProductReference(request.getProductReference());
        product.setProductPrice(request.getProductPrice());
        product.setProductWeight(request.getProductWeight());
        product.setProductCategory(request.getProductCategory());
        product.setProductStock(stock);
        return product;
    }

    private boolean isValidateRequest(SellProductModel request) {
        if(request.getProductId().equals("") || request.getAmount() == 0 ){ 
            return false;
        }
        return true;
    }

    @GetMapping(path = "/moreProductSelt")
    private ProductModel getMoreProductSelt(){
        int productId = service.getMoreProductSelt();
        Optional<ProductModel> product = productService.getProductsById(parseIntToLong(productId));
        return product.get();
    }

    private long parseIntToLong(int number) {
        String stringNumber = String.valueOf(number);
        return Long.parseLong(stringNumber);
    }


}
