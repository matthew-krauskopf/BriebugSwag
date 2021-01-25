package com.briebug.swag.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Float cost;

    @Min(0)
    private Integer stock;

    public Product () {}

    public Product(String name, Float cst, Integer stock) {
        this.name = name;
        this.cost = cst;
        this.stock = stock;
    }

    public Product(Long id, String name, Float cst, Integer stock) {
        this.id = id;
        this.name = name;
        this.cost = cst;
        this.stock = stock;
    }

    public String toString() {
        return String.format("ID: %d | Name: %s | Cost: $%.2f | Stock: %d ", id, name, cost, stock);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean equals(Product product) {
        return ((name.equals(product.getName())) &&
                (id.equals(product.getId())) &&
                (cost.equals(product.getCost())) &&
                (stock.equals(product.getStock())));
    }
}
