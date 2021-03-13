package com.bb.rxjava.observer;

public abstract class ObserverWithDownstream<T> implements Observer<T> {
    protected final Observer<T> actual;//原下游对象

    protected ObserverWithDownstream(Observer<T> actual) {
        this.actual = actual;
    }
}
