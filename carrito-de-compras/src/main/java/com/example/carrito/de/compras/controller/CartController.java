package com.example.carritodecompras.controller;

import com.example.carritodecompras.model.CartItem;
import com.example.carritodecompras.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody CartItem item) {
        cartService.addItem(item);
        return ResponseEntity.ok("Item added");
    }

    @GetMapping("/items")
    public Collection<CartItem> listItems() {
        return cartService.getItems();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }
}