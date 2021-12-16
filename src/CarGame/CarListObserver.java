package src.CarGame;

import src.Entities.Car;

import java.util.ArrayList;

//holds a list of cars that are in the game, can return them to any class thar requests them
public class CarListObserver {
    ArrayList<Car> cars = new ArrayList<>();

    public void updateCarList(ArrayList<Car> cars){
        this.cars = cars;
    }

    public ArrayList<Car> getCarList(){
        return cars;
    }
}
