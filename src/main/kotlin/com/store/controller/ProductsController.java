package com.store.controller;

import com.store.exception.InvalidProductFieldException;
import com.store.model.Product;
import com.store.model.ProductDetails;
import com.store.model.ProductId;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDetails productDetails) throws InvalidProductFieldException {
        ProductId newProductId = productService.createProduct(productDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProductId)
                .toUri();
        return ResponseEntity.created(location).body(newProductId);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false, name = "type") String type) throws InvalidProductFieldException {
        List<Product> products = productService.getProducts(type);
        return ResponseEntity.ok(products);
    }
}
