package com.yidont.flux;

/**
 * Created by jp on 2016/11/3.</br>
 */

public abstract class INormalAction<T extends IActionEntityBuilder> extends Action<T> {

    public static final int TYPE_NORMAL_NOTHING=0; //一般post，不需要携带数据
    public static final int TYPE_NORMAL_OBJ=TYPE_NORMAL_NOTHING+1;//一般post，需要携带数据

    /**
     * 区别于父类的type字段：这个字段只是针对NormalAction而使用的<br/>
     * 主要是用来区别这个action是否需要携带数据TYPE_NORMAL_NOTHING 以及TYPE_NORMAL_OBJ
     */
    public final int nortype;

    /**
     * @param tag
     * @param opeType  具体操作类型
     * @param nortype   是否携带数据类型 TYPE_NORMAL_NOTHING或者TYPE_NORMAL_OBJ
     * @param data
     */
    protected INormalAction(String tag, int opeType, int nortype, T data) {
        super(tag, opeType,data);
        this.nortype=nortype;
    }

}
