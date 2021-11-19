package src;

public abstract class Truck extends Car implements HasTrailer {
    public abstract void setTrailerAngle(double angle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}