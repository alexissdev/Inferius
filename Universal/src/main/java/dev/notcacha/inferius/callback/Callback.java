package dev.notcacha.inferius.callback;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Callback<T> {

    void call(T object);

    default void handleException(Throwable throwable){
        Logger.getGlobal().log(Level.SEVERE, "Error executing callback.", throwable);
    }
}
