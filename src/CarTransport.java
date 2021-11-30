package src;

import java.awt.*;
import java.util.ArrayList;


/**
 * CarTransport for cars, implements interface CanLoad, Movable, HasTrailer and HasEngine
 */
public class CarTransport implements ICanLoad<Car>, IMovable, IHasTrailer, IHasEngine {
    Truck hasATruck;
    int loadCapacity;
    double pickupRange;
    private ArrayList<Car> loadedCars;

    /**
     * final variables for the different directions in order to improve clarity
     */
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    /**
     * CarTransport initialises a truck (hasATruck) of the kind Scania
     * defines the load capacity of the truck
     * sets the pickup range to 20 pixels
     * initialises an arraylist with the capacity of the total amount of cars possible to load
     */
    public CarTransport(int loadCapacity){
        hasATruck = new Scania();
        this.loadCapacity = loadCapacity;
        pickupRange = 20;
        loadedCars = new ArrayList<>(loadCapacity);
    }

    /**
     * returns an ArrayList of the cars loaded on the truck
     */
    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    /**
     * sets the angle to minimum possible (zero) degrees
     */
    public void setRampUp(){
        hasATruck.setTrailerUp();
    }

    /**
     * sets the angle to maximum possible (70) degress
     */
    public void setRampDown(){
        hasATruck.setTrailerDown();
    }

    /**
     * checks if a car can be loaded onto the truck by checking:
     * so it is a car
     * so it is not already loaded on the truck
     * so the total capacity of the truck has not been exceeded
     * so the trailer is put down so the car can be loaded
     * so the car is not a truck (because a truck should not be able to load itself)
     */
    public boolean canLoadObject(Car car){
        if(!inRange(car)){
            return false;
        } else if(loadedCars.contains(car)){
            return false;
        } else if(loadedCars.size() >= loadCapacity){
            return false;
        } else if(!trailerIsDown()){
            return false;
        } else if(car instanceof Truck){
            return false;
        } else return true;
    }

    /**
     * checks so that the car that should be picked up is in a range of 20 pixels in either
     * x-direction or y-direction from the truck
     * if the distance is greater than allowed, the car could not be picked up
     */
    public boolean inRange (Car car){
        double xdiff = car.getPosition().getX() - hasATruck.getPosition().getX();
        double ydiff = car.getPosition().getY() - hasATruck.getPosition().getY();

        if(Math.abs(xdiff) > pickupRange || Math.abs(ydiff) > pickupRange){
            return false;
        } else return true;
    }

    /**
     * loads the car onto the truck if it is possible
     */
    public void loadObject(Car car){
        if(canLoadObject(car)){
            loadedCars.add(car);

            int xPos = (int) hasATruck.getPosition().getX();
            int yPos = (int) hasATruck.getPosition().getY();
            Point newPos = new Point(xPos, yPos);
            car.setPosition(newPos);
            car.isLoaded = true;
        }
    }


    /**
     * unloads the car off the truck if the number of cars are above zero
     * removes the car from the ArrayList if it had been unloaded
     * gets the x and y position of the truck
     * based on the direction of the truck, the method unloads the car 20 pixels behind the truck
     */
    public void unloadObject(Car car){
        if(loadedCars.size() > 0){
            loadedCars.remove(car);

            int xPos = (int) hasATruck.getPosition().getX();
            int yPos = (int) hasATruck.getPosition().getY();

            switch (hasATruck.getDir()) {
                case NORTH -> yPos += pickupRange;
                case EAST -> xPos += -pickupRange;
                case SOUTH -> yPos += -pickupRange;
                case WEST -> xPos += pickupRange;
            }
            Point newPos = new Point(xPos,yPos);
            car.setPosition((newPos));
            car.isLoaded = false;
        }
    }

    /**
     * unloads the last car off the truck and updates the list on which car
     * that is next in turn to be loaded off
     */
    public void unloadLastCar(){
        Car lastCar = loadedCars.get(loadedCars.size()-1);
        unloadObject(lastCar);
    }

    /**
     * moves the truck and makes the cars loaded onto it move with it
     */
    @Override
    public void move(){
        hasATruck.move();
        Point newPos = hasATruck.getPosition();

        for(int i = 0; i < loadedCars.size(); i++){
            loadedCars.get(i).setPosition(newPos);
            loadedCars.get(i).setDir(hasATruck.getDir());
        }
    }

    /**
     * turns the truck in the left direction
     */
    @Override
    public void turnLeft() {
        hasATruck.turnLeft();
    }

    /**
     * turns the truck in the right direction
     */
    @Override
    public void turnRight() {
        hasATruck.turnRight();
    }

    /**
     * returns the speed factor of the truck
     */
    double speedFactor() { return hasATruck.speedFactor(); }

    /**
     * returns the position of the truck
     */
    public Point getPosition(){return hasATruck.getPosition();}

    /**
     * sets the position of the truck
     */
    public void setPosition(Point newPos){ hasATruck.setPosition(newPos);}

    /**
     * increases the speed of the truck
     */
    public void gas(double amount){hasATruck.gas(amount);}

    /**
     * decreases the speed of the truck
     */
    public void brake(double amount){hasATruck.brake(amount);}

    /**
     * starts the engine of the truck
     */
    public void startEngine(){hasATruck.startEngine();}

    /**
     * stops the engine of the truck
     */
    public void stopEngine(){hasATruck.stopEngine();}

    /**
     * returns the engine power of the truck
     */
    @Override
    public double getEnginePower() { return hasATruck.getEnginePower(); }

    /**
     * returns the current speed of the truck
     */
    public double getCurrentSpeed() {return hasATruck.getCurrentSpeed(); }

    /**
     * returns the direction of the truck
     */
    public int getDir() {return hasATruck.getDir();}

    /**
     * sets the direction of the truck
     */
    public void setDir(int newDir) {hasATruck.setDir(newDir);}

    /**
     * returns the actual truck
     */
    public Truck getTruck(){ return hasATruck; }

    /**
     * returns the range inside which the truck could pick up something inside
     */
    public double getPickupRange(){return pickupRange;}

    /**
     * sets the angle of the truck trailer
     */
    @Override
    public void setTrailerAngle(double newAngle) {throw new IllegalArgumentException("Transporters can only have their ramp/trailer up or down");}

    /**
     * returns the angle of the truck trailer
     */
    @Override
    public double getTrailerAngle() {throw new IllegalArgumentException("Transporters do now have an angle of their ramp");}

    /**
     * returns true of the trailer is up, false otherwise
     */
    @Override
    public boolean trailerIsUp() {return hasATruck.trailerIsUp();}

    /**
     * returns true of the trailer is down, false otherwise
     */
    public boolean trailerIsDown() {return hasATruck.trailerIsDown();}

    /**
     * sets the trailer down
     */
    @Override
    public void setTrailerDown() {setRampDown();}

    /**
     * sets the trailer up
     */
    @Override
    public void setTrailerUp() { setRampUp();}
}
