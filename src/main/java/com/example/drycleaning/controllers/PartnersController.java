package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.model.Partner;
import com.example.drycleaning.repository.PartnerRepository;
import com.example.drycleaning.util.EditablePartner;
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
public class PartnersController {
    private final FXMLConfiguration configuration;
    private final PartnerRepository partnerRepository;

    @FXML
    private TableView<Partner> table;

    @FXML
    private TableColumn<Partner, Integer> id;

    @FXML
    private TableColumn<Partner, String> name;

    @FXML
    private TableColumn<Partner, String> director;

    @FXML
    private TableColumn<Partner, String> phone;

    @FXML
    private TableColumn<Partner, String> email;

    @FXML
    private TableColumn<Partner, String> inn;

    @FXML
    private TableColumn<Partner, Short> rating;

    @FXML
    private TableColumn<Partner, LocalDateTime> createdAt;

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        director.setCellValueFactory(new PropertyValueFactory<>("directorFullname"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        inn.setCellValueFactory(new PropertyValueFactory<>("inn"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        refreshTable();
    }

    @FXML
    private void refreshTable() {
        List<Partner> partners = partnerRepository.findAll();
        table.getItems().setAll(partners);
    }

    @FXML
    protected void exit() throws IOException {
        Stage mainPage = configuration.mainPageFXMLLoader();
        mainPage.show();
        closePage();
    }

    @FXML
    protected void addPartner() throws IOException {
        Stage addPage = configuration.addPartnerPageFXMLLoader();
        addPage.show();
        closePage();
    }

    @FXML
    protected void editPartner() throws IOException {
        EditablePartner.setPartner(table.getSelectionModel().getSelectedItem());
        Stage editPage = configuration.addPartnerPageFXMLLoader();
        EditablePartner.setPartner(null);
        editPage.show();
        closePage();
    }

    @FXML
    protected void deletePartner() {
        Partner partner = table.getSelectionModel().getSelectedItem();
        if (partner == null) {
            return;
        }
        partnerRepository.delete(partner);
        refreshTable();
    }

    private void closePage() {
        Stage thisPage = (Stage) table.getScene().getWindow();
        thisPage.close();
    }
}
