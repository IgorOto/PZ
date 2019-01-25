package com.igi.popup;

import com.igi.lang.ActiveLang;
import com.igi.lang.LangValue;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public class Popup {

    public static void invalidCredentials() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ActiveLang.getValue(LangValue.POPUP_INVALID_CREDENTIALS_HEADER));
        alert.setHeaderText(ActiveLang.getValue(LangValue.POPUP_INVALID_CREDENTIALS_HEADER));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void loggedIn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ActiveLang.getValue(LangValue.LOGGEDIN));
        alert.setHeaderText(ActiveLang.getValue(LangValue.LOGGEDIN));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void success(String content) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void failure(String content) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }
}
