package com.igi.lang;

import lombok.Getter;

public enum LangValue {
    LOGIN_HEADER("login.header"),
    REGISTER_HEADER("register.header"),
    MENU_LANG_HEADER("menu.lang.header"),
    POPUP_INVALID_CREDENTIALS_HEADER("popup.invalid_credentials_title"),


    ROOM("room"), 
    LOGIN_BUTTON("login.button"), 
    LOGIN_LABEL("login.label"), 
    LOGIN_PASSWORD_LABEL("login.password.label"), 
    REGISTER_LABEL("register.label"), 
    REGISTER_USERNAME("login.label"), 
    PASSWORD("password"), 
    PASS_CONFIRM("password.confirm"), REGISTER_BUTTON("register.button"), ADD_TITLE("admin.title.add"),
    MOVIES("movies"),
    ADD_TITLE_BTN("movies.add_title_btn"),
    ADD_SHOWING("add_shwing"), MOVIE("movie"),
    DATE("date"), START_TIME("start.time"),
    END_TIME("end.time"),
    FROM("from"), TO("to"),
    SEARCH("search"),
    SHOWINGS("showings"),
    MAKE_RESERVATION("make_reservation"),
    POLISH("polish"),
    ENGLISH("english"),
    SKINS("skins"),
    SKIN1("skin1"),
    SKIN2("skin2"),
    SKIN3("skin3"),
    FIND("find"),
    MY_RES("my_res"),
    USER("user"),
    ADMIN_PANEL("admin.panel"),
    SUCCESS("success"),
    FAILURE("failure"),
    REGISTERED("registered"), NOT_REGISTERED("not_registered"), TICKET_NORMAL("ticket.normal"), TICKET_DISCOUNT("ticket.discount"), LOGGEDIN("popup.loggedin");

    @Getter
    private String value;

    LangValue(String value) {
        this.value = value;
    }
}