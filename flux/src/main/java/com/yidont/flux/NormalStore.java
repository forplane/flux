package com.yidont.flux;


/**
 * Created by jp on 2016/11/3.<p>
 * 此类搭配{@link NormalAction}使用
 */

public class NormalStore extends Store {


    @Override
    public StoreChangeEvent changeEvent(String tag,int opeType) {
        return new NormalStoreEvent(tag,opeType);
    }

    @Override
    public void onAction(Action action) {
        onNormalAction((INormalAction) action);
    }

    public void onNormalAction(INormalAction action) {
        int nortype = action.nortype;//这个的类型是来区别是NormalStore需不需要携带数据的
        StoreChangeEvent event = null;
        if (nortype == INormalAction.TYPE_NORMAL_NOTHING) {
            event = new NormalStoreEvent(action.tag,action.opeType);
        } else if (nortype == INormalAction.TYPE_NORMAL_OBJ) {
            NormalAction.NormalDataActionEntity entity = (NormalAction.NormalDataActionEntity) action.data;
            event = new NormalDataStoreEvent(action.tag,action.opeType,entity.obj);
        }
        emitStoreChange(event);

    }

    /**
     * 不需要携带数据的StoreEvent
     */
    public class NormalStoreEvent extends StoreChangeEvent {
        public NormalStoreEvent(String tag,int opeType) {
            super(tag,opeType);
        }
    }


    /**
     * 跟NormalStoreEvent的区别在于需要携带数据
     */
    public class NormalDataStoreEvent extends NormalStoreEvent {
        private Object obj;
        public NormalDataStoreEvent(String tag,int opeType,Object obj) {
            super(tag,opeType);
            this.obj=obj;
        }
        public Object getObj() {
            return obj;
        }
    }
}
