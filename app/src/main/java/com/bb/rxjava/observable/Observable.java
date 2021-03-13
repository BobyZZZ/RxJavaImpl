package com.bb.rxjava.observable;

import com.bb.rxjava.observer.Observer;
import com.bb.rxjava.operate.Function;
import com.bb.rxjava.operate.MapObservable;
import com.bb.rxjava.operate.ObserveOnObservable;
import com.bb.rxjava.operate.SubscribeOnObservable;

public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public static <T> Observable<T> create(ObservableOnSubscriber<T> onSubscriber) {
        return new ObservableCreator<>(onSubscriber);
    }

    /**
     * 转换
     * @param function
     * @param <R>
     * @return
     */
    public <R> Observable<R> map(Function<T, R> function) {
        return new MapObservable(this, function);
    }

    /**
     * 切换到子线程
     * @return
     */
    public Observable<T> subscribeOn() {
        return new SubscribeOnObservable<>(this);
    }

    /**
     * 切换到ui线程
     * @return
     */
    public Observable<T> observeOn() {
        return new ObserveOnObservable<>(this);
    }
}
