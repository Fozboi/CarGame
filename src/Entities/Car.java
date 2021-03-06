package src.Entities;

import java.awt.*;

/**
 * Superclass Car, keeps track of a car's direction, position and speed
 * Contains the functions of all cars, but some are overridden in subclasses
 */
public abstract class Car implements IMovable, IHasEngine {

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
    public boolean isLoaded = false;

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
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private final Color color; // Color of the car
    private final String modelName; // The car model name

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
        if (!isLoaded) {
            switch (dir) {
                case NORTH -> ycoord -= currentSpeed;
                case EAST -> xcoord += currentSpeed;
                case SOUTH -> ycoord += currentSpeed;
                case WEST -> xcoord -= currentSpeed;
            }
            position.setLocation(xcoord, ycoord);
        }
    }

    /**
     * turns the car to the left
     */
    public void turnLeft(){
        if (!isLoaded) {
            dir = (dir + 3) % 4;
        }
    }

    /**
     * turns the car to the right
     */
    public void turnRight(){
        if (!isLoaded) {
            dir = (dir + 1) % 4;
        }
    }

    /**
     * returns the car's direction
     * @return car direction
     */
    public int getDir(){
        return dir;
    }

    /**
     * sets the car's direction to a determined value
     * @param dir desired direction
     */
    public void setDir(int dir){
        this.dir = dir;
    }

    /**
     * returns the car's number of doors
     * @return number of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * returns the car's engine power
     * @return car's engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * returns the car's current speed
     * @return car's current speed
     */
    public double getCurrentSpeed(){ return currentSpeed; }

    /**
     * returns the car's color
     * @return car's color
     */
    public Color getColor(){
        return color;
    }

    /**
     * returns the car's model name
     * @return car's model name
     */
    public String getModelName(){ return modelName; }

    /**
     * returns the car's current position
     * @return car's current position
     */
    public Point getPosition(){return position;}

    /**
     * sets the car's position to a determined point
     * @param position desired position
     */
    public void setPosition(Point position){
        this.position = position;
        xcoord = (int) position.getX();
        ycoord = (int) position.getY();
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
     * specific calculations unique for each car model
     * @return the car's speed factor
     */
    abstract double speedFactor();

    /**
     * calculates the incremental speed increase when gas() is called
     * @param amount amount of gas
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * calculates the incremental speed decrease when brake() is called
     * @param amount amount of brake
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * accepts values between 0 and 1 and increases the car's speed by calling incrementSpeed()
     * @param amount amount of gas
     */
    public void gas(double amount){
        if(0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        }else throw new IllegalArgumentException("only values between 0 and 1 allowed");
    }

    /**
     * accepts values between 0 and 1 and decreases the car's speed by calling decrementSpeed()
     * @param amount amount of brake
     */
    public void brake(double amount){
        if(0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        }else throw new IllegalArgumentException("only values between 0 and 1 allowed");
    }
}
