module com.example.drycleaning {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires jakarta.persistence;
    requires static lombok;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.data.jpa;
    requires spring.core;

    requires org.hibernate.orm.core;
    requires spring.beans;
    requires spring.data.commons;

    opens com.example.drycleaning.model;
    opens com.example.drycleaning;
    opens com.example.drycleaning.controllers;
    opens com.example.drycleaning.service;
    opens com.example.drycleaning.config;

    exports com.example.drycleaning;
    exports com.example.drycleaning.controllers;
    exports com.example.drycleaning.service;
    exports com.example.drycleaning.model;
    exports com.example.drycleaning.config;
}