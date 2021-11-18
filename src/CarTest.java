package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

/**
 * CarTest tests methods in Car to make sure they're all working
 */

public class CarTest {

    public Point position = new Point(200,200);
    Saab95 testCar;
    public int dir = 0;

    @BeforeEach
    public void init(){
        testCar = new Saab95();
    }

    /**
     * makes sure move() method actually moves the car, first north, then the other directions
     * in order
     */
    @Test
    public void moveMovesCarInRightDir(){
        //north
        testCar.currentSpeed=10;
        double yPosBefore = testCar.getPosition().getY();
        testCar.move();
        double yPosAfter = testCar.getPosition().getY();

        assertTrue(yPosBefore > yPosAfter);

        //east
        testCar.turnRight();
        double xPosBefore = testCar.getPosition().getX();
        testCar.move();
        double xPosAfter = testCar.getPosition().getX();

        assertTrue(xPosBefore < xPosAfter);

        //south
        testCar.turnRight();
        yPosBefore = testCar.getPosition().getY();
        testCar.move();
        yPosAfter = testCar.getPosition().getY();

        assertTrue(yPosBefore < yPosAfter);

        //west
        testCar.turnRight();
        xPosBefore = testCar.getPosition().getX();
        testCar.move();
        xPosAfter = testCar.getPosition().getX();

        assertTrue(xPosBefore > xPosAfter);

    }

    @Test
    public void turnLeftWorks(){
        testCar.turnLeft();
        assertTrue(testCar.getDir()==3);
    }

    @Test
    public void turnRightWorks(){
        testCar.turnRight();
        assertTrue(testCar.getDir()==1);
    }

    /**
     * makes sure brake() method actually slows down the car
     */
    @Test
    public void breakDecreasesSpeed(){
        testCar.gas(1);
        double beforeSpeed = testCar.getCurrentSpeed();
        testCar.brake(0.5);
        double afterSpeed = testCar.getCurrentSpeed();

        assertTrue(afterSpeed < beforeSpeed);
    }

    /**
     * makes sure gas() actually speeds up the car
     */
    @Test
    public void gasIncreasesSpeed(){
        testCar.gas(1);
        double beforeSpeed = testCar.getCurrentSpeed();
        testCar.gas(0.5);
        double afterSpeed = testCar.getCurrentSpeed();

        assertTrue(afterSpeed > beforeSpeed);
    }

    /**
     * makes sure only values between 0 and 1 are accepted in brake
     */
    @Test
    public void breakOnlyAcceptsValidValues(){
        try{testCar.brake(1.5);
            fail("should have thrown exception");
        } catch (IllegalArgumentException expectedException){ }
    }

    /**
     * makes sure only values between 0 and 1 are accepted in gas
     */
    @Test
    public void gasOnlyAcceptsValidValues(){
        try{testCar.gas(-1.5);
            fail("should have thrown exception");
        } catch (IllegalArgumentException expectedException){ }
    }

    @Test
    public void startEngineWorks(){
        testCar.startEngine();
        assertTrue(testCar.getCurrentSpeed()==0.1);
    }

    @Test
    public void stopEngineWorks(){
        testCar.stopEngine();
        assertTrue(testCar.getCurrentSpeed()==0);
    }

    @Test
    public void getColor(){
        testCar.color = Color.WHITE;
        assertTrue(testCar.getColor()==Color.WHITE);
    }

    @Test
    public void getEnginePwr(){
        testCar.enginePower = 100;
        assertTrue(testCar.getEnginePower()==100);
    }

    @Test
    public void getNrDoors(){
        testCar.nrDoors = 3;
        assertTrue(testCar.getNrDoors()==3);
    }

    @Test
    public void getModelName(){
        testCar.modelName = "Saab95";
        assertTrue(testCar.getModelName() == "Saab95");
    }
}
