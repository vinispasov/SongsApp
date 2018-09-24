package com.wasp.songapp.http;

import com.wasp.songapp.http.base.HttpRequester;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpRequester implements HttpRequester {
    private final OkHttpClient client;

    public OkHttpHttpRequester() {

        client = new OkHttpClient();
    }

    @Override
    public String get(String url) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String body = response
                .body()
                .string();

        return body;
    }

    @Override
    public void post(String url, String bodyString) throws IOException {

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                bodyString);

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        client
                .newCall(request)
                .execute();

    }

    @Override
    public void delete(String url, int id) throws IOException {

        Request request = new Request.Builder()
                .delete()
                .url(url)
                .build();

        client
                .newCall(request)
                .execute();

    }

    @Override
    public String update(String url, String body, int id) throws IOException {

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                body);

        Request request = new Request.Builder()
                .put(requestBody)
                .url(url)
                .build();

        Response response = client
                .newCall(request)
                .execute();

        String responseBody = response
                .body()
                .string();

        return responseBody;
    }
}
