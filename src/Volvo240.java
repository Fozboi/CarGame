package src;

import java.awt.*;
/**
 * klassen Volvo240 är en subklass til Car och innehåller värden och metoder för en
 * specifik biltyp.
 */
public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        setNrDoors(4);
        setColor(Color.black);
        setEnginePower(100);
        setModelName("Volvo240");
        stopEngine();
    }
    /**
     * a method that calculates a factor for the calculation of a veichle's acceleration
     * @return
     */
    @Override
    double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
