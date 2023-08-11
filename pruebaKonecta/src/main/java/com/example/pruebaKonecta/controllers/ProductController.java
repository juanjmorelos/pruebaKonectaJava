package com.example.pruebaKonecta.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.example.pruebaKonecta.models.ProductModel;
import com.example.pruebaKonecta.services.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(path = "/getProducts")
    public ArrayList<ProductModel> getProduct() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/insertProduct")
    public ResponseEntity<Map<String, Object>> insertProduct(@RequestBody ProductModel request) {

        ProductModel product = new ProductModel();
        Map<String, Object> response = new HashMap<>();

        if(!isValidateRequest(request)) {
            response.put("result", "fail");
            response.put("cause", "Campos incompletos");
            return ResponseEntity.badRequest().body(response);
        }

        ProductModel insertedProduct = productService.insertProducts(productToInsert(product, request));
       
        response.put("result", "OK");
        response.put("ProductId", insertedProduct.getProductId());
        return ResponseEntity.ok(response);
        
    }

    
    private boolean isValidateRequest(ProductModel request) {
        if(request.getProductName().equals("") || 
        request.getProductReference().equals("") || 
        request.getProductPrice() < 0 || request.getProductWeight() < 0 || request.getProductStock() < 0){ 
            return false;
        }
        return true;
    }
    
    @DeleteMapping(path = "/deleteProduct")
    public ResponseEntity<Map<String, Object>> deleteProductbyId(@RequestParam Long productId) {

        Map<String, Object> response = new HashMap<>();
        
        if(productId == null) {
            response.put("Result", "Fail");
            response.put("msg", "Debe indicar el id del producto");
            return ResponseEntity.badRequest().body(response);
        }
        
        Optional<ProductModel>product = productService.getProductsById(productId);
        
        if(product.isPresent()) {
            productService.deleteProducts(productId);
            response.put("Result", "Ok");
            return ResponseEntity.ok(response);
        } else {
            response.put("Result", "Fail");
            response.put("msg", "El producto que desea eliminar no existe");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/editProduct")
    public ResponseEntity<Map<String, Object>> editProductbyId(@RequestBody ProductModel request) {
        Map<String, Object> response = new HashMap<>();

        if(request.getProductId() == null) {
            response.put("Result", "Fail");
            response.put("msg", "Debe enviar un id de producto");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<ProductModel>product = productService.getProductsById(request.getProductId());
        
        if(product.isPresent()) {
            ProductModel insertedProduct = productService.insertProducts(productToInsert(product.get(), request));
            response.put("result", "OK");
            response.put("ProductId", insertedProduct.getProductId());
            return ResponseEntity.ok(response);
        } else {
            response.put("Result", "Fail");
            response.put("msg", "El producto que desea editar no existe");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    private ProductModel productToInsert(ProductModel productsToSave, ProductModel request) {
        ProductModel product = productsToSave;
        product.setProductName(request.getProductName());
        product.setProductReference(request.getProductReference());
        product.setProductPrice(request.getProductPrice());
        product.setProductWeight(request.getProductWeight());
        product.setProductCategory(request.getProductCategory());
        product.setProductStock(request.getProductStock());
        System.out.println("Estock mandado: " + request.getProductStock());
        return product;
    }

    @GetMapping(path = "/getProductsById")
    public ResponseEntity<Map<String, Object>> getProductById(@RequestParam Long productId) {
        Map<String, Object> response = new HashMap<>();

        if(productId == null) {
            response.put("Result", "Fail");
            response.put("msg", "Debe enviar un id de producto");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<ProductModel>product = productService.getProductsById(productId);

        if(product.isPresent()) {
            response.put("result", "OK");
            response.put("data", product.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("Result", "Fail");
            response.put("msg", "El producto que desea editar no existe");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(path = "/moreStockProduct")
    public ProductModel moreStockroduct(){
        return productService.getMoreStock();
    }


}
