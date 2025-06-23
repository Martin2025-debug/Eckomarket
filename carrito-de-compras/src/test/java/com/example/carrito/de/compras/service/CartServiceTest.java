package test.java.com.example.carrito.de.compras.service;

import com.example.carrito.de.compras.model.CartItem;
import com.example.carrito.de.compras.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }

    @Test
    void addAndRetrieveItem() {
        CartItem item = new CartItem(1L, "Apple", 2, 1.5);
        cartService.addItem(item);
        assertEquals(1, cartService.getItems().size());
    }

    @Test
    void removeItem() {
        CartItem item = new CartItem(1L, "Apple", 2, 1.5);
        cartService.addItem(item);
        cartService.removeItem(1L);
        assertTrue(cartService.getItems().isEmpty());
    }

    
    @Test
    void addItemNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cartService.addItem(null));
    }

    @Test
    void addItemWithNullIdThrowsException() {
        CartItem item = new CartItem(null, "Orange", 1, 0.5);
        assertThrows(IllegalArgumentException.class, () -> cartService.addItem(item));
    }
}