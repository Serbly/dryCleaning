package com.example.drycleaning.controllers;

import com.example.drycleaning.config.FXMLConfiguration;
import com.example.drycleaning.util.GlobalRole;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MainPageController {
    private final FXMLConfiguration configuration;

    @FXML
    private AnchorPane anPan;

    @FXML
    private Button managerPartners;

    @FXML
    private Button costPrice;

    @FXML
    public void initialize() throws IOException {
        String role = GlobalRole.getRole();

        costPrice.setVisible(false);
        managerPartners.setVisible(false);

        if (role.equals("Менеджер")) {
            costPrice.setVisible(true);
            managerPartners.setVisible(true);
        }
    }

    @FXML
    protected void exit() throws IOException {
        Stage authPage = configuration.authPageFXMLLoader();
        authPage.show();
        closePage();
    }

    @FXML
    protected void openPartnersEditPage() throws IOException {
        Stage partnersPage = configuration.partnersPageFXMLLoader();
        partnersPage.show();
        closePage();
    }

    @FXML
    protected void openCostPriceCalc() throws IOException {
        Stage costPricePage = configuration.costPricePageFXMLLoader();
        costPricePage.show();
        closePage();
    }

    private void closePage() {
        Stage thisPage = (Stage) anPan.getScene().getWindow();
        thisPage.close();
    }
}
