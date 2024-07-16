package com.store.model;

import jakarta.validation.constraints.NotNull;

public class Product extends ProductDetails {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Product(@NotNull String name, @NotNull ProductType type, @NotNull int inventory, @NotNull Double cost, int id) {
        super(name, type, inventory, cost);
        this.id = id;
    }

    public Product(@NotNull String name, @NotNull String type, @NotNull int inventory, @NotNull Double cost, int id) {
        super(name, type, inventory, cost);
        this.id = id;
    }

}
