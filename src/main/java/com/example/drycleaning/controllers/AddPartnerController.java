package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.Employee;
import com.example.drycleaning.model.Partner;
import com.example.drycleaning.repository.EmployeeRepository;
import com.example.drycleaning.repository.PartnerRepository;
import com.example.drycleaning.util.ManagerComboBoxItem;
import com.example.drycleaning.util.EditablePartner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddPartnerController {
    private final FXMLConfiguration configuration;
    private final PartnerRepository repository;
    private final EmployeeRepository employeeRepository;

    @FXML
    private TextField name;

    @FXML
    private TextField director;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField inn;

    @FXML
    private TextField rating;

    @FXML
    private ComboBox<ManagerComboBoxItem> manager;

    @FXML
    private DatePicker createdAt;

    @FXML
    private Label error;

    private Partner partner;

    @FXML
    public void initialize() {
        List<ManagerComboBoxItem> items = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            ManagerComboBoxItem managerComboBoxItem = new ManagerComboBoxItem();
            managerComboBoxItem.setManager(employee);
            managerComboBoxItem.setFullname(employee.getUser().getFullName());
            items.add(managerComboBoxItem);
        }

        ObservableList<ManagerComboBoxItem> observableList = FXCollections.observableList(items);
        manager.getItems().setAll(observableList);

        partner = EditablePartner.getPartner();

        if (partner != null) {
            name.setText(partner.getName());
            director.setText(partner.getDirectorFullname());
            phone.setText(partner.getPhone());
            email.setText(partner.getEmail());
            inn.setText(partner.getInn());
            rating.setText(partner.getRating().toString());
            manager.getSelectionModel().select(
                    new ManagerComboBoxItem(partner.getManager(), partner.getManager().getUser().getFullName())
            );
            createdAt.setValue(partner.getCreatedAt().toLocalDate());
        }
    }

    @FXML
    protected void addPartner() throws IOException {
        if (partner != null) {
            Partner partnerToSave = repository.findById(partner.getId()).orElseThrow();
            partnerToSave.setName(name.getText());
            partnerToSave.setDirectorFullname(director.getText());
            partnerToSave.setPhone(phone.getText());
            partnerToSave.setEmail(email.getText());
            partnerToSave.setInn(inn.getText());
            partnerToSave.setRating(Short.valueOf(rating.getText()));
            partnerToSave.setManager(manager.getValue().getManager());
            partnerToSave.setCreatedAt(createdAt.getValue().atStartOfDay());
            repository.save(partnerToSave);

            Stage partnersPage = configuration.partnersPageFXMLLoader();
            partnersPage.show();
            closePage();

            return;
        }

        try {
            Partner partner = Partner.builder()
                    .name(name.getText())
                    .directorFullname(director.getText())
                    .phone(phone.getText())
                    .email(email.getText())
                    .inn(inn.getText())
                    .rating(Short.valueOf(rating.getText()))
                    .manager(manager.getValue().getManager())
                    .createdAt(createdAt.getValue().atStartOfDay())
                    .build();

            repository.save(partner);

            Stage partnersPage = configuration.partnersPageFXMLLoader();
            partnersPage.show();
            closePage();

        } catch (Exception exception) {
            error.setText("Не удалось добавить партнера");
        }
    }

    @FXML
    protected void exit() throws IOException {
        Stage partnersPage = configuration.partnersPageFXMLLoader();
        partnersPage.show();
        closePage();
    }

    private void closePage() {
        Stage thisPage = (Stage) name.getScene().getWindow();
        thisPage.close();
    }
}
