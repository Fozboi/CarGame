package src.Entities;

import java.util.ArrayList;

/**
 * workshop for cars, implements interface CanLoad
 * @param <T> type variable, describes the type of car the workshop accepts
 */
public class Workshop<T extends Car> implements ICanLoad<T> {
    int loadCapacity;
    private ArrayList<T> loadedCars;

    /**
     * initialises the load capacity of the Workshop, and creates an ArrayList with the size of this capacity
     */
    public Workshop(int loadCapacity){
        this.loadCapacity = loadCapacity;
        loadedCars = new ArrayList<>(loadCapacity);
    }

    /**
     * Loads a vehicle into the workshop if it is possible
     */
    public void loadObject(T obj){
        if(canLoadObject(obj)){
            loadedCars.add(obj);
        }
    }

    /**
     * Checks if it is possible to load a vehicle into the workshop by checking so the capacity is not exceeded
     */
    public boolean canLoadObject(T obj){
        return loadedCars.size() < loadCapacity;
    }

    /**
     * Unloads a vehicle out of the workshop if it is possible
     */
    public void unloadObject(T obj){
        loadedCars.remove(obj);
    }

    public ArrayList<T> getLoadedObjects(){
        return loadedCars;
    }
}
