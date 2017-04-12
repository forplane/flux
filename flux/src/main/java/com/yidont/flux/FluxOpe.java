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
    /**
     * 操作符默认方法的ope标签值
     */
    int DEFOPE=9999;

    /**
     * @return 操作标签
     */
    int ope();

    /**
     * @return 是否为操作默认（既是不指定ope），默认为false
     */
    boolean def() default false;
}
