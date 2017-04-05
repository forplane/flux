package com.yidont.flux;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Flux的Dispatcher模块
 */
public class Dispatcher {

    public final String TAG=Dispatcher.class.getSimpleName();

    private static Dispatcher instance;

    private volatile ConcurrentHashMap<String, Store> storeHash = new ConcurrentHashMap<>();
    public static Dispatcher get() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    Dispatcher() {}

    public void register(Object subscriber, final Store store) {

        if(store!=null){
            store.register(subscriber);
            storeHash.put(subscriber.getClass().getCanonicalName(), store);
        }
    }

    public void unregister(Object subscriber, final Store store) {
        if(store!=null){
            store.unRegister(subscriber);
            storeHash.remove(subscriber.getClass().getCanonicalName(),store);
        }
    }

    public void dispatch(Action action) {
        post(action);
    }



    private void post(final Action action) {
        Store store = storeHash.get(action.tag);
        if (store != null) {
            store.onAction(action);
        }
    }
}
