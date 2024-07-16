package com.store.service;

import com.store.exception.InvalidProductFieldException;
import com.store.model.Product;
import com.store.model.ProductDetails;
import com.store.model.ProductId;
import com.store.model.ProductType;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private static final Map<Integer, Product> products = new HashMap<>();
    private int initialId = 0;

    public ProductId createProduct(ProductDetails productDetails) throws InvalidProductFieldException {
        int id = ++initialId;

        Product newProduct = null;
        ProductId newProductId = new ProductId(id);

        if (productDetails.getName() == null || productDetails.getType() == null || productDetails.getInventory() <= 0) {
            throw new InvalidProductFieldException("Invalid product field");
        } else {
            newProduct = new Product(productDetails.getName(), productDetails.getType().name(), productDetails.getInventory(), productDetails.getCost(), newProductId.getId());
        }
        products.put(id, newProduct);
        return newProductId;
    }

    public List<Product> getProducts(String type) throws InvalidProductFieldException {
        List<Product> filteredProducts = new ArrayList<>();
        if (type == null) {
            for (Product product : products.values()) {
                if (product.getCost() != null) {
                    filteredProducts.add(product);
                }
            }
        } else if (!type.matches("^[a-zA-Z]+$")) {
            throw new InvalidProductFieldException("Invalid query param");
        } else {
            try {
                ProductType productType = ProductType.valueOf(type.toLowerCase());
                for (Product product : products.values()) {
                    if (product.getType().equals(productType) && product.getCost() != null) {
                        filteredProducts.add(product);
                    }
                }
            } catch (ConstraintViolationException | IllegalArgumentException e) {
                throw new InvalidProductFieldException("Invalid product type");
            }
        }
        return filteredProducts;
    }
}
