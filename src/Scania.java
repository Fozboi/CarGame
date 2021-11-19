package src;

import java.awt.*;

public class Scania extends Truck{
    private double trailerAngle;
    private final double maxTrailerAngle = 70;

    public Scania(){
        super.nrDoors = 2;
        super.color = Color.BLUE;
        super.enginePower = 90;
        super.modelName = "Scania";
        stopEngine();
    }

    @Override
    public double speedFactor(){
        if(trailerIsUp()){
            return getEnginePower() * 0.01;
        }
        else throw new IllegalStateException("Can't move while trailer is raised");
    }

    @Override
    public double getTrailerAngle(){
        return trailerAngle;
    }

    @Override
    public void setTrailerAngle(double angle){
        if(angle <= maxTrailerAngle && angle >= 0){
            if(getCurrentSpeed() == 0){
                trailerAngle = angle;
            }
            else throw new IllegalStateException("Can't change trailer angle while vehicle is moving");
        }
        else throw new IllegalArgumentException("Only angles between 0 and 70 are allowed");
    }

    @Override
    public boolean trailerIsUp(){
        return trailerAngle == 0;
    }

    @Override
    public boolean trailerIsDown(){
        return trailerAngle == maxTrailerAngle;
    }

    @Override
    public void setTrailerUp(){
        setTrailerAngle(0);
    }

    @Override
    public void setTrailerDown(){
        setTrailerAngle(maxTrailerAngle);
    }
}
