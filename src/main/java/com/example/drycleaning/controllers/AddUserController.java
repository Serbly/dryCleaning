package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.Role;
import com.example.drycleaning.model.User;
import com.example.drycleaning.repository.RoleRepository;
import com.example.drycleaning.repository.UserRepository;
import com.example.drycleaning.util.RoleComboBoxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddUserController {
    private final FXMLConfiguration configuration;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @FXML
    private ComboBox<RoleComboBoxItem> role;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField fullname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Label error;

    @FXML
    public void initialize() {
        List<RoleComboBoxItem> items = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();

        for (Role role : roles) {
            RoleComboBoxItem roleComboBoxItem = new RoleComboBoxItem();
            roleComboBoxItem.setRole(role);
            roleComboBoxItem.setRoleName(role.getRoleName());
            items.add(roleComboBoxItem);
        }

        ObservableList<RoleComboBoxItem> observableList = FXCollections.observableList(items);
        role.getItems().setAll(observableList);
    }

    @FXML
    protected void addUser() {
        try {
            User user = User.builder()
                    .username(username.getText())
                    .password(password.getText())
                    .fullName(fullname.getText())
                    .email(email.getText())
                    .phone(phone.getText())
                    .role(role.getValue().getRole())
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(user);

            Stage users = configuration.usersPageFXMLLoader();
            users.show();
            closePage();

        } catch (Exception e) {
            error.setText("Не удалось добавить пользователя");
        }
    }

    @FXML
    protected void exit() throws IOException {
        Stage users = configuration.usersPageFXMLLoader();
        users.show();
        closePage();
    }

    private void closePage() {
        Stage thisPage = (Stage) error.getScene().getWindow();
        thisPage.close();
    }
}
