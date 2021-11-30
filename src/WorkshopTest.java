package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests different methods in Workshop
 */
public class WorkshopTest {
    Workshop<Car> testWorkshop;
    Saab95 testCar;

    @BeforeEach
    public void init(){
        testWorkshop = new Workshop<>(1);
        testCar = new Saab95();
    }

    /**
     * using a workshop with capacity 1, checks if a 2nd car can be loaded.
     */
    @Test
    public void cantLoadOverCapacity(){
        Volvo240 testVolvo = new Volvo240();
        testWorkshop.loadObject(testCar);
        assertFalse(testWorkshop.canLoadObject(testVolvo));

    }

    /**
     * makes sure that a car is actually unloaded when unloadObject() is called
     */
    @Test
    public void unloadWorks(){
        testWorkshop.loadObject(testCar);
        testWorkshop.unloadObject(testCar);
        assertTrue(testWorkshop.getLoadedObjects().size() == 0);
    }
}
