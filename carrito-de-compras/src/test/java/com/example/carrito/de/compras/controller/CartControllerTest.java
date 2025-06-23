package test.java.com.example.carrito.de.compras.controller;

import com.example.carrito.de.compras.controller.CartController;
import com.example.carrito.de.compras.model.CartItem;
import com.example.carrito.de.compras.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void addItemReturnsOk() throws Exception {
        String json = "{\"id\":1,\"name\":\"Apple\",\"quantity\":2,\"price\":1.5}";

        mockMvc.perform(post("/api/cart/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added"));

        verify(cartService).addItem(any(CartItem.class));
    }
}