package com.bb.rxjava;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bb.rxjava.observable.Emitter;
import com.bb.rxjava.observable.Observable;
import com.bb.rxjava.observable.ObservableOnSubscriber;
import com.bb.rxjava.observer.Observer;
import com.bb.rxjava.operate.Function;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    String TAG = "ExampleInstrumentedTest";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.bb.rxjava", appContext.getPackageName());

        Observable.create(new ObservableOnSubscriber<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("hello world");
                emitter.onError(new IllegalAccessError("test onError"));
                emitter.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + ",map1!";
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + ",map2!";
            }
        }).subscribeOn()
                .observeOn()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        Log.d(TAG, "onSubscribe() called in: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext() called with: s = [" + s + "] in: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: e = [" + e + "] in: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete() called in: " + Thread.currentThread().getName());
                    }
                });
    }
}
