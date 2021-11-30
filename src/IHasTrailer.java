package src;

/**
 * Creates the interface (requirements) for having a trailer:
 * method for defining the angle of the trailer
 * method for returning the angle of the trailer
 * method for knowing if the trailer is up (or not)
 * method for knowing if the trailer is down (or not)
 * method for setting the trailer up (min angle)
 * method for setting the trailer down (max angle)
 */
public interface IHasTrailer {
    void setTrailerAngle(double angle);
    double getTrailerAngle();
    boolean trailerIsUp();
    boolean trailerIsDown();
    void setTrailerUp();
    void setTrailerDown();
}
