package src;

import java.awt.*;
/**
 * The class Saab95 is a subclass of Car and contains values and methods for a specific type of car
 */
public class Saab95 extends Car{

    private boolean turboOn;
    
    public Saab95(){
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
	    setTurboOff();
        setModelName("Saab95");
        stopEngine();
    }

    /**
     * turns on the turbo
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * turns off the turbo
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * a method that calculates a factor for the calculation of a viechle's acceleration
     * @return
     */
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
