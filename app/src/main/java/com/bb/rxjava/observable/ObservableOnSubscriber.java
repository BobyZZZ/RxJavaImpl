package com.bb.rxjava.observable;

public interface ObservableOnSubscriber<T> {
    void subscribe(Emitter<T> emitter);
}
