package src.CarGame;

import src.Entities.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    CarModel cm;
    CarView cv;
    CarListHolder carListHolder;
    private int gasAmount = 0;

    public CarController(){
        carListHolder = new CarListHolder();
        cv = new CarView("CarSim 1.0", carListHolder);
    }

    protected void initFunctionality() {
        cv.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        cv.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.gas(gasAmount);
            }
        });

        cv.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.brake(gasAmount);
            }
        });

        cv.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOn();
            }
        });

        cv.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOff();
            }
        });

        cv.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.liftBed();
            }
        });

        cv.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.lowerBed();
            }
        });

        cv.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.startEngine();
            }
        });

        cv.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.stopEngine();
            }
        });

        cv.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cm.cars.size() < 10){
                    cm.addRandomCar();
                }else{
                    System.out.println("Only 10 cars allowed");
                }
            }
        });

        cv.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cm.cars.size() == 0){
                    System.out.println("No cars exist");
                }
                else
                {
                    cm.removeCar();
                }
            }
        });
    }
}
