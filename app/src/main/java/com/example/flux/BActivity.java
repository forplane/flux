package com.example.flux;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yidont.flux.Flux;
import com.yidont.flux.FluxOpe;
import com.yidont.flux.NormalActionCreator;

import rx.functions.Action1;


public class BActivity extends Activity{

    private TextView ttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttt=(TextView)findViewById(R.id.send);
        Flux.getDefault().register(this);
        ttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NormalActionCreator.get().post(BActivity.class.getCanonicalName(),ddd,"234234");
            }
        });
        findViewById(R.id.send1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                RxBus.getDefault().post("Main",111,"11111");
            }
        });



        Action1 a1=new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("11111");
            }
        };

        Action1 a2=new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("22222");
            }
        };

        RxBus.getDefault().toObservable("Main",111,String.class).subscribe(a1);


        RxBus.getDefault().toObservable("Main",222,String.class).subscribe(a2);
    }



    private final int ddd=222;
    @FluxOpe(ope = ddd)
    public void opeOne(Object event){
        System.out.println(event.toString());
    }

    @FluxOpe(ope=FluxOpe.DEFOPE,def = true)
    public void opeDef(Object event){
        System.out.println(event.toString());
    }
}
