package src;

import java.awt.*;
/**
 * klassen Saab95 är en subklass til Car och innehåller värden och metoder för en
 * specifik biltyp.
 */
public class Saab95 extends Car{

    private boolean turboOn;
    
    public Saab95(){
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
	    setTurboOff();
        setModelName("Saab95");
        stopEngine();
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
