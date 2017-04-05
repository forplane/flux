package com.yidont.flux;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jpmac on 2017/4/5.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取
@Target({ElementType.METHOD})//作用到方法上面
public @interface FluxOpe {
    int ope();
}
