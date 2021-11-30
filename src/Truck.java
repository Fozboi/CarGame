package src;

import java.awt.*;

/**
 * The class Truck is a subclass of Car and implements interface HasTrailer
 */
public abstract class Truck extends Car implements IHasTrailer {
    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    /**
     * initialises methods for the trailer angle, as well as the speed factor for the truck
     */
    public abstract void setTrailerAngle(double angle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}