package dev.notcacha.inferius.response.async;

import dev.notcacha.inferius.callback.Callback;
import dev.notcacha.inferius.response.Response;

public interface AsyncResponse<T> {

    void callback(Callback<Response<T>> callback);
}
