package com.igi.event;

import com.igi.repository.model.UserAuth;
import lombok.Getter;

@Getter
public class LoginEvent {
    private boolean successful;
    private UserAuth userAuth;

    public LoginEvent(boolean successful, UserAuth userAuth) {
        this.successful = successful;
        this.userAuth = userAuth;
    }
}
