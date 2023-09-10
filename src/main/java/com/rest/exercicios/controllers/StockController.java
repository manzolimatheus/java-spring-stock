package com.rest.exercicios.controllers;

import com.rest.exercicios.models.Product;
import com.rest.exercicios.models.StockItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StockController {

    List<Product> stock = new ArrayList<Product>();

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        Map<String, Object> res = new HashMap<>();

        Product product = stock.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (product == null) {
            res.put("isError", true);
            res.put("message", "Product not found!");
            return ResponseEntity.status(404).body(res);
        }

        res.put("product", product);
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        Map<String, Object> res = new HashMap<>();
        res.put("products", stock);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Map<String, Object> res = new HashMap<>();
        stock.add(product);
        res.put("product", product);

        return ResponseEntity.status(201).body(res);
    }

    @PatchMapping("/products/{id}/increment-stock")
    public ResponseEntity<?> incrementStock(@RequestBody StockItem item, @PathVariable String id) {
        Map<String, Object> res = new HashMap<>();

        Product product = stock.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (product == null) {
            res.put("isError", true);
            res.put("message", "Product not found!");
            return ResponseEntity.status(404).body(res);
        }

        try {
            product.incrementStock(item.getQuantity());
            res.put("product", product);
            return ResponseEntity.status(200).body(res);
        } catch (Exception e) {
            res.put("isError", true);
            res.put("message", e.getMessage());
            return ResponseEntity.status(400).body(res);
        }

    }

    @PatchMapping("/products/{id}/decrement-stock")
    public ResponseEntity<?> decrementStock(@RequestBody StockItem item, @PathVariable String id) {
        Map<String, Object> res = new HashMap<>();

        Product product = stock.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if (product == null) {
            res.put("isError", true);
            res.put("message", "Product not found!");
            return ResponseEntity.status(404).body(res);
        }

        try {
            product.decrementStock(item.getQuantity());
            res.put("product", product);
            return ResponseEntity.status(200).body(res);
        } catch (Exception e) {
            res.put("isError", true);
            res.put("message", e.getMessage());
            return ResponseEntity.status(400).body(res);
        }
    }

}