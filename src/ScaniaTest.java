package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

/**
 * ScaniaTest tests methods in Scania to make sure they're all working
 */
public class ScaniaTest {
    public Point position = new Point(200,200);
    Scania testScania;
    public int dir = 0;

    @BeforeEach
    public void init(){
        testScania = new Scania();
    }

    /**
     * controls so the truck does not move when the trailer is set down
     */
    @Test
    public void cantMoveWithTrailerDown(){
        testScania.setTrailerAngle(40);
        try{testScania.gas(1);
            fail("should have thrown exception");
        } catch (IllegalStateException expectedException){ }
    }

    /**
     * controls so the angle of the trailer cannot be changed when the truck is moving
     */
    @Test
    public void cantChangeTrailerAngleWhileMoving(){
        testScania.gas(1);
        try {
            testScania.setTrailerAngle(40);
            fail("should have thrown exception");
        } catch (IllegalStateException expectedException){}
    }
}
