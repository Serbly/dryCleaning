package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.User;
import com.example.drycleaning.repository.UserRepository;
import com.example.drycleaning.util.RoleComboBoxItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersController {
    private final FXMLConfiguration configuration;
    private final UserRepository userRepository;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, RoleComboBoxItem> role;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableColumn<User, String> fullname;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> phone;

    @FXML
    private TableColumn<User, LocalDateTime> createdAt;

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        refreshTable();
    }

    @FXML
    protected void exit() throws IOException {
        Stage authPage = configuration.authPageFXMLLoader();
        authPage.show();
        closePage();
    }

    @FXML
    protected void addUser() throws IOException {
        Stage addUser = configuration.addUserPageFXMLLoader();
        addUser.show();
        closePage();
    }

    @FXML
    protected void deleteUser() {
        User user = table.getSelectionModel().getSelectedItem();
       if (user == null) {
           return;
       }
       userRepository.delete(user);
       refreshTable();
    }

    private void closePage() {
        Stage thisPage = (Stage) table.getScene().getWindow();
        thisPage.close();
    }

    private void refreshTable() {
        List<User> users = userRepository.findAll();
        table.getItems().setAll(users);
    }
}
