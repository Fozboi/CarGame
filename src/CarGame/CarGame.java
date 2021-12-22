package src.CarGame;

import src.Entities.Saab95;
import src.Entities.Scania;
import src.Entities.Volvo240;

/**
 * Launches and initiates the game
 */

public class CarGame {
    public static void main(String[] args) {
        // Instance of controller class
        CarController cc = new CarController();

        cc.cm = new CarModel();

        cc.cm.addCar(new Volvo240());
        cc.cm.addCar(new Saab95());
        cc.cm.addCar(new Scania());

        cc.cm.addModelObserver(cc.cv.drawPanel);
        cc.cm.addModelObserver(cc.cv.speedometer);

        cc.initFunctionality();
    }
}
