package com.bb.rxjava.observable;

import com.bb.rxjava.observer.Observer;

/**
 * 装饰器类
 * @param <T>
 */
public abstract class ObservableWithUpstream<T> extends Observable<T> {
    protected final ObservableSource source;

    public ObservableWithUpstream(ObservableSource source) {
        this.source = source;
    }
}
