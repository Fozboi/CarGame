package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        double beforeSpeed = 10;
        double gasFactor = 0.5;

        testSaab.setCurrentSpeed(beforeSpeed);
        testSaab.setTurboOff();
        testSaab.gas(gasFactor);
        double offSpeed = testSaab.getCurrentSpeed();

        testSaab.setCurrentSpeed(beforeSpeed);
        testSaab.setTurboOn();
        testSaab.gas(gasFactor);
        double onSpeed = testSaab.getCurrentSpeed();


        assertTrue(offSpeed < onSpeed);
    }

    /**
     * makes sure brake() actually slows down the car
     */
    @Test
    public void brakeDecreasesSpeed(){
        testSaab.setCurrentSpeed(20);
        double preSpeed = testSaab.getCurrentSpeed();
        testSaab.brake(0.5);
        double postSpeed = testSaab.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }
}
