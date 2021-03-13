package com.bb.rxjava.operate;

import com.bb.rxjava.observable.ObservableSource;
import com.bb.rxjava.observable.ObservableWithUpstream;
import com.bb.rxjava.observer.Observer;

public class MapObservable<T, R> extends ObservableWithUpstream<T> {
    private final Function<T, R> function;

    public MapObservable(ObservableSource<T> upstream, Function<T, R> function) {
        super(upstream);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        source.subscribe(new MapObserver(observer, function));
    }

    static final class MapObserver<T, R> implements Observer<T> {
        private final Observer<R> actual;
        private final Function<T, R> function;

        MapObserver(Observer<R> actual, Function<T, R> function) {
            this.actual = actual;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            R apply = function.apply(t);
            actual.onNext(apply);
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }

}
