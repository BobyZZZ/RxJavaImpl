package com.bb.rxjava.observer;

public interface Observer<T> {
    //订阅成功回调
    void onSubscribe();

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();
}
