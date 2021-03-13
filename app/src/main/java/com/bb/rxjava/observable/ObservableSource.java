package com.bb.rxjava.observable;

import com.bb.rxjava.observer.Observer;

import java.util.Observable;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observable);
}
