package dev.notcacha.inferius.response;

import dev.notcacha.inferius.response.state.ResponseStatus;

import java.util.Optional;
import java.util.function.Consumer;

public class SimpleResponse<T> implements Response<T> {

    private final ResponseStatus state;
    private final T response;

    public SimpleResponse(ResponseStatus state, T response) {
        this.state = state;
        this.response = response;
    }

    @Override
    public ResponseStatus getState() {
        return this.state;
    }

    @Override
    public Optional<T> getResponse() {
        return Optional.ofNullable(this.response);
    }

    @Override
    public void ifSuccessful(Consumer<? super T> consumer) {
        if (isSuccessful()) {
            consumer.accept(response);
        }
    }
}
