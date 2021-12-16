package src.CarGame;

import src.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CarModel extends Observable {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    public Timer timer = new Timer(delay, new TimerListener());

    public final int carSpacing = 100;
    public final int maxNrCars = 10;

    public Dimension worldSize = new Dimension(carSpacing*maxNrCars, 600);

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Saab95> turboCars = new ArrayList<>();
    ArrayList<Truck> trailerCars = new ArrayList<>();

    CarListObserver carListObserver;
    CarView frame;


    public CarModel(){
        carListObserver = new CarListObserver();
        frame = new CarView("CarSim 1.0", carListObserver);

        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Car car : cars) {
                if (hitWall(car)){
                    turnAround(car);
                }
                car.move();
            }
            setChanged();
            notifyObservers();
        }
    }

    private boolean hitWall(Car car){
        int x = (int) car.getPosition().getX();
        int y = (int) car.getPosition().getY();
        int carDir = car.getDir();

        if((x <= 0 && carDir == Car.WEST)
                || (x >= worldSize.width - 120 && carDir == Car.EAST)
                || (y <= 0 && carDir == Car.NORTH)
                || (y >= worldSize.height - 100 && carDir == Car.SOUTH)){
            return true;
        } else return false;
    }

    public void turnAround(Car car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    public void setTurboOn(){
        for(Saab95 car : turboCars) {
            car.setTurboOn();
        }
    }

    public void setTurboOff(){
        for(Saab95 car : turboCars) {
            car.setTurboOff();
        }
    }

    public void liftBed(){
        for(Truck car : trailerCars) {
            car.setTrailerUp();
        }
    }

    public void lowerBed(){
        for(Truck car : trailerCars) {
            car.setTrailerDown();
        }
    }

    public void startEngine(){
        for(Car car : cars){
            car.startEngine();
        }
    }

    public void stopEngine(){
        for(Car car : cars){
            car.stopEngine();
        }
    }

    public void addCar(Car inputCar) {
        boolean found = false;

        if(cars.size() == 0)
            found = true;
        else {
            for (int i = 0; i <= 9; i++) {

                for (Car car : cars) {
                    if (car.getPosition().getX() == i * carSpacing) {
                        break;
                    } else if (car == cars.get(cars.size() - 1)) {
                        found = true;
                    }
                }

                if (found) {
                    inputCar.setPosition(new Point(i * carSpacing, 0));
                    break;
                }
            }
        }

        if(!found){
            System.out.println("No free space!");
        }else{
            cars.add(inputCar);

            if (inputCar instanceof Saab95){
                turboCars.add((Saab95) inputCar);
            }
            else if (inputCar instanceof Truck){
                trailerCars.add((Truck) inputCar);
            }

            carListObserver.updateCarList(cars);
        }
    }

    public void addRandomCar(){
        Random randomizer = new Random();
        int upper = 3;
        int randomCar = randomizer.nextInt(upper);

        switch (randomCar) {
            case 0 -> addCar(new Saab95());
            case 1 -> addCar(new Volvo240());
            case 2 -> addCar(new Scania());
        }
    }

    public void removeCar(){
        cars.remove(cars.size()-1);
        carListObserver.updateCarList(cars);
    }
}
