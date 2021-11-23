package src;

import java.awt.*;

public class Scania extends Truck{
    private double trailerAngle;
    private final double maxTrailerAngle = 70;
    private final double minTrailerAngle = 0;

    public Scania(){
        super(2,Color.BLUE,90,"Scania");
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
        if(angle <= maxTrailerAngle && angle > minTrailerAngle){
            if(getCurrentSpeed() == 0){
                trailerAngle = angle;
            }
            else throw new IllegalStateException("Can't change trailer angle while vehicle is moving");
        }
        else throw new IllegalArgumentException("Only angles between 0 and 70 are allowed");
    }

    @Override
    public boolean trailerIsUp(){
        return trailerAngle == minTrailerAngle;
    }

    @Override
    public boolean trailerIsDown(){ return trailerAngle == maxTrailerAngle; }

    @Override
    public void setTrailerUp(){
        setTrailerAngle(minTrailerAngle);
    }

    @Override
    public void setTrailerDown(){
        setTrailerAngle(maxTrailerAngle);
    }
}
