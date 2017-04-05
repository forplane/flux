package com.example.flux;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.yidont.flux.NormalActionCreator;


/**
 * Created by jpmac on 2017/4/5.
 *
 */

public class StoreServer extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    int num=1;

    @Override
    public void onCreate() {
        super.onCreate();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num>0) {
                    System.out.println("=========");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (num == 2) {
                        NormalActionCreator.get().post(MainActivity.class.getCanonicalName());
                    }
                }
            }
        }).start();
    }
}
