package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

/**
 * CarTest tests methods in Car to make sure they're all working
 */

public class ScaniaTest {
    public Point position = new Point(200,200);
    Scania testScania;
    public int dir = 0;

    @BeforeEach
    public void init(){
        testScania = new Scania();
    }

    @Test
    public void cantMoveWithTrailerDown(){
        testScania.setTrailerAngle(40);
        try{testScania.gas(1);
            fail("should have thrown exception");
        } catch (IllegalStateException expectedException){ }
    }

    @Test
    public void cantChangeTrailerAngleWhileMoving(){
        testScania.gas(1);
        try {
            testScania.setTrailerAngle(40);
            fail("should have thrown exception");
        } catch (IllegalStateException expectedException){}
    }
}
