package src.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Entities.Saab95;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests all methods in the class Saab95. 100% method coverage
 */
public class Saab95Test {

    Saab95 testSaab;

    @BeforeEach
    public void init(){
        testSaab = new Saab95();
    }

    /**
     * accelerates the testcar with and without turbo and compares the values to assure function
     */
    @Test
    public void turboIncreasesAcceleration(){

        testSaab.gas(1);
        testSaab.setTurboOff();
        testSaab.gas(0.5);
        double offSpeed = testSaab.getCurrentSpeed();
        testSaab.stopEngine();

        testSaab.startEngine();
        testSaab.gas(1);
        testSaab.setTurboOn();
        testSaab.gas(0.5);
        double onSpeed = testSaab.getCurrentSpeed();


        assertTrue(offSpeed < onSpeed);
    }

    /**
     * makes sure brake() actually slows down the car
     */
    @Test
    public void brakeDecreasesSpeed(){
        testSaab.gas(1);
        double preSpeed = testSaab.getCurrentSpeed();
        testSaab.brake(0.5);
        double postSpeed = testSaab.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }
}
