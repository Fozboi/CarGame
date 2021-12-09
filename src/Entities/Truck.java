package src.Entities;

import java.awt.*;

/**
 * The class Truck is a subclass of Car and implements interface HasTrailer
 */
public abstract class Truck extends Car implements IHasTrailer {
    protected double trailerAngle;
    protected final int minTrailerAngle = 0;
    protected final int maxTrailerAngle = 70;

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    /**
     * initialises methods for the trailer angle, as well as the speed factor for the truck
     */
    public void setTrailerAngle(double angle){
        trailerAngle = angle;
    }

    public double getTrailerAngle() {
        return trailerAngle;
    }

    @Override
    public abstract double speedFactor();

    /**
     * returns true if trailer is put up
     */
    @Override
    public boolean trailerIsUp(){
        return trailerAngle == minTrailerAngle;
    }

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