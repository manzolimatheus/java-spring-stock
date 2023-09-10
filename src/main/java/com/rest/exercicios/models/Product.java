package com.rest.exercicios.models;

public class Product {
    private String id;
    private String name;
    private Integer stock;
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = 0;
    }

    public Product incrementStock(Integer newQuantity) throws Exception {
        setStock(getStock() + newQuantity);
        return this;
    }

    public Product decrementStock(Integer newQuantity) throws Exception {
        if (getStock() - newQuantity < 0) throw new Exception("Invalid Quantity!");
        setStock(getStock() - newQuantity);
        return this;
    }

    public Product printReport() {
        return this;
    }
}
