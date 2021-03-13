package com.bb.rxjava.observable;

import com.bb.rxjava.observer.Observer;

public class ObservableCreator<T> extends Observable<T> {
    private final ObservableOnSubscriber<T> source;

    public ObservableCreator(ObservableOnSubscriber<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        //订阅成功
        observer.onSubscribe();
        //发送事件
        Emitter<T> emitter = new Emitter<>(observer);
        source.subscribe(emitter);
    }
}
