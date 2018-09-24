package com.wasp.songapp.parsers;

import com.google.gson.Gson;
import com.wasp.songapp.parsers.base.JsonParser;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {


    private final Class<T> mklass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;

    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mklass = klass;
        mArrayKlass = arrayKlass;
        mGson = new Gson();
    }

    @Override
    public List<T> fromJsonArray(String jsonString) {
        T[] result = mGson.fromJson(jsonString, mArrayKlass);

        return Arrays.asList(result);
    }

    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mklass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }
}