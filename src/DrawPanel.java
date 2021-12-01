package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    CarController carController;
    HashMap<Car,BufferedImage> carImageMap = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController cc) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.carController = cc;

    }

    public void updateCarImageMap(){
        for(Car car : carController.getCars()){
            if (!carImageMap.containsKey(car)){
                carImageMap.put(car,findImageFromFile(car));
            }
        }

        ArrayList<Car> abundantCars = new ArrayList<>();

        carImageMap.forEach((car, bufferedImage) -> {
            if(!carController.getCars().contains(car)){abundantCars.add(car);}});

        for(Car car : abundantCars){carImageMap.remove(car);}
    }

    public BufferedImage findImageFromFile(Car car){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("pics/" + car.getModelName() + ".jpg"));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return image;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        updateCarImageMap();
        super.paintComponent(g);
        for(Car car : carController.getCars()){
            g.drawImage(carImageMap.get(car), (int)car.getPosition().getX(), (int)car.getPosition().getY(), null);
        }
    }
}
