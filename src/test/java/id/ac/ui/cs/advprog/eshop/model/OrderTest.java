package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("idididid", this.products, 1000000L, "abhiboy");
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order("idididid", this.products, 1000000L, "abhiboy");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().getSize());
        assertEquals("Shampoo Cap Bambang", order.getProducts().get(1).getProductName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("idididid", order.getId());
        assertEquals(1000000L, order.getOrderTime());
        assertEquals("abhiboy", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("idididid", this.products,
                1000000L, "abhiboy", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("idididid", this.products, 1000000L, "abhiboy", "MEOW");
        });
    }

    @Test
    void testStatusToCancelled(){
        Order order = new Order("idididid", this.products,
                1000000L, "abhiboy");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus(){
        Order order = new Order("idididid", this.products,
                1000000L, "abhiboy");
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}
