package com.igi.lang;

import lombok.Getter;
import lombok.Setter;

import java.util.ResourceBundle;

public class ActiveLang {

    @Getter
    @Setter
    private static ResourceBundle lang;

    protected ActiveLang() {
    }

    static {
        lang = Lang.EN.getLang();
    }

    public static String getValue(LangValue langValue) {
        return lang.getString(langValue.getValue());
    }
}
