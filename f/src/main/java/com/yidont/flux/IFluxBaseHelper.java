package com.yidont.flux;


import android.content.Context;

public interface IFluxBaseHelper {

    /**
     * evenbus的事件回调，也是页面ui更新管理器
     */
    void onViewUpdate(Object event);

    /**
     * 被继承的Fragment必须初始化返回Store
     * @return
     */
    Store initStore();

    void initDependencies();

    Context getCtx();
}
