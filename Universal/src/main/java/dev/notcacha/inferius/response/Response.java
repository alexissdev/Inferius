package dev.notcacha.inferius.response;

import dev.notcacha.inferius.response.state.ResponseStatus;

import java.util.Optional;
import java.util.function.Consumer;

public interface Response<T> {

    ResponseStatus getState();

    Optional<T> getResponse();

    default boolean isSuccessful() {
        return getState() == ResponseStatus.SUCCESS;
    }

    void ifSuccessful(Consumer<? super T> consumer);

}
