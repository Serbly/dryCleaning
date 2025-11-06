package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.Employee;
import com.example.drycleaning.model.ServiceEntity;
import com.example.drycleaning.repository.CostCalculationRepository;
import com.example.drycleaning.repository.ServiceRepository;
import com.example.drycleaning.util.ServiceComboBoxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CostCalcController {
    private final FXMLConfiguration configuration;
    private final ServiceRepository serviceRepository;
    private final CostCalculationRepository calculationRepository;

    @FXML
    private Button exitBtn;

    @FXML
    private Label label;

    @FXML
    private ComboBox<ServiceComboBoxItem> serviceCB;

    public void initialize() {
        List<ServiceComboBoxItem> items = new ArrayList<>();
        List<ServiceEntity> services = serviceRepository.findAll();

        for (ServiceEntity service : services) {
            ServiceComboBoxItem serviceComboBoxItem = new ServiceComboBoxItem();
            serviceComboBoxItem.setService(service);
            serviceComboBoxItem.setServiceName(service.getName());
            items.add(serviceComboBoxItem);
        }

        ObservableList<ServiceComboBoxItem> observableList = FXCollections.observableList(items);
        serviceCB.getItems().setAll(observableList);
    }

    @FXML
    protected void onSelectItem() {
        ServiceEntity service = serviceCB.getSelectionModel().getSelectedItem().getService();
        label.setText(calculationRepository.findByService(service).orElseThrow().getTotalCost().toString());
    }

    @FXML
    protected void exit() throws IOException {
        Stage mainPage = configuration.mainPageFXMLLoader();
        mainPage.show();
        closePage();
    }

    private void closePage() {
        Stage thisPage = (Stage) exitBtn.getScene().getWindow();
        thisPage.close();
    }
}
