package src.CarGame;

import src.Entities.Car;

import java.util.ArrayList;

public class CarListObserver {
    ArrayList<Car> cars = new ArrayList<>();

    public void updateCarList(ArrayList<Car> cars){
        this.cars = cars;
    }

    public ArrayList<Car> getCarList(){
        return cars;
    }
}
