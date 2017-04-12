package com.example.flux;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jpmac on 2017/4/7.
 *
 */

public class TTTT {
    public static final String TAG="";
    public static void main(String[] args) {
        //创建一个被观察者(发布者)
        Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1001);
                subscriber.onNext(1002);
                subscriber.onNext(1003);
                subscriber.onCompleted();
            }
        });

        //创建一个观察者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted.. ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "subscriber onError.. "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext.. integer:" + integer);
            }
        };

        //注册观察者(这个方法名看起来有点怪，还不如写成regisiterSubscriber(..)或者干脆addSubscriber(..))
        //注册后就会开始调用call()中的观察者执行的方法 onNext() onCompleted()等
        observable.subscribe(subscriber);
    }
}
