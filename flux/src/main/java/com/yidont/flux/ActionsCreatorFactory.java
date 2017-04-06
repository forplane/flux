package com.yidont.flux;


/**
 * ActionCreator工厂
 */
public abstract class ActionsCreatorFactory {

   protected ActionsCreator actionsCreator;

    public ActionsCreatorFactory(Dispatcher dispatcher){
        actionsCreator=ActionsCreator.get(dispatcher);
    }
}
