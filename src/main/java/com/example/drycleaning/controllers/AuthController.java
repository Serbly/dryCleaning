package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.User;
import com.example.drycleaning.repository.PartnerRepository;
import com.example.drycleaning.repository.UserRepository;
import com.example.drycleaning.util.EditablePartner;
import com.example.drycleaning.util.GlobalRole;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;

    private final FXMLConfiguration configuration;

    @FXML
    private Label error;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onBtnClick() throws IOException {
        boolean isValid = authUser(loginField.getText(), passwordField.getText());

        User user = isValid ? repository.findByUsername(loginField.getText()).orElseThrow() : null;

        if (user == null) {
            error.setText("Login or password is incorrect");
        }
        else {
            GlobalRole.setRole(user.getRole().getRoleName());

            if (GlobalRole.getRole().equals("Администратор")) {
                Stage users = configuration.usersPageFXMLLoader();
                users.show();
                closePage();
                return;
            }

            if (GlobalRole.getRole().equals("Партнёр")) {
                Stage services = configuration.servicesPageFXMLLoader();
                services.show();
                closePage();
                return;
            }

            Stage mainPage = configuration.mainPageFXMLLoader();
            mainPage.show();
            closePage();
        }

    }

    private boolean authUser(String username, String password) {
        User user = repository.findByUsername(username).orElse(null);

        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    private void closePage() {
        Stage thisPage = (Stage) error.getScene().getWindow();
        thisPage.close();
    }
}
