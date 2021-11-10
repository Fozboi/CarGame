package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Saab95Test {

    Saab95 testSaab;

    @BeforeEach
    public void init(){
        testSaab = new Saab95();
    }

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
}
