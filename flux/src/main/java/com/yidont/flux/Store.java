package com.yidont.flux;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Flux的Store模块
 */
public abstract class Store {

    public static final int TYPE_NONET="nonet".hashCode()>>2 + "store".hashCode() <<2;//统一没有网络是的type类型，保证唯一性



    protected Object subscriber;

    protected Store() {

    }


    public void register(Object subscriber) {
        this.subscriber = subscriber;
    }

    public void unRegister(Object subscriber) {
        this.subscriber = null;
    }





    /**
     * 传入操作类型，然后出发主界面更新
     */
    protected abstract  void emitStoreChange(final StoreChangeEvent storeChangeEvent);


    /**
     * 传入操作类型，然后出发主界面更新
     *
     * @param tag
     */
    protected void emitStoreChange(String tag, int opeType) {
        //根据类型得到具体的Event对象
        emitStoreChange(changeEvent(tag, opeType));
    }

    protected abstract StoreChangeEvent changeEvent(String tag, int opeType);

    /**
     * 所有逻辑的处理，在实现类中可以简单想象成对应着一个Activity（View）的增删改查的处理
     *
     * @param action
     */
    public abstract void onAction(Action action);

    /**
     * 返回到view中的对象，在activity得到这个对象，通过operationtype来判断对应的操作来更新对应的ui
     */
    public static class StoreChangeEvent {
        public String tag;
        public int opeType;//操作类型

        public StoreChangeEvent(String tag, int opeType) {
            this.tag = tag;
            this.opeType = opeType;
        }

    }

    /**
     * 基类覆盖该方法可以在activity或者fragment的onstart回调
     */
    public void onStart() {

    }



}
