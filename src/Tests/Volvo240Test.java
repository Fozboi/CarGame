package src.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Entities.Volvo240;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests all methods in Volvo240, 100% method coverage
 */
public class Volvo240Test {
    Volvo240 testVolvo;

    @BeforeEach
    public void init(){
        testVolvo = new Volvo240();
    }

    /**
     * makes sure brake() method slows down the car
     */
    @Test
    public void brakeDecreasesSpeed(){
        testVolvo.gas(1);
        double preSpeed = testVolvo.getCurrentSpeed();
        testVolvo.brake(0.5);
        double postSpeed = testVolvo.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }
}
