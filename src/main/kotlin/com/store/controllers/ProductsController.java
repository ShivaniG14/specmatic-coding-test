package com.store.controllers;

import com.store.exceptions.InvalidProductTypeException;
import com.store.model.*;
import com.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService = new ProductService();

    public ProductsController() {
    }

    @PostMapping
    public ResponseEntity<ProductId> createProduct( @RequestBody @Valid ProductDetails productDetails){
        ProductId newProductId = productService.createProduct(productDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProductId)
                .toUri();
        return ResponseEntity.created(location).body(newProductId);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false,name = "type") String type) {
        List<Product> products=null;
        try {
            products = productService.getProducts(type);
        }catch (InvalidProductTypeException e){
            ErrorResponseBody errorResponse = new ErrorResponseBody();
            errorResponse.setError(e.getMessage());
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponse.setTimestamp(String.valueOf(LocalDateTime.now()));
            errorResponse.setPath(ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.ok(products);
    }

}
