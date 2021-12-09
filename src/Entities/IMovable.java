package src.Entities;

/**
 * Interface that declares methods for objects that can move
 */
public interface IMovable {
    /**
     * moves the object in the desired direction
     */
    void move();

    /**
     * turns the object to the left
     */
    void turnLeft();

    /**
     * turns the object to the right
     */
    void turnRight();
}
