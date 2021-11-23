package src;

import java.awt.*;

/**
 * Superclass Car, keeps track of a car's direction, position and speed
 * Contains the functions of all cars, but some are overridden in sublcasses
 */
public abstract class Car implements Movable {

    /**
     * Int dir stores a value corresponding to the direction of the car
     */
    private int dir;
    /**
     * coordinates for the car's position.
     */
    private int xcoord = 0;
    private int ycoord = 0;
    private Point position = new Point(xcoord,ycoord);

    /**
     * final variables for the different directions in order to improve clarity
     */
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    /**
     * Declaring variables determining car specifications
     */
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    public Car(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
    }
    /**
     * the move() method moves a car with its current speed in the direction it is facing, then updates the
     * position of the car.
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

    /**
     * turns the car to the left
     */
    public void turnLeft(){
        dir = (dir+3)%4;
    }

    /**
     * turns the car to the right
     */
    public void turnRight(){
        dir = (dir+1)%4;
    }

    /**
     * returns the car's direction
     * @return
     */
    public int getDir(){
        return dir;
    }

    /**
     * sets the car's direction to a determined value
     * @param dir
     */
    public void setDir(int dir){
        this.dir = dir;
    }

    /**
     * returns the car's number of doors
     * @return
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * returns the car's engine power
     * @return
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * returns the car's current speed
     * @return
     */
    public double getCurrentSpeed(){ return currentSpeed; }

    /**
     * returns the car's color
     * @return
     */
    public Color getColor(){
        return color;
    }

    /**
     * returns the car's model name
     * @return
     */
    public String getModelName(){ return modelName; }

    /**
     * returns the car's current position
     * @return
     */
    public Point getPosition(){return position;}

    /**
     * sets the car's position to a determined point
     * @param position
     */
    public void setPosition(Point position){
        this.position = position;
    }

    /**
     * starts the car's engine, setting it to roll slowly forward
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * stops the car's engine, making it stop completely
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * abstract variable for a car's speed factor, a factor for how quick it is. Is overridden in subclasses by
     * specifik calculations unique for each car model
     * @return
     */
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
