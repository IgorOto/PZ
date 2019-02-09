package com.kino.kino.lang;

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Lang {
    EN(new Locale("en", "EN")),
    PL(new Locale("pl", "PL"));

    @Getter
    Locale locale;

    Lang(Locale locale) {
        this.locale = locale;
    }

    public ResourceBundle getLang() {
        return ResourceBundle.getBundle("lang", locale, new UTF8Control());
    }
}
