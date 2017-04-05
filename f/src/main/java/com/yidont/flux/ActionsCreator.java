package com.yidont.flux;


/**
 * Flux的ActionCreator模块
 */
public class ActionsCreator {

    private static ActionsCreator instance;
    private final Dispatcher dispatcher;

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    public void sendMessage(Action action) {
        dispatcher.dispatch(action);
    }


}
