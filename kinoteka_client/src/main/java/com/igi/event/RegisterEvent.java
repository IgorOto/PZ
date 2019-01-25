package com.igi.event;

import lombok.Getter;

@Getter
public class RegisterEvent {
    private boolean successful;

    public RegisterEvent(boolean successful) {
        this.successful = successful;
    }
}
