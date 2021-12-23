package src.Entities;

import java.awt.*;
/**
 * the Class Volvo240 is a sub class to Car and contains values and methods for a specific type of car.
 */
public class Volvo240 extends Car {

    /**
     * Sets a fixed value of the trim factor that is used in the calculation of the speed of the car.
     */
    private final static double trimFactor = 1.25;

    /**
     * Defines the specific car type Volvo240 and
     * sets the number of doors to 4
     * sets the color to black
     * sets the power of the engine to 100
     * sets the name of the model tp Volvo240
     * initializes the method stop engine which assures that the car does not move it is created
     */
    public Volvo240(){
        super(4,Color.BLACK,100,"Volvo240");
        stopEngine();
    }

    /**
     * The speed factor returns the acceleration of the Volvo240 Car calculated by inserting the engine power
     * from above, multiplied by a constant factor, multiplied with the trim factor from above.
     */
    @Override
    double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
