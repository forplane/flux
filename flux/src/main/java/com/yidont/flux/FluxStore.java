package com.yidont.flux;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jpmac on 2017/4/12.
 *
 */

public class FluxStore extends NormalStore {

    private Method defOpeMethod;
    private HashMap<Integer, Method> opeHashMap = new HashMap<>();
    public FluxStore(List<Method> methods) {
        super();
        for (Method method : methods) {
            FluxOpe annotation = method.getAnnotation(FluxOpe.class);
            if (annotation != null) {
                boolean def = annotation.def();
                if (def) {
                    defOpeMethod=method;
                }else{
                    opeHashMap.put(annotation.ope(), method);
                }
            }
        }
    }

    //主要用于子线程post作用
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            sendPost((StoreChangeEvent) msg.obj);
        }
    };

    @Override
    protected void emitStoreChange(StoreChangeEvent storeChangeEvent) {
        //通知订阅者更新，并携带数据
        if (subscriber != null) {
            Looper otherLooper = Looper.myLooper();
            if (Looper.myLooper() == null) {
                Message msg = Message.obtain(mHandler);
                msg.obj=storeChangeEvent;
                mHandler.sendMessage(msg);
            }else if( Looper.getMainLooper() == otherLooper){
                sendPost(storeChangeEvent);
            }
        }
    }

    private void sendPost(StoreChangeEvent storeChangeEvent){
        try {
            //没有携带opeType的情况
            if (storeChangeEvent.opeType == Action.NOACTION_TYPE) {
                defOpeMethod.invoke(subscriber, storeChangeEvent);
            } else {
                Method me = opeHashMap.get(storeChangeEvent.opeType);
                me.invoke(subscriber, storeChangeEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
