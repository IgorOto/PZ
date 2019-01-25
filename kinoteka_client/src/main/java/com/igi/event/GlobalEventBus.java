package com.igi.event;

import com.google.common.eventbus.EventBus;

public class GlobalEventBus {
    private static final GlobalEventBus INSTANCE = new GlobalEventBus();
    private EventBus eventBus;

    private GlobalEventBus() {
        eventBus = new EventBus();
    }

    public static GlobalEventBus getBus() {
        return INSTANCE;
    }

    public void register(Object listener) {
        eventBus.register(listener);
    }

    public void post(Object event) {
        eventBus.post(event);
    }
}
