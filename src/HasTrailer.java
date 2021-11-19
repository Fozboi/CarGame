package src;

public interface HasTrailer {
    void setTrailerAngle(double angle);
    double getTrailerAngle();
    boolean trailerIsUp();
    boolean trailerIsDown();
    void setTrailerUp();
    void setTrailerDown();
}
