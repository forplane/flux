package com.yidont.flux;

import java.io.Flushable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jpmac on 2017/4/12.
 *
 */

public class Flux {
    static volatile Flux defaultInstance;
    private static final Map<Class<?>, List<Method>> METHOD_CACHE = new ConcurrentHashMap<>();
    private static final Map<Class<?>, FluxStore> SUBSTORE = new ConcurrentHashMap<>();


    /**
     * Creates a new EventBus instance; each instance is a separate scope in which events are delivered. To use a
     * central bus, consider {@link #getDefault()}.
     */
    private Flux() {
    }

    /** Convenience singleton for apps using a process-wide Flux instance. */
    public static Flux getDefault() {
        if (defaultInstance == null) {
            synchronized (Flux.class) {
                if (defaultInstance == null) {
                    defaultInstance = new Flux();
                }
            }
        }
        return defaultInstance;
    }

    public void register(Object subscriber) {
        Class<?> aClass = subscriber.getClass();
        List<Method> methods = METHOD_CACHE.get(aClass);
        if (methods == null) {
            methods=findUsingReflection(aClass);
            METHOD_CACHE.put(aClass, methods);
        }

        FluxStore fluxStore = new FluxStore(methods);
        SUBSTORE.put(aClass, fluxStore);
        Dispatcher.get().register(subscriber, fluxStore);
    }

    public void unregister(Object subscriber) {
        Class<?> aClass = subscriber.getClass();
        FluxStore fluxStore = SUBSTORE.get(aClass);
        Dispatcher.get().unregister(subscriber,fluxStore);
    }

    private List<Method> findUsingReflection(Class<?> aClass){
        List<Method> methods = new ArrayList<>();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            FluxOpe annotation = method.getAnnotation(FluxOpe.class);
            if (annotation != null) {
                methods.add(method);
            }
        }
        return methods;
    }

}
