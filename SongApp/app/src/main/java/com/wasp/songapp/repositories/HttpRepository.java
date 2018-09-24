package com.wasp.songapp.repositories;

import com.wasp.songapp.http.base.HttpRequester;
import com.wasp.songapp.models.Song;
import com.wasp.songapp.parsers.base.JsonParser;
import com.wasp.songapp.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class HttpRepository<T> implements Repository<T> {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<T> mJsonParser;


    public HttpRepository(String serverUrl, HttpRequester httpRequester, JsonParser<T> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public void add(T item) throws IOException {

        String requestBody = mJsonParser.toJson(item);
        String postServerUrl = mServerUrl + "/new";
        mHttpRequester.post(postServerUrl, requestBody);
    }

    @Override
    public void delete(int id) throws IOException {
        String deleteServerUrl = mServerUrl + "/delete/" + id;

        mHttpRequester.delete(deleteServerUrl, id);

    }

    @Override
    public T update(T item, int id) throws IOException {
        String updateServerUrl = mServerUrl + "/update/" + id;

        String requestBody = mJsonParser.toJson(item);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody, id);


        return mJsonParser.fromJson(responseBody);

    }

    @Override
    public T getById(int id) throws IOException {

        String itemJson = mHttpRequester.get(mServerUrl + "/" + id);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<T> getAll() throws IOException {


        String itemsJson = mHttpRequester.get(mServerUrl + "/get");
        return mJsonParser.fromJsonArray(itemsJson);

    }
}
