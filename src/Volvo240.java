package src;

import java.awt.*;

public class Volvo240 extends Car{

    public final static double trimFactor = 1.25;
    
    private Volvo240(){
        setNrDoors(4);
        setColor(Color.black);
        setEnginePower(100);
        setModelName("Volvo240");
        stopEngine();
    }

    double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
