package com.shadrin_like_you.gb_note.domain;

public interface Callback<T> {

    void onSuccess(T data);

    void onError(Throwable exception);
}
