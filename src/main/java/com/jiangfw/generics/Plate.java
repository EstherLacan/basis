package com.jiangfw.generics;

public class Plate<T> {

    private T item;

    public Plate(T t) {
        item = t;
    }


    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }


    public <V extends T> void setExtends(V t) {
        item = t;
    }

    public <V extends T> V getExtends() {
        return (V) item;
    }
}
