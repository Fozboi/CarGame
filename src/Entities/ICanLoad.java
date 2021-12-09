package src.Entities;

/**
 * Creates the interface (requirements) for vehicles (or workshops) that can load things:
 * method for loading the object
 * method for checking if it is possible to load the object
 * method for unloading the object
 */
interface ICanLoad<T> {
    void loadObject(T object);
    boolean canLoadObject(T object);
    void unloadObject(T object);
}
