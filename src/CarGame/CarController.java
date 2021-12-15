package src.CarGame;

import src.Entities.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController extends JFrame{
    // member fields:

    // The delay (ms) corresponds to about 60 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    public final int carSpacing = 100;
    public final int maxNrCars = 8;
    private int gasAmount = 0;
    public Dimension worldSize = new Dimension(carSpacing*maxNrCars, 600);
    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.initFunctionality();

        cc.addCar(new Volvo240());
        cc.addCar(new Saab95());
        cc.addCar(new Scania());
        // Start a new view and send a reference of self

        // Start the timer
        cc.timer.start();
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
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                //frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                frame.speedometer.repaint();
            }
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

    public <T extends IHasTurbo> void setTurboOn(){
        for(Car car : cars){
            try{
                ((T) car).setTurboOn();
            }catch(ClassCastException ex){}
        }
    }

    public <T extends IHasTurbo> void setTurboOff(){
        for(Car car : cars){
            try{
                ((T) car).setTurboOn();
            }catch(ClassCastException ex){}
        }
    }

    public <T extends IHasTrailer> void liftBed(){
        for(Car car : cars){
            try{
                ((T) car).setTrailerUp();
            }catch(ClassCastException ex){}
        }
    }

    public <T extends IHasTrailer> void lowerBed(){
        for(Car car : cars){
            try{
                ((T) car).setTrailerDown();
            }catch(ClassCastException ex){}
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
            for (int i = 0; i <= 7; i++) {

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
        }
    }

    public Car stringToCar(String carName){
        Car newCar = null;

        try{
            Class carClass = Class.forName("Entities." + carName);
            newCar = (Car) carClass.getConstructor().newInstance();

        }catch(Exception e){
            throw new IllegalArgumentException("Unrecognised car model");
        }
        return newCar;
    }

    public void removeCar(){
        cars.remove(cars.size()-1);
        adjustCars();
    }

    private void adjustCars(){
        for(int i = 0; i < cars.size(); i++){
            Point oldPos = cars.get(i).getPosition();
            Point newPos = new Point((int) i*carSpacing,(int) oldPos.getY());
            cars.get(i).setPosition(newPos);
        }
    }

    public ArrayList<Car> getCars(){return cars;}

    private void initFunctionality() {
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(gasAmount);
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brake(gasAmount);
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTurboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTurboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liftBed();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBed();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startEngine();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopEngine();
            }
        });

        frame.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cars.size() < 10){
                    addCar(stringToCar(frame.carModelField.getText()));
                }else{
                    System.out.println("Only 10 cars allowed");
                }
            }
        });

        frame.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cars.size() == 0){
                    System.out.println("No cars exist");
                }
                else
                {
                    removeCar();
                }
            }
        });
    }


}
