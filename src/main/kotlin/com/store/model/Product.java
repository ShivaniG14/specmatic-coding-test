package com.store.model;

public class Product {

    private int id;
    private String name;
    private ProductType type;
    private int inventory;

    public Product() {
    }

    public Product(int id, String name, ProductType type, int inventory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
