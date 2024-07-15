package com.store.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class ProductDetails {

    @jakarta.validation.constraints.NotNull
    @Pattern(regexp = "^(?!true$|false$)[a-zA-Z]*$")
    private String name;
    @jakarta.validation.constraints.NotNull
    private ProductType type;

    @jakarta.validation.constraints.NotNull
    @Min(1)
    @Max(9999)
    private int inventory;

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

    public ProductDetails() {
    }

    public ProductDetails(String name, ProductType type, int inventory) {
        this.name = name;
        this.type = type;
        this.inventory = inventory;
    }
}
