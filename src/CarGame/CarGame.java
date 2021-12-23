package src.CarGame;

import src.Entities.Saab95;
import src.Entities.Scania;
import src.Entities.Volvo240;

/**
 * Launches and initiates the game
 */

public class CarGame {
    public static void main(String[] args) {
        CarListHolder cl = new CarListHolder();
        CarController cc = new CarController(cl);
        cc.cm = new CarModel(cl);

        cc.cm.addCar(new Volvo240());
        cc.cm.addCar(new Saab95());
        cc.cm.addCar(new Scania());
        cc.cm.addModelObserver(cc.cv);

        cc.initFunctionality();
    }
}
