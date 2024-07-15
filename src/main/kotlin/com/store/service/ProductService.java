package com.store.service;

import com.store.exceptions.InvalidProductTypeException;
import com.store.model.Product;
import com.store.model.ProductDetails;
import com.store.model.ProductId;
import com.store.model.ProductType;

import java.util.*;

public class ProductService {
    private static final Map<Integer, Product> products = new HashMap<>();
    private int initialId = 0;
    public ProductService() {
    }

    public ProductId createProduct(ProductDetails productDetails) {
        int id = ++initialId;

        Product newProduct = new Product();
        ProductId newProductId = new ProductId(id);
        newProduct.setId(newProductId.getId());
        newProduct.setName(productDetails.getName());
        newProduct.setType(productDetails.getType());
        newProduct.setInventory(productDetails.getInventory());

        products.put(id, newProduct);
        return newProductId;
    }

    public List<Product> getProducts(String type) throws InvalidProductTypeException{
        if(type == null) {
            return new ArrayList<>(products.values());
        }
        else if (!type.matches("^(?!true$|false$|\\d+$)[a-zA-Z]+$")){
            throw new InvalidProductTypeException("Invalid query param");
        }
        else {
            ProductType productType = ProductType.valueOf(type.toLowerCase());

            List<Product> filteredProducts = new ArrayList<>();
                for (Product product : products.values()) {
                    if (product.getType().equals(productType)) {
                        filteredProducts.add(product);
                    }
                }

            return filteredProducts;
        }
    }
}
