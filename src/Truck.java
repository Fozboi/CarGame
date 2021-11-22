package src;

import java.awt.*;

public abstract class Truck extends Car implements HasTrailer {
    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public abstract void setTrailerAngle(double angle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}