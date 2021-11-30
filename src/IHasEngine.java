package src;

/**
 * Creates the interface (requirements) for having an engine:
 * method for starting the engine
 * method for stopping the engine
 * method for returning the engine power
 * method for defining the positive acceleration
 * method for defining the negative acceleration
 */
public interface IHasEngine {
    void startEngine();
    void stopEngine();
    double getEnginePower();
    void gas(double amount);
    void brake(double amount);
}
