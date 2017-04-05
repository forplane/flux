package com.yidont.flux;


/**
 * Created by jp on 2016/11/3.</br>
 * 此类搭配{@link NormalStore}使用
 */

public class NormalAction extends INormalAction<IActionEntityBuilder> {
    protected NormalAction(String tag,int type, int nortype,IActionEntityBuilder data) {
        super(tag,type,nortype, data);
    }





    /**
     * 一般Action的Entity
     */
    protected static class NormalActionEntity implements IActionEntityBuilder{

        @Override
        public Action buildWithTag(String tag,int type) {
            return new NormalAction(tag,type,INormalAction.TYPE_NORMAL_NOTHING,this);
        }
    }

    /**
     * 一般Action需要携带数据的Entity
     */
    protected static class NormalDataActionEntity implements IActionEntityBuilder{

        public Object obj;
        public NormalDataActionEntity setObj(Object obj) {
            this.obj = obj;
            return this;
        }

        @Override
        public Action buildWithTag(String tag,int opeType) {
            return new NormalAction(tag,opeType,INormalAction.TYPE_NORMAL_OBJ,this);
        }
    }

}
