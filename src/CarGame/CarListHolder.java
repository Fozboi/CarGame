package src.CarGame;

import src.Entities.Car;

import java.util.ArrayList;

//holds a list of cars that are in the game, can return them to any class that requests them
public class CarListHolder {
    ArrayList<Car> cars = new ArrayList<>();

    public void updateCarList(ArrayList<Car> cars){
        this.cars = cars;
    }

    public ArrayList<Car> getCarList(){
        return cars;
    }
}
