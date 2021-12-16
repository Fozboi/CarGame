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

    private int gasAmount = 0;

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cm = new CarModel();

        cc.cm.addCar(new Volvo240());
        cc.cm.addCar(new Saab95());
        cc.cm.addCar(new Scania());

        cc.initFunctionality();
    }

    private void initFunctionality() {
        cm.frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        cm.frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.gas(gasAmount);
            }
        });

        cm.frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.brake(gasAmount);
            }
        });

        cm.frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOn();
            }
        });

        cm.frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOff();
            }
        });

        cm.frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.liftBed();
            }
        });

        cm.frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.lowerBed();
            }
        });

        cm.frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.startEngine();
            }
        });

        cm.frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.stopEngine();
            }
        });

        cm.frame.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cm.cars.size() < 10){
                    cm.addRandomCar();
                }else{
                    System.out.println("Only 10 cars allowed");
                }
            }
        });

        cm.frame.removeCarButton.addActionListener(new ActionListener() {
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
