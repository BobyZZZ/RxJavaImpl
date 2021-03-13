package com.bb.rxjava.observable;

import com.bb.rxjava.observer.Observer;

public class Emitter<T> implements Observer<T>{
    private final Observer<T> mObserver;

    public Emitter(Observer<T> observer) {
        mObserver = observer;
    }

    public void onSubscribe() {
        mObserver.onSubscribe();
    }

    public void onNext(T t) {
        mObserver.onNext(t);
    }

    public void onError(Throwable e) {
        mObserver.onError(e);
    }

    public void onComplete() {
        mObserver.onComplete();
    }
}
