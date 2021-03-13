package com.bb.rxjava.operate;

import com.bb.rxjava.observable.Emitter;
import com.bb.rxjava.observable.ObservableSource;
import com.bb.rxjava.observable.ObservableWithUpstream;
import com.bb.rxjava.observer.Observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscribeOnObservable<T> extends ObservableWithUpstream<T> {

    private final ExecutorService mExecutors;

    public SubscribeOnObservable(ObservableSource source) {
        super(source);
        mExecutors = Executors.newCachedThreadPool();
    }

    @Override
    protected void subscribeActual(final Observer<T> observer) {
        mExecutors.submit(new Runnable() {
            @Override
            public void run() {
                source.subscribe(new Emitter(observer));
            }
        });
    }
}
