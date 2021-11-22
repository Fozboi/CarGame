package src;

import java.util.ArrayList;

/**
 * workshop for cars, implements interface CanLoad
 * @param <T> type variable, describes the type of car the workshop accepts
 */
public class Workshop<T extends Car> implements CanLoad<T> {
    int loadCapacity;
    private ArrayList<T> loadedCars;

    public Workshop(int loadCapacity){
        this.loadCapacity = loadCapacity;
        loadedCars = new ArrayList<>(loadCapacity);
    }

    public void loadObject(T obj){
        if(canLoadObject(obj)){
            loadedCars.add(obj);
        }
    }

    public boolean canLoadObject(T obj){
        if(loadedCars.size() < loadCapacity){
            return true;
        }else return false;
    }

    public void unloadObject(T obj){
        loadedCars.remove(obj);
    }
}
