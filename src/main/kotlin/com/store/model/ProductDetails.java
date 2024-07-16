package com.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.exception.InvalidProductFieldException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @JsonProperty(required = false)
    private Double cost;

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) throws InvalidProductFieldException {
        if (cost == null) {
            throw new InvalidProductFieldException("Invalid product cost");
        }
        this.cost = cost;
    }

    public ProductDetails() {
    }

    public ProductDetails(@NotNull String name, @NotNull ProductType type, @NotNull int inventory, Double cost) {
        this.name = name;
        this.type = type;
        this.inventory = inventory;
        this.cost = cost;
    }
}
