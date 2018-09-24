package com.wasp.songapp.http.base;

import java.io.IOException;

public interface HttpRequester {

    String get(String url) throws IOException;

    void post(String url, String body) throws IOException;

    void delete(String url, int id) throws IOException;

    String update(String url,String body , int id) throws IOException;
}