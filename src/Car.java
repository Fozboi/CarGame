package src;

import java.awt.*;

public abstract class Car {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    public int getNrDoors(){
        return nrDoors;
    }
    public void setNrDoors(int nr){nrDoors = nr;}

    public double getEnginePower(){
        return enginePower;
    }
    public void setEnginePower(double pwr){enginePower = pwr;}

    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public void setCurrentSpeed(double speed){currentSpeed = speed;}

    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }

    public String getModelName(){ return modelName; }
    public void setModelName(String name){modelName = name;}

    public void startEngine(){
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        currentSpeed = 0;
    }

    abstract double speedFactor();

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }
}
