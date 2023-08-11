package com.example.pruebaKonecta.models;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "producto")
public class ProductModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long productId;
    @Column(name = "nombreProd")
    private String productName;
    @Column(name = "referenciaProd")
    private String productReference;
    @Column(name = "precioProd")
    private int productPrice;
    @Column(name = "pesoProd")
    private int productWeight;
    @Column(name = "categoriaProd")
    private String productCategory;
    @Column(name = "stockProd")
    private int productStock;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prodCreateDate")
    private Date productCreateDate;

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductReference() {
        return productReference;
    }
    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductWeight() {
        return productWeight;
    }
    public void setProductWeight(int productWeight) {
        this.productWeight = productWeight;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public int getProductStock() {
        return productStock;
    }
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    public Date getProductCreateDate() {
        return productCreateDate;
    }
    public void setProductCreateDate(Date productCreateDate) {
        this.productCreateDate = productCreateDate;
    }
    

    
}
