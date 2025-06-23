package com.example.carritodecompras.service;

import com.example.carritodecompras.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartService {

    private final Map<Long, CartItem> items = new ConcurrentHashMap<>();

    public void addItem(CartItem item) {
        items.put(item.getId(), item);
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public void removeItem(Long id) {
        items.remove(id);
    }
}