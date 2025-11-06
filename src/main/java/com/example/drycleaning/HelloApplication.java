package com.example.drycleaning;

import com.example.drycleaning.config.SpringFXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class HelloApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(HelloApplication.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SpringFXMLLoader loader = context.getBean(SpringFXMLLoader.class);
        Parent root = loader.load("/com/example/drycleaning/auth_form.fxml");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    public static void main(String[] args) {
        launch();
    }
}