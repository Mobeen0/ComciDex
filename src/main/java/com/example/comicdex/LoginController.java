package com.example.comicdex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.sql.*;
import javafx.scene.control.TextField;
import java.lang.System;
import javafx.scene.text.Text;

public class LoginController implements Initializable {


    @FXML
    private Button sign1;

    @FXML
    private Button log1;

    @FXML
    private ImageView load;
    @FXML
    private TextField userName;
    @FXML
    private TextField pass;
    @FXML
    private Text error;

    @FXML
    protected void onHoverSign1(){
        sign1.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }

    @FXML
    protected void onHoverLeaveSign1(){
        sign1.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }

    @FXML
    protected void onHoverLog1(){
        log1.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }

    @FXML
    protected void onHoverLeaveLog1(){
        log1.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }
    @FXML
    protected void onLogClick() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        // remember to enter SQL query here as well
        load.setVisible(true);


        int count;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ userName.getText()
                + "' AND u.PASSWORD1 = '"+ pass.getText()+"'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt("ME");
        if(count ==1){
            query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ userName.getText()
                    + "' AND u.PASSWORD1 = '"+ pass.getText()+"' AND u.UTYPE = 'A'";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt("ME");
            if(count==1){
                mydb.closeConnection();
                takeAdminPanel();
                return;
            }
            query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ userName.getText()
                    + "' AND u.PASSWORD1 = '"+ pass.getText()+"' AND u.UTYPE = 'P'";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt("ME");
            if(count ==1){
                mydb.closeConnection();
                takePublisherPanel(userName.getText());
                return;
            }
            query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ userName.getText()
                    + "' AND u.PASSWORD1 = '"+ pass.getText()+"' AND u.UTYPE = 'C'";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt("ME");
            if(count ==1){
                mydb.closeConnection();
                takeCustomerPanel(userName.getText());
            }
        }
        else{ // write cases for
            load.setVisible(false);
            error.setVisible(true);
            mydb.closeConnection();
        }




    }
    @FXML
    protected void onSignClick() throws IOException {
        //takes me to the SignUp page
        log1.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene sc = new Scene(root);
        signup.setScene(sc);
        signup.setTitle("ComicDex");
        signup.show();
        signup.setResizable(false);

    }
    @FXML
    protected void takeAdminPanel() throws IOException{
        log1.getScene().getWindow().hide();
        Stage Admin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene sc = new Scene(root);
        Admin.setScene(sc);
        Admin.setTitle("ComicDex");
        Admin.show();
        Admin.setResizable(false);
    }

    @FXML
    protected void takePublisherPanel(String val) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        log1.getScene().getWindow().hide();
        Stage Pub = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("PublisherPanel.fxml"));
        Parent root;
        FXMLLoader load = new FXMLLoader(getClass().getResource("PublisherPanel.fxml"));;
        root = load.load();
        PublisherPanelController PPC = load.getController();
        PPC.setPubUserName(val);
        Scene sc = new Scene(root);
        Pub.setScene(sc);
        Pub.setTitle("ComicDex");
        Pub.show();
        Pub.setResizable(false);
    }
    @FXML
    protected void takeCustomerPanel(String val) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        log1.getScene().getWindow().hide();
        Stage Pub = new Stage();
        Parent root;
        FXMLLoader load = new FXMLLoader(getClass().getResource("CustomerPanel.fxml"));;
        root = load.load();
        CustomerPanelController CPC = load.getController();
        CPC.setCustName(val);
        Scene sc = new Scene(root);
        Pub.setScene(sc);
        Pub.setTitle("ComicDex");
        Pub.show();
        Pub.setResizable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load.setVisible(false);

    }

}
