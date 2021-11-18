package src;

import java.awt.*;

/**
 * Superclass Car, keeps track of a car's direction, position and speed
 * Contains the functions of all cars, but some are overridden in sublcasses
 */
public abstract class Car implements Movable {

    /**
     * 
     */
    private int dir;

    private int xcoord = 0;
    private int ycoord = 0;
    private Point position = new Point(xcoord,ycoord);

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

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
            case NORTH:
                ycoord+= -currentSpeed;
                break;
            case EAST:
                xcoord+= currentSpeed;
                break;
            case SOUTH:
                ycoord+= currentSpeed;
                break;
            case WEST:
                xcoord+= -currentSpeed;
                break;
        }
        position.setLocation(xcoord,ycoord);
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

    public double getCurrentSpeed(){ return currentSpeed; }

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
