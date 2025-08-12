package com.example.java_project;

import java.sql.*;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    public void InsertCustomer(int idText, String nameText, String surnameText, String addressText, int ageText)  {
        String sql = "INSERT INTO customers (customer_id,name, surname, Addressl, age ) VALUES (?,?,?,?,?)";
        try (Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java",
                "root",
                "kaishori03");

             PreparedStatement state = connect.prepareStatement(sql)) {
            state.setInt(1, idText);
            state.setString(2, nameText);
            state.setString(3, surnameText);
            state.setString(4, addressText);
            state.setInt(5, ageText);
            state.executeUpdate();//Run the query

            try(Statement check = connect.createStatement();
            ResultSet rs = check.executeQuery("SELECT DATABASE(), SCHEMA(), COUNT(*) FROM customers")){
               if (rs.next()){
                   System.out.println("DEBUG: Connected to schema = " + rs.getString(1));
                   System.out.println("DEBUG: SCHEMA() function says = " + rs.getString(2));
                   System.out.println("DEBUG: Total rows in customers table now = " + rs.getInt(3));

               }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);//Show alert if its successful to view that it worked
            alert.setTitle("Successful");
            alert.setContentText("Record has been inserted");
            alert.show();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

         public  void InsertPolicy(int customerID, String policyType, int sumInsured, double coverage, double premium)  {
             String sql ="INSERT INTO policies (customer_id, policyType, sumInsured, coverage, premium) VALUES (?,?,?,?,?)";
             try(Connection connect = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/java",
                     "root",
                     "kaishori03");
                 PreparedStatement state = connect.prepareStatement(sql)){
                 state.setInt(1, customerID);
                 state.setString(2, policyType);
                 state.setInt(3, sumInsured);
                 state.setDouble(4, coverage);
                 state.setDouble(5, premium);
                 state.executeUpdate();

                 try(Statement check = connect.createStatement();
                     ResultSet rs = check.executeQuery("SELECT DATABASE(), SCHEMA(), COUNT(*) FROM policies")){
                     if (rs.next()){
                         System.out.println("DEBUG: Connected to schema = " + rs.getString(1));
                         System.out.println("DEBUG: SCHEMA() function says = " + rs.getString(2));
                         System.out.println("DEBUG: Total rows in policies table now = " + rs.getInt(3));

                     }
                 }

                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Successful");
                 alert.setContentText("Recored has been inserted");
                 alert.show();
             } catch (SQLException e){
                 e.printStackTrace();
             }

         }
         public ObservableList<Plans>getPolicies() {
             ObservableList<Plans> policylist = FXCollections.observableArrayList();
             String sql1 = "Select cus.customer_id, cus.Name, cus.surname, cus.Addressl, cus.age, " + " pol.policyType, pol.sumInsured, pol.coverage, pol.premium " + "FROM customers cus JOIN policies pol ON cus.customer_id = pol.customer_id";
             try (Connection connect = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/java",
                     "root",
                     "kaishori03");
                  PreparedStatement state = connect.prepareStatement(sql1);
                  ResultSet result = state.executeQuery()    ) {
                 while (result.next()) {
                    Plans record = new Plans(
                             result.getInt("customer_id"),
                             result.getString("name"),
                             result.getString("surname"),
                             result.getString("Addressl"),
                             result.getInt("age"),
                             result.getString("policyType"),
                             result.getInt("sumInsured"),
                             result.getDouble("coverage"),
                             result.getDouble("premium"));
                     policylist.add(record);
                 }
             }
             catch (SQLException e) {
                 e.printStackTrace();
             }
             return policylist;
         }
}


