package com.wasp.songapp.repositories.base;

import com.wasp.songapp.models.Song;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {


    void add(T item) throws IOException;

    void delete(int id) throws IOException;

    T update(T item, int id) throws IOException;

    T getById(int id) throws IOException;

    List<T> getAll() throws IOException;


}