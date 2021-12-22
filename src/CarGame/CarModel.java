package src.CarGame;

import src.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CarModel{
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    public Timer timer = new Timer(delay, new TimerListener());

    public final int carSpacing = 100;
    public final int maxNrCars = 10;

    public Dimension worldSize = new Dimension(carSpacing*maxNrCars, 600);

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<IHasTurbo> turboCars = new ArrayList<>();
    ArrayList<IHasTrailer> trailerCars = new ArrayList<>();

    CarListHolder carListHolder;
    private ArrayList<ModelObserver> modelObservers = new ArrayList<>();


    public CarModel(){
        carListHolder = new CarListHolder();

        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Car car : cars) {
                if (hitWall(car)){
                    turnAround(car);
                }
                car.move();
            }

            for (ModelObserver obs : modelObservers){
                obs.update();
            }
        }
    }

    public void addModelObserver(ModelObserver mo){
        modelObservers.add(mo);
    }

    public void removeModelObserver(ModelObserver mo){
        modelObservers.remove(mo);
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
        for(IHasTurbo car : turboCars) {
            car.setTurboOn();
        }
    }

    public void setTurboOff(){
        for(IHasTurbo car : turboCars) {
            car.setTurboOff();
        }
    }

    public void liftBed(){
        for(IHasTrailer car : trailerCars) {
            car.setTrailerUp();
        }
    }

    public void lowerBed(){
        for(IHasTrailer car : trailerCars) {
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

            if (inputCar instanceof IHasTurbo){
                turboCars.add((IHasTurbo) inputCar);
            }
            else if (inputCar instanceof IHasTrailer){
                trailerCars.add((IHasTrailer) inputCar);
            }

            carListHolder.updateCarList(cars);
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
        carListHolder.updateCarList(cars);
    }
}
