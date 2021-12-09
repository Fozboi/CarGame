package src.Tests;

import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import org.junit.jupiter.api.Test;
import src.Entities.CarTransport;
import src.Entities.Saab95;
import src.Entities.Scania;
import src.Entities.Volvo240;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransportTest {
    CarTransport testTransporter;
    Saab95 testCar;
    Scania testScania;
    Point transporterPos = new Point(170,200);
    Point carPos = new Point (200,200);

    @BeforeEach
    public void init(){
        testTransporter = new CarTransport(1);
        testCar = new Saab95();
    }

    @Test
    public void cantLoadOverCapacity(){
        Volvo240 testVolvo = new Volvo240();

        testTransporter.setPosition(transporterPos);
        testCar.setPosition(transporterPos);
        testVolvo.setPosition(transporterPos);
        testTransporter.setTrailerDown();

        testTransporter.loadObject(testCar);
        assertFalse(testTransporter.canLoadObject(testVolvo));
    }

    @Test
    public void unloadWorks(){
        testTransporter.loadObject(testCar);
        testTransporter.unloadObject(testCar);
        assertEquals(0, testTransporter.getLoadedCars().size());
    }

    @Test
    public void cantLoadOutOfRange(){
        testTransporter.setPosition(transporterPos);
        testCar.setPosition(carPos);
        testTransporter.setTrailerDown();

        assertFalse(testTransporter.canLoadObject(testCar));
    }

    @Test
    public void cantLoadTruck(){
        testScania = new Scania();
        testTransporter.setPosition(transporterPos);
        testScania.setPosition(transporterPos);
        testTransporter.setTrailerDown();

        assertFalse(testTransporter.canLoadObject(testScania));
    }
}
