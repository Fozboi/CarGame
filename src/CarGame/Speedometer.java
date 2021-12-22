package src.CarGame;

import src.Entities.Car;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ConcurrentModificationException;

public class Speedometer extends JLabel implements ModelObserver{
    private final CarListHolder carListHolder;

    public Speedometer(CarListHolder cl){
        this.carListHolder = cl;
        setText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText();
    }

    private static final DecimalFormat df = new DecimalFormat("0.0");

    //returns a text label with the car model followed by the current speed
    private void setText(){
        try{
            String labelText = "";

            for(Car car : carListHolder.getCarList()){
                labelText = labelText + car.getModelName() + " : " + df.format(car.getCurrentSpeed()) + "     |    ";
            }

            this.setText(labelText);
        }catch(ConcurrentModificationException e){System.out.println("Stopped that weird bug again");}
        //sometimes when launching the program the exception above is thrown...

    }

    @Override
    public void update() {
        repaint();
    }
}
