package src.Entities;

import java.awt.*;

/**
 * The class Scania is a subclass of Truck and initialises values for the angle of the trailer
 */
public class Scania extends Truck{

    /**
     * Defines the specific truck type Scania and
     * sets the number of doors to 2
     * sets the color to blue
     * sets the power of the engine to 90
     * sets the name of the model tp Scania
     * initializes the method stop engine which assures that the truck does not move when it is created
     */
    public Scania(){
        super(2,Color.BLUE,90,"Scania");
        stopEngine();
    }

    /**
     * returns the speed factor if the trailer is up, otherwise returns illegal state exception
     */
    @Override
    public double speedFactor() {
        if (trailerIsUp()) {
            return getEnginePower() * 0.01;
        } else System.out.println("Can't move while trailer is raised");
            return 0;
    }
    /**
     * sets the trailer angle to an input value, and only accepts values above min(0) and equal to or below max (70),
     * and only does so if the trailer speed is zero
     */
    @Override
    public void setTrailerAngle(double angle){
        if(angle <= maxTrailerAngle && angle >= minTrailerAngle){
            if(getCurrentSpeed() == 0){
                trailerAngle = angle;
            }
            else System.out.println("Cant change trailer angle while moving");
        }
        else System.out.println("Only angles between 0 and 70 are allowed");
    }
}
