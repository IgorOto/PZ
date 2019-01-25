package com.igi.controller;

import com.google.common.eventbus.Subscribe;
import com.igi.event.GlobalEventBus;
import com.igi.event.LangEvent;
import com.igi.event.RegisterEvent;
import com.igi.lang.ActiveLang;
import com.igi.lang.LangValue;
import com.igi.popup.Popup;
import com.igi.repository.Repository;
import com.igi.repository.model.UserDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    @FXML
    public Label usernameLoginlabel;
    public Label passwordLoginLabel;
    public Label registerLabel;
    public Label usernameRegisterLabel;
    public Label passwordRegisterLabel;
    public Label passwordRegisterConfirmLabel;

    @FXML
    private Label loginHeader;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameLoginInput;

    @FXML
    private TextField passwordLoginInput;

    @FXML
    private TextField passwordRegisterConfirmInput;
    @FXML
    private TextField passwordRegisterInput;

    @FXML
    private TextField usernameRegisterInput;

    private GlobalEventBus bus;

    private Repository repository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus = GlobalEventBus.getBus();
        bus.register(this);
        repository = Repository.get();
        refreshText();
    }

    private void refreshText() {
        loginHeader.setText(ActiveLang.getValue(LangValue.LOGIN_HEADER));
        loginButton.setText(ActiveLang.getValue(LangValue.LOGIN_BUTTON));
        usernameLoginlabel.setText(ActiveLang.getValue(LangValue.LOGIN_LABEL));
        passwordLoginLabel.setText(ActiveLang.getValue(LangValue.LOGIN_PASSWORD_LABEL));
        registerLabel.setText(ActiveLang.getValue(LangValue.REGISTER_LABEL));
        usernameRegisterLabel.setText(ActiveLang.getValue(LangValue.REGISTER_USERNAME));
        passwordRegisterLabel.setText(ActiveLang.getValue(LangValue.PASSWORD));
        passwordRegisterConfirmLabel.setText(ActiveLang.getValue(LangValue.PASS_CONFIRM));
        registerButton.setText(ActiveLang.getValue(LangValue.REGISTER_BUTTON));
    }

    @Subscribe
    public void onLangChange(LangEvent event) {
       refreshText();
    }

    @Subscribe
    public void onRegister(RegisterEvent event) {
        if(event.isSuccessful()){
            Popup.success(ActiveLang.getValue(LangValue.REGISTERED));
            usernameRegisterInput.setText("");
            passwordRegisterInput.setText("");
            passwordRegisterConfirmInput.setText("");
        }
        else Popup.failure(ActiveLang.getValue(LangValue.NOT_REGISTERED));
    }


    public void login() {
        String username = usernameLoginInput.getText();
        String password = passwordLoginInput.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Popup.invalidCredentials();
            return;
        }

        repository.logIn(username, password);
    }

    public void onRegister() {
        if (!usernameRegisterInput.getText().isEmpty() &&
                !passwordRegisterInput.getText().isEmpty() &&
                !passwordRegisterConfirmInput.getText().isEmpty() &&
                passwordRegisterInput.getText().equals(passwordRegisterConfirmInput.getText())) {
                repository.register(UserDto.builder()
                        .username(usernameRegisterInput.getText())
                        .password(passwordRegisterInput.getText())
                        .build());
        }
    }
}
