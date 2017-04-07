package com.example.flux;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.TextView;

import com.yidont.flux.Dispatcher;
import com.yidont.flux.FluxOpe;
import com.yidont.flux.IFluxBaseHelper;
import com.yidont.flux.NormalActionCreator;
import com.yidont.flux.NormalStore;
import com.yidont.flux.Store;


public class MainActivity extends Activity implements IFluxBaseHelper{

    private TextView ttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        ttt=(TextView)findViewById(R.id.send);
        ttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NormalActionCreator.get().post(MainActivity.class.getCanonicalName());
                NormalActionCreator.get().post(MainActivity.class.getCanonicalName(),222);
                NormalActionCreator.get().post(MainActivity.class.getCanonicalName(),333,"myObj");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        NormalActionCreator.get().post(MainActivity.class.getCanonicalName());
                    }
                }).start();


            }
        });
        findViewById(R.id.send1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                }).start();
                NormalActionCreator.get().post(MainActivity.class.getCanonicalName(),ddd);

            }
        });

//        Intent tent = new Intent(this, StoreServer.class);
//        startService(tent);

    }

    private final int ddd=222;
    @FluxOpe(ope = ddd)
    public void opeOne(Object event){
        System.out.println(event.toString());
    }



    @Override
    public void onViewUpdate(Object event) {
        try {
            ttt.setText("234234");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Store initStore() {
        return new NormalStore();
    }

    @Override
    public void initDependencies() {

    }

    @Override
    public Context getCtx() {
        return this;
    }
    private Store store;


    @Override
    protected void onStart() {
        super.onStart();
        if (store==null) {
            store = initStore();
            if (store != null) {
                Dispatcher.get().register(this, store);
                store.onStart();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(store!=null){
            Dispatcher.get().unregister(this, store);
        }
//        Intent tent = new Intent(this, StoreServer.class);
//        stopService(tent);
    }
}
