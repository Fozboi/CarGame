package src;

interface CanLoad<T> {
    void loadObject(T object);
    boolean canLoadObject(T object);
    void unloadObject(T object);
}
