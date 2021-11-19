package src;

import java.awt.*;
import java.util.ArrayList;

public class CarTransport implements CanLoad<Car>, Movable, HasTrailer{
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

    public CarTransport(int loadCapacity){
        hasATruck = new Scania();
        this.loadCapacity = loadCapacity;
        pickupRange = 20;
        loadedCars = new ArrayList<>(loadCapacity);
    }

    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    public void setRampUp(){
        hasATruck.setTrailerUp();
    }

    public void setRampDown(){
        hasATruck.setTrailerDown();
    }

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

    public boolean inRange (Car car){
        double xdiff = car.getPosition().getX() - hasATruck.getPosition().getX();
        double ydiff = car.getPosition().getY() - hasATruck.getPosition().getY();

        if(Math.abs(xdiff) > pickupRange || Math.abs(ydiff) > pickupRange){
            return false;
        } else return true;
    }

    public void loadObject(Car car){
        if(canLoadObject(car)){
            loadedCars.add(car);
        }
    }

    public void unloadObject(Car car){
        if(loadedCars.size() > 0){
            loadedCars.remove(car);

            int xPos = (int) hasATruck.getPosition().getX();
            int yPos = (int) hasATruck.getPosition().getY();

            switch (hasATruck.getDir()){
                case NORTH:
                    yPos += pickupRange;
                    break;
                case EAST:
                    xPos += -pickupRange;
                    break;
                case SOUTH:
                    yPos += -pickupRange;
                    break;
                case WEST:
                    xPos += pickupRange;
                    break;
            }
            Point newPos = new Point(xPos,yPos);
            car.setPosition((newPos));
        }
    }

    public void unloadLastCar(){
        Car lastCar = loadedCars.get(loadedCars.size()-1);
        unloadObject(lastCar);
    }

    @Override
    public void move(){
        hasATruck.move();
        Point newPos = hasATruck.getPosition();

        for(int i = 0; i < loadedCars.size(); i++){
            loadedCars.get(i).setPosition(newPos);
            loadedCars.get(i).setDir(hasATruck.getDir());
        }
    }

    @Override
    public void turnLeft() {
        hasATruck.turnLeft();
    }
    @Override
    public void turnRight() {
        hasATruck.turnLeft();
    }
    double speedFactor() {
        return hasATruck.speedFactor();
    }
    @Override
    public Point getPosition(){return hasATruck.getPosition();}
    @Override
    public void setPosition(Point newPos){ hasATruck.setPosition(newPos);}
    @Override
    public double getEnginePower() {return hasATruck.getEnginePower();}
    @Override
    public void gas(double amount){hasATruck.gas(amount);}
    @Override
    public void brake(double amount){hasATruck.brake(amount);}
    @Override
    public void startEngine(){hasATruck.startEngine();}
    @Override
    public void stopEngine(){hasATruck.stopEngine();}
    @Override
    public double getCurrentSpeed() {return hasATruck.getCurrentSpeed(); }
    @Override
    public int getDir() {return hasATruck.getDir();}
    @Override
    public void setDir(int newDir) {hasATruck.setDir(newDir);}
    public Truck getTruck(){ return hasATruck; }
    public double getPickupRange(){return pickupRange;}
    @Override
    public void setTrailerAngle(double newAngle) {throw new IllegalArgumentException("Transporters can only have their ramp/trailer up or down");}
    @Override
    public double getTrailerAngle() {throw new IllegalArgumentException("Transporters do now have an angle of their ramp");}
    @Override
    public boolean trailerIsUp() {return hasATruck.trailerIsUp();}
    @Override
    public boolean trailersIsDown() {return hasATruck.trailersIsDown();}
    @Override
    public void setTrailerDown() {setRampDown();}
    @Override
    public void setTrailerUp() { setRampUp();}
}
