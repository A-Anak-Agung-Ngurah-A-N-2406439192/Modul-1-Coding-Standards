package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    // UBAH JADI CLASS IMPL
    @InjectMocks
    CarRepositoryImpl carRepository;

    @BeforeEach
    void setUp(){
    }

    @Test
    void testCreateAndFind(){
        Car car = new Car();
        car.setCarId("testcarid");
        car.setCarColor("white");
        car.setCarName("Lamborghini");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindIdFound(){
        Car car = new Car();
        car.setCarId("testcarid");
        car.setCarColor("white");
        car.setCarName("Lamborghini");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals(car.getCarName(), foundCar.getCarName());
    }

    @Test
    void testFindIdNotFound(){
        Car foundCar = carRepository.findById("apappa");
        assertNull(foundCar);
    }

    @Test
    void testUpdateCar(){
        Car car = new Car();
        car.setCarColor("white");
        car.setCarName("Lamborghini");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarColor("black");
        updatedCar.setCarName("Ferrari");
        updatedCar.setCarQuantity(10);

        Car result = carRepository.update(car.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("black", result.getCarColor());
        assertEquals("Ferrari", result.getCarName());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testDeleteCar(){
        Car car = new Car();
        car.setCarColor("white");
        car.setCarName("Lamborghini");
        car.setCarQuantity(100);
        carRepository.create(car);

        carRepository.delete(car.getCarId());

        Car foundCar = carRepository.findById(car.getCarId());
        assertNull(foundCar);
    }
}