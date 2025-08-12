package com.example.java_project;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import java.util.Arrays;


public class InsuranceSystem extends Application {


    private TableView<Plans> tableView;
    private ObservableList<Plans> policyList;
    private Database database;

    @Override
    public void start(Stage primaryStage) throws IOException {
        database = new Database();
        //setting Title
        primaryStage.setTitle("Insurance Agency Management System");
        GridPane panel = new GridPane();
        panel.setAlignment(Pos.CENTER_LEFT);
        panel.setPadding(new Insets(20.5, 21.5, 23.5, 24.5));
        panel.setHgap(4.5);
        panel.setVgap(4.5);
        //adding labels with Textfields
        panel.add(new Label("ID number"), 0, 0);
        TextField ID = new TextField();
        panel.add(ID, 1, 0);
        panel.add(new Label("Name"), 0, 1);
        TextField Name = new TextField();
        panel.add(Name, 1, 1);
        panel.add(new Label("Surname"), 0, 2);
        TextField Surname = new TextField();
        panel.add(Surname, 1, 2);
        panel.add(new Label("Address"), 0, 3);
        TextField Address = new TextField();
        panel.add(Address, 1, 3);
        panel.add(new Label("Age"), 0, 4);
        TextField Age = new TextField();
        panel.add(Age, 1, 4);
        panel.add(new Label("Policy Type"), 0, 5);
        //adding combo boxes for policy type and sum insured
        ComboBox<String> PolicyType = new ComboBox<>();
        PolicyType.getItems().addAll("Health","Auto","Life");
        panel.add(PolicyType, 1, 5);
        panel.add(new Label("Sum Insured"), 0, 6);
        ComboBox<Integer> Sum_Insured = new ComboBox<>();
        Sum_Insured.getItems().addAll(100000, 200000, 150000, 50000);
        panel.add(Sum_Insured, 1, 6);
        panel.add(new Label("Coverage Amount"), 0, 7);
        TextField Coverage = new TextField();
        panel.add(Coverage, 1, 7);
        Button btnPremium = new Button("Calculate Premium");
        panel.add(new Label("Premium"), 0, 8);
        TextField Premium = new TextField();
        panel.add(btnPremium, 1, 8);
        panel.add(Premium, 1, 9);
        //adding the buttons to form
        Button btnSubmit = new Button("Submit");
        panel.add(btnSubmit, 1, 10);
        Button btnView = new Button("View Policies");
        panel.add(btnView, 0, 13);
        //Creating table for database
        tableView = new TableView<>();
        policyList = FXCollections.observableArrayList();

        //adding columns to table
        TableColumn<Plans, String> Id_Numbers = new TableColumn<>("ID Number");
        Id_Numbers.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(String.valueOf(productStringCellDataFeatures.getValue().getId_numbers())));
        TableColumn<Plans, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getName()));
        TableColumn<Plans, String> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getSurname()));
        TableColumn<Plans, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getAddress()));
        TableColumn<Plans, String> age = new TableColumn<>("Age");
        age.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getAge()));
        TableColumn<Plans, String> policyType = new TableColumn<>("Policy Type");
        policyType.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getPolicy_type()));
        TableColumn<Plans, String> sum_Insured = new TableColumn<>("Sum Insured");
        sum_Insured.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(String.valueOf(productStringCellDataFeatures.getValue().getSum())));
        TableColumn<Plans, String> coverage = new TableColumn<>("Coverage");
        coverage.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(String.valueOf(productStringCellDataFeatures.getValue().getCoverage())));
        TableColumn<Plans, String> premium = new TableColumn<>("Premium");
        premium.setCellValueFactory(productStringCellData -> new SimpleStringProperty(String.valueOf(productStringCellData.getValue().getPremium())));
        tableView.getColumns().addAll(Id_Numbers, name, surname, address, age, policyType, sum_Insured, coverage, premium);

        //adding buttons functionality
        btnSubmit.setOnAction(actionEvent -> {
            int idText = Integer.parseInt(ID.getText());
            String nameText = Name.getText();
            String surnameText = Surname.getText();
            String addressText = Address.getText();
            int ageValue = Integer.parseInt(Age.getText());
            String policyTypeValue = PolicyType.getValue();
            int sumInsuredValue = Sum_Insured.getValue();
            double coverageValue = Double.parseDouble(Coverage.getText());
            double premiumValue = calculatePremium(policyTypeValue, coverageValue);


            database.InsertCustomer(idText, nameText, surnameText, addressText, ageValue);
            database.InsertPolicy(idText, policyTypeValue, sumInsuredValue, coverageValue, premiumValue);


            updatetableView();
        });

        btnView.setOnAction(actionEvent -> {
           updatetableView();
        });

        btnPremium.setOnAction(actionEvent -> {
             String policyTypeValue = PolicyType.getValue();
             double coverageValue = Double.parseDouble(Coverage.getText());
             double calculatedPremium = calculatePremium(policyTypeValue, coverageValue);
             Premium.setText(String.valueOf(calculatedPremium));

        });
       

        VBox var = new VBox(30, panel, tableView);
        Scene scene = new Scene(var, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updatetableView() {
        policyList.clear();
        policyList.addAll(database.getPolicies());
        tableView.setItems(policyList);
    }

    private double calculatePremium( String policyType, double coverage){
        switch(policyType){
            case "Health" :
                return 0.08 * coverage;
            case "Auto":
                return 0.1 * coverage;
            case "Life":
                return 0.05 * coverage;
            default:
                return 0;
        }

    }


    public static void main(String[] args) {
        launch();
    }

}