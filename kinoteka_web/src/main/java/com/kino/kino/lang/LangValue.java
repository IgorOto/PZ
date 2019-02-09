package com.kino.kino.lang;

import lombok.Getter;

public enum LangValue {
    LOGIN("index.login"),
    REGISTER("index.register");

    @Getter
    private String value;

    LangValue(String value) {
        this.value = value;
    }
}
