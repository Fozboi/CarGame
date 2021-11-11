package src;

import java.awt.*;

/**
 * Superklass Car, håller reda på en bils riktning, position och hastighet
 * Innehåller samtliga bilars funktioner men vissa override:as av subklasser
 */
public abstract class Car implements Movable {

    private int dir;

    private Point position = new Point(0,0);
    private double xcoord;
    private double ycoord;

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    /**
     * the move() method moves a car with its current speed in the direction it is facing
     */
    public void move(){
        switch (dir) {
            case 0:
                ycoord+= -currentSpeed;
                break;
            case 1:
                xcoord+= currentSpeed;
                break;
            case 2:
                ycoord+= currentSpeed;
                break;
            case 3:
                xcoord+= -currentSpeed;
                break;
        }

        position = new Point((int) xcoord,(int) ycoord);
    }

    public void turnLeft(){
        dir = (dir+3)%4;
    }

    public void turnRight(){
        dir = (dir+1)%4;
    }

    public int getDir(){
        return dir;
    }

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

    /**
     * calculates the incremental speed increase when gas() is called
     * @param amount
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * calculates the incremental speed decrease when brake() is called
     * @param amount
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * accepts values between 0 and 1 and increases the car's speed by calling incrementSpeed()
     * @param amount
     */
    public void gas(double amount){
        if(0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        }else throw new IllegalArgumentException("bara värden mellan 0 och 1 tillåtna");
    }

    /**
     * accepts values between 0 and 1 and decreases the car's speed by calling decrementSpeed()
     * @param amount
     */
    public void brake(double amount){
        if(0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        }else throw new IllegalArgumentException("bara värden mellan 0 och 1 tillåtna");
    }
}
