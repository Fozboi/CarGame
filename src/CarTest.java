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
    public void moveMovesCarNorth() {
        //north
        testCar.gas(1);
        double yPosBefore = testCar.getPosition().getY();
        testCar.move();
        double yPosAfter = testCar.getPosition().getY();

        assertTrue(yPosBefore > yPosAfter);
    }

    @Test
    public void moveMovesCarEast() {
        //east
        testCar.turnRight();
        double xPosBefore = testCar.getPosition().getX();
        testCar.move();
        double xPosAfter = testCar.getPosition().getX();

        assertTrue(xPosBefore < xPosAfter);
    }

    @Test
    public void moveMovesCarSouth() {
        //south
        testCar.turnRight();
        double yPosBefore = testCar.getPosition().getY();
        testCar.move();
        double yPosAfter = testCar.getPosition().getY();

        assertTrue(yPosBefore < yPosAfter);
    }

    @Test
    public void moveMovesCarWest() {
        //west
        testCar.turnLeft();
        double xPosBefore = testCar.getPosition().getX();
        testCar.move();
        double xPosAfter = testCar.getPosition().getX();

        assertTrue(xPosBefore > xPosAfter);
    }

    @Test
    public void turnLeftWorks(){
        testCar.turnLeft();
        assertTrue(testCar.getDir()==Car.WEST);
    }

    @Test
    public void turnRightWorks(){
        testCar.turnRight();
        assertTrue(testCar.getDir()==Car.EAST);
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
        assertTrue(testCar.getCurrentSpeed() == 0);
    }

    @Test
    public void getColor(){
        assertTrue(testCar.getColor().equals(Color.RED));
    }

    @Test
    public void getEnginePwr(){
        assertTrue(testCar.getEnginePower() == 125);
    }

    @Test
    public void getNrDoors(){
        assertTrue(testCar.getNrDoors() == 2);
    }

    @Test
    public void getModelName(){
        assertTrue(testCar.getModelName().equals("Saab95"));
    }

    @Test
    public void setDir(){
        testCar.setDir(Car.SOUTH);
        assertTrue(testCar.getDir() == Car.SOUTH);
    }

    @Test
    public void setPosition(){
        Point testpos = new Point(20,20);
        testCar.setPosition(testpos);
        assertTrue(testCar.getPosition().equals(testpos));
    }
}
