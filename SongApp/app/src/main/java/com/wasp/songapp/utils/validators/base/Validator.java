package com.wasp.songapp.utils.validators.base;

public interface Validator<T> {
    boolean isItemValid(T item);
}
