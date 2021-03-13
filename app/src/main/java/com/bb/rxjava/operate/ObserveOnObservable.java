package com.bb.rxjava.operate;

import android.os.Handler;
import android.os.Looper;

import com.bb.rxjava.observable.ObservableSource;
import com.bb.rxjava.observable.ObservableWithUpstream;
import com.bb.rxjava.observer.Observer;
import com.bb.rxjava.observer.ObserverWithDownstream;

public class ObserveOnObservable<T> extends ObservableWithUpstream<T> {

    public ObserveOnObservable(ObservableSource source) {
        super(source);
    }

    @Override
    protected void subscribeActual(final Observer<T> observer) {
        source.subscribe(new ObserveOnObserver(observer));
    }

    static final class ObserveOnObserver<T> extends ObserverWithDownstream<T> {
        private final Handler mMainHandler;

        protected ObserveOnObserver(Observer<T> downstream) {
            super(downstream);
            mMainHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(final T t) {
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });
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
