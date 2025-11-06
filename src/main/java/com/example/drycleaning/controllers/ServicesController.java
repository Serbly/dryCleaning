package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.Qualification;
import com.example.drycleaning.model.ServiceEntity;
import com.example.drycleaning.model.User;
import com.example.drycleaning.repository.ServiceRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServicesController {
    private final FXMLConfiguration configuration;
    private final ServiceRepository serviceRepository;

    @FXML
    private TableView<ServiceEntity> table;

    @FXML
    private TableColumn<ServiceEntity, Integer> id;

    @FXML
    private TableColumn<ServiceEntity, String> name;

    @FXML
    private TableColumn<ServiceEntity, String> description;

    @FXML
    private TableColumn<ServiceEntity, Double> normTime;

    @FXML
    private TableColumn<ServiceEntity, Qualification> qualification;

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        normTime.setCellValueFactory(new PropertyValueFactory<>("normTime"));
        qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));

        List<ServiceEntity> services = serviceRepository.findAll();
        table.getItems().setAll(services);
    }

    @FXML
    protected void exit() throws IOException {
        Stage authPage = configuration.authPageFXMLLoader();
        authPage.show();
        Stage thisPage = (Stage) table.getScene().getWindow();
        thisPage.close();
    }

}
