package src.Entities;

import java.awt.*;
import java.util.ArrayList;


/**
 * CarTransport for cars, implements interface CanLoad, Movable, HasTrailer and HasEngine
 */
public class CarTransport extends Truck implements ICanLoad<Car>{
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
        super(2,Color.BLUE,90,"CarTransport");
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
        } else if(trailerIsUp()){
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
        double xdiff = car.getPosition().getX() - getPosition().getX();
        double ydiff = car.getPosition().getY() - getPosition().getY();

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

            int xPos = (int) getPosition().getX();
            int yPos = (int) getPosition().getY();
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

            int xPos = (int) getPosition().getX();
            int yPos = (int) getPosition().getY();

            switch (getDir()) {
                case NORTH -> yPos += pickupRange;
                case EAST -> xPos -= pickupRange;
                case SOUTH -> yPos -= pickupRange;
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
        move();
        Point newPos = getPosition();

        for (Car loadedCar : loadedCars) {
            loadedCar.setPosition(newPos);
        }
    }

    /**
     * turns the truck in the left direction
     */
    @Override
    public void turnLeft() {
        turnLeft();
        int newDir = getDir();

        for(Car car : loadedCars){
            car.setDir(newDir);
        }
    }

    /**
     * turns the truck in the right direction
     */
    @Override
    public void turnRight() {
        turnRight();
        int newDir = getDir();

        for(Car car : loadedCars){
            car.setDir(newDir);
        }
    }

    /**
     * returns the speed factor of the truck
     */
    @Override
    public double speedFactor(){
        if(trailerIsUp()){
            return getEnginePower() * 0.01;
        }
        else throw new IllegalStateException("Can't move while trailer is raised");
    }

    /**
     * returns the range inside which the truck could pick up something inside
     */
    public double getPickupRange() {return pickupRange;}

    /**
     * sets the angle of the truck trailer
     */
    @Override
    public void setTrailerAngle(double newAngle) {System.out.println("Transporters can only have their ramp/trailer up or down");}
}
