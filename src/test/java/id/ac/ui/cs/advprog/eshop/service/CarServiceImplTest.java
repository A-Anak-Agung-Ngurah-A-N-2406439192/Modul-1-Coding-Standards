package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarReadRepository;
import id.ac.ui.cs.advprog.eshop.repository.CarWriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    // DIPISAH SESUAI ISP
    @Mock
    CarWriteRepository carWriteRepository;

    @Mock
    CarReadRepository carReadRepository;

    @InjectMocks
    CarServiceImpl carService;
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
    void testCreate(){
        when(carWriteRepository.create(car)).thenReturn(car);
        Car createdCar = carService.create(car);
        assertEquals(car.getCarId(), createdCar.getCarId());
        verify(carWriteRepository, times(1)).create(car);
    }

    @Test
    void testFindAll(){
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carReadRepository.findAll()).thenReturn(carList.iterator());

        List<Car> foundCar = carService.findAll();
        assertFalse(foundCar.isEmpty());
        assertEquals(1, foundCar.size());
        verify(carReadRepository, times(1)).findAll();
    }

    @Test
    void testFindById(){
        when(carReadRepository.findById("testcarid")).thenReturn(car);
        Car findCar = carService.findById(car.getCarId());
        assertNotNull(findCar);
        assertEquals("Lamborghini", findCar.getCarName());
        verify(carReadRepository, times(1)).findById("testcarid");
    }

    @Test
    void testUpdate(){
        Car updateCar = new Car();
        updateCar.setCarName("ulala");
        carService.update("testcarid", updateCar);
        verify(carWriteRepository, times(1)).update("testcarid", updateCar);
    }

    @Test
    void testDeleteCar(){
        carService.deleteCarById("testcarid");
        verify(carWriteRepository, times(1)).delete("testcarid");
    }
}