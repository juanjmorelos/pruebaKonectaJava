package com.example.pruebaKonecta.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "venta")
public class SellProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Long idVenta;
    @Column(name = "producto")
    private Long productId;
    @Column(name = "cantidad")
    private int amount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaVenta")
    private Date productCreateDate;

    public Long getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Date getProductCreateDate() {
        return productCreateDate;
    }
    public void setProductCreateDate(Date productCreateDate) {
        this.productCreateDate = productCreateDate;
    }
    
    

    
}
