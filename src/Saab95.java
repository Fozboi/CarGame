package src;

import java.awt.*;
/**
 * The class Saab95 is a subclass of Car and contains values and methods for a specific type of car
 */
public class Saab95 extends Car{

    /**
     * Creates the function for turbo, where a true value sets the turbo on, and a false value sets the turbo to off.
     * */
    private boolean turboOn;

    /**
     * Defines the specific car type Saab 95 and
     * sets the number of doors to 2
     * sets the color to red
     * sets the power of the engine to 125
     * sets the turbo to off by calling a method from below
     * sets the name of the model tp Saab95
     * initializes the method stop engine which assures that the car does not move it is created
     */
    public Saab95(){
        super(2, Color.RED, 125, "Saab95");
	    setTurboOff();
        stopEngine();
    }

    /**
     * turns the turbo on
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * turns the turbo off
     */
    public void setTurboOff(){
	    turboOn = false;
    }


    /**
     * The speed factor returns the acceleration of the Saab95 Car calculated by inserting the turbo, which if it
     * is off returns a factor of 1, and if it is on returns a factor of 1.3.
     * inserting the engine power from above, multiplied by a constant factor, multiplied with the turbo factor
     * from above.
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
