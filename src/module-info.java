module RoboSurgeon {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;
    opens application;
    opens controllers;
    opens views;
    opens models;
}