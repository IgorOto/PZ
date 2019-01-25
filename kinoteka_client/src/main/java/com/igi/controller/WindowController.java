package com.igi.controller;

import com.aquafx_project.AquaFx;
import com.google.common.eventbus.Subscribe;
import com.igi.event.GlobalEventBus;
import com.igi.event.LangEvent;
import com.igi.event.LoginEvent;
import com.igi.lang.ActiveLang;
import com.igi.lang.Lang;
import com.igi.lang.LangValue;
import com.igi.popup.Popup;
import com.igi.repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    public MenuItem langPl;
    public MenuItem langEn;
    public Menu skinMenu;
    public MenuItem skin1;
    public MenuItem skin2;
    public MenuItem skin3;
    public MenuItem findShowingsPanel;
    public MenuItem myReservationsPanel;
    public MenuItem moviesAdminButton;
    @FXML
    private javafx.scene.control.Menu langMenu;
    @FXML
    private javafx.scene.control.Menu showingsMenu;
    @FXML
    MenuBar menu;
    @FXML
    BorderPane window;
    @FXML
    Menu userMenu;
    @FXML
    Menu adminMenu;

    private Repository repository;

    private GlobalEventBus bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus = GlobalEventBus.getBus();
        bus.register(this);
        repository = Repository.get();
        refreshLang();
    }

    public void setLangPl(){
        ActiveLang.setLang(Lang.PL.getLang());
        refreshLang();
    }

    public void setLangEn(){
        ActiveLang.setLang(Lang.EN.getLang());
        refreshLang();
    }

    private void refreshLang() {
        bus.post(new LangEvent());
        langMenu.setText(ActiveLang.getValue(LangValue.MENU_LANG_HEADER));
        langPl.setText(ActiveLang.getValue(LangValue.POLISH));
        langEn.setText(ActiveLang.getValue(LangValue.ENGLISH));
        skinMenu.setText(ActiveLang.getValue(LangValue.SKINS));
        skin1.setText(ActiveLang.getValue(LangValue.SKIN1));
        skin2.setText(ActiveLang.getValue(LangValue.SKIN2));
        skin3.setText(ActiveLang.getValue(LangValue.SKIN3));
        showingsMenu.setText(ActiveLang.getValue(LangValue.SHOWINGS));
        findShowingsPanel.setText(ActiveLang.getValue(LangValue.FIND));
        myReservationsPanel.setText(ActiveLang.getValue(LangValue.MY_RES));
        userMenu.setText(ActiveLang.getValue(LangValue.USER));
        moviesAdminButton.setText(ActiveLang.getValue(LangValue.ADMIN_PANEL));
    }

    public void setSkin1() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void setSkin2() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void setSkin3() {
        AquaFx.style();
    }

    public void logout(){
        adminMenu.setDisable(true);
        repository.logout();
        showingsMenu.setDisable(true);
        userMenu.setDisable(true);
        URL authURL = getClass()
                .getClassLoader()
                .getResource("fxml/auth.fxml");
        try {
            Parent root = FXMLLoader.load(authURL);
            window.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onLogIn(LoginEvent event){
        if(event.isSuccessful()){
            showingsMenu.setDisable(false);
            userMenu.setDisable(false);
            if(event.getUserAuth().getRoleName().equals("ROLE_ADMIN")) adminMenu.setDisable(false);
            URL showingsURL = getClass()
                    .getClassLoader()
                    .getResource("fxml/showings.fxml");
            try {
                Parent root = FXMLLoader.load(showingsURL);
                window.setCenter(root);
                Popup.loggedIn();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            showingsMenu.setDisable(true);
            Popup.invalidCredentials();
        }
    }

    public void showFindShowingsPanel(){
        URL showingsURL = getClass()
                .getClassLoader()
                .getResource("fxml/showings.fxml");
        try {
            Parent root = FXMLLoader.load(showingsURL);
            window.setCenter(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMyReservationsPanel(){
     
        URL url = getClass()
                .getClassLoader()
                .getResource("fxml/reservations.fxml");
        try {
            Parent root = FXMLLoader.load(url);
            window.setCenter(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdminPanel(){
       
        URL url = getClass()
                .getClassLoader()
                .getResource("fxml/admin.fxml");
        try {
            Parent root = FXMLLoader.load(url);
            window.setCenter(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
