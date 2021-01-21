package com.briebug.swag.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "products")
public class Product {
    @Id
    private Integer id;
    private String name;
    private Float cost;
    private Integer stock;

    public Product() {
        this.id = null;
        this.name = null;
        this.cost = null;
        this.stock = null;
    }

    public Product(Integer id, String name, Float cst, Integer stock) {
        this.id = id;
        this.name = name;
        this.cost = cst;
        this.stock = stock;
    }

    public String toString() {
        return String.format("ID: %d | Name: %s | Cost: $%.2f | Stock: %d ", id, name, cost, stock);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
