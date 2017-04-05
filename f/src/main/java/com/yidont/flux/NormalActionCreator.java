package com.yidont.flux;



/**
 * Created by jp on 2016/11/3.<p>
 */

public class NormalActionCreator extends ActionsCreatorFactory {


    public static NormalActionCreator get(){
        return new NormalActionCreator(Dispatcher.get());
    }

    protected NormalActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }


    /**
     * 不需要携带数据，不需要操作类型；只适用于一个tag中，单一的post操作
     */
    public void post(String tag) {
        post(tag,Action.NOACTION_TYPE);
    }

    /**
     * 不需要携带数据，需要操作类型；适用于一个tag中，有2个以上的post操作
     */
    public void post(String tag,int opeType){
        NormalAction.NormalActionEntity builder = new NormalAction.NormalActionEntity();
        post(tag,opeType,builder);
    }


    public void post(String tag, Object object) {
        NormalAction.NormalDataActionEntity builder = new NormalAction.NormalDataActionEntity()
                .setObj(object);
        post(tag,Action.NOACTION_TYPE,builder);
    }


    /**
     * 携带数据，需要操作类型；适用于一个tag中，有2个以上的post操作
     * @param tag   标签
     * @param opeType  操作类型
     * @param object    携带数据
     */
    public void post(String tag, int opeType,Object object) {
        NormalAction.NormalDataActionEntity builder = new NormalAction.NormalDataActionEntity()
                .setObj(object);
        post(tag,opeType,builder);
    }


    private void post(String tag,int opeType,IActionEntityBuilder builder) {
        Action action = builder.buildWithTag(tag, opeType);
        actionsCreator.sendMessage(action);
    }
}
