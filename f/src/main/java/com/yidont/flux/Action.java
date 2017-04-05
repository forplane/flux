package com.yidont.flux;


public abstract class Action<T extends IActionEntityBuilder> {

    public static final int NOACTION_TYPE=-963258;


    public final String tag;
    public final T data;
    /**
     * 这个action锁定应操作的是什么操作
     */
    public final int opeType;



    protected Action(String tag, int opeType, T data) {
        this.tag = tag;
        this.opeType =opeType;
        this.data = data;
    }
}
