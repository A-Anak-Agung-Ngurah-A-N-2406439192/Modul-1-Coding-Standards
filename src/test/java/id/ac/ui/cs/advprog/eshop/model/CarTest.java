package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarTest {
    Car car;

    @BeforeEach
    void setUp(){
        this.car = new Car();
        this.car.setCarId("testcarid");
        this.car.setCarColor("white");
        this.car.setCarName("Lamborghini");
        this.car.setCarQuantity(100);
    }

    @Test
    void testGetId(){
        assertEquals("testcarid", car.getCarId());
    }

    @Test
    void testGetColor(){
        assertEquals("white", car.getCarColor());
    }

    @Test
    void testGetName(){
        assertEquals("Lamborghini", car.getCarName());
    }

    @Test
    void testGetQuantity(){
        assertEquals(100, car.getCarQuantity());
    }
}
