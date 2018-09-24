package com.wasp.songappspring.utils;

public class IdGenerator {
    private int idGenerator = 0;


    public int getNextId() {
        return ++this.idGenerator;
    }
}
