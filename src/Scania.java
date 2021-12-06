package src;

import java.awt.*;

/**
 * The class Scania is a subclass of Truck and initialises values for the angle of the trailer
 */
public class Scania extends Truck{
    private double trailerAngle;
    private final double maxTrailerAngle = 70;
    private final double minTrailerAngle = 0;

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
    public double speedFactor(){
        if(trailerIsUp()){
            return getEnginePower() * 0.01;
        }
        else throw new IllegalStateException("Can't move while trailer is raised");
    }

    /**
     * returns the angle of the trailer
     */
    @Override
    public double getTrailerAngle(){
        return trailerAngle;
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

    /**
     * returns true if trailer is put up
     */
    @Override
    public boolean trailerIsUp(){
        return trailerAngle == minTrailerAngle;
    }

    /**
     * returns true if trailer is put down
     */
    @Override
    public boolean trailerIsDown(){ return trailerAngle == maxTrailerAngle; }

    /**
     * sets the trailer angle value to min (0)
     */
    @Override
    public void setTrailerUp(){
        setTrailerAngle(minTrailerAngle);
    }

    /**
     * sets the trailer angle value to max (70)
     */
    @Override
    public void setTrailerDown(){
        setTrailerAngle(maxTrailerAngle);
    }
}
