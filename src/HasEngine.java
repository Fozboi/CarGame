package src;

public interface HasEngine {
    void startEngine();
    void stopEngine();
    double getEnginePower();
    void gas(double amount);
    void brake(double amount);
}
