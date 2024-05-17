package com.example.comicdex;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.*;


public class SignupController implements Initializable {
    @FXML
    private Button sign1;
    @FXML
    private Button log1;
    @FXML
    private ImageView load;
    @FXML
    private RadioButton cust;
    @FXML
    private RadioButton publ;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private ImageView fnameImage;
    @FXML
    private ImageView lnameImage;
    @FXML
    private TextField pubName;
    @FXML
    private ImageView pubImage;
    @FXML
    private Text errMsg;
    @FXML
    private TextField UserName;
    @FXML
    private TextField pass;
    @FXML
    private TextField cPass;
    @FXML
    private TextField addr;
    @FXML
    private TextField email;
    @FXML
    private TextField bankNo;


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
    protected void onSignClick()  throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        // remember to enter SQL query here as well

        if(!publ.isSelected() && !cust.isSelected()){
            errMsg.setText("Error: Please Select Type of User");
            errMsg.setVisible(true);
            return;
        }
        if(!(pass.getText().equals(cPass.getText()))){
            errMsg.setText("Error: Password and confirm password do not match");
            errMsg.setVisible(true);
            return;
        }

        if(UserName.getText().isEmpty() || pass.getText().isEmpty() || cPass.getText().isEmpty() ||
                email.getText().isEmpty() || addr.getText().isEmpty()){
            errMsg.setText("Error: Please Fill all Fields");
            errMsg.setVisible(true);
            return;
        }
        if(cust.isSelected()){
            if(fname.getText().isEmpty() || lname.getText().isEmpty()){
                errMsg.setText("Error: Please Fill all Fields");
                errMsg.setVisible(true);
                return;
            }
        }
        if(publ.isSelected()){
            if(pubName.getText().isEmpty() || bankNo.getText().isEmpty()){
                errMsg.setText("Error: Please Fill all Fields");
                errMsg.setVisible(true);
                return;
            }
        }
        int count;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) FROM USER1 u WHERE u.UNAME1 = '" + UserName.getText() + "'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt(1);
        if(count >0){// will be only 1
            errMsg.setText("Error: UserName already Exists in database");
            errMsg.setVisible(true);
            mydb.conn.close();
            return;
        }
        query = "SELECT MAX(ID1) FROM USER1";
        st = mydb.conn.createStatement();
        rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt(1);
        count = count+1;
        String query2 = "";
        PreparedStatement ps;
        if(cust.isSelected()) {
            query = "INSERT INTO USER1(ID1,UNAME1,PASSWORD1,EMAIL,DATE_CREATED,UTYPE) VALUES(" +
            count+",'"+UserName.getText()+"','"+pass.getText()+"','"+ email.getText()+
                    "',SYSDATE,'" + "C')";
            query2 = "INSERT INTO CUSTOMER(ID1,FNAME,LNAME,BAL) VALUES (" +
            count +",'" + fname.getText() + "','" + lname.getText()+"',2000)";
        }
        if(publ.isSelected()){
            query = "INSERT INTO USER1(ID1,UNAME1,PASSWORD1,EMAIL,DATE_CREATED,UTYPE) VALUES(" +
                    count+",'"+UserName.getText()+"','"+pass.getText()+"','"+ email.getText()+
                    "',SYSDATE,'" + "P')";
            query2 = "INSERT INTO PUBLISHER(ID1,NAME1,BANK_ACCOUNT_NO) VALUES (" +
                    count +",'" + pubName.getText() + "'," + bankNo.getText() + ")";
        }
        ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
        ps = mydb.conn.prepareStatement(query2);
        ps.executeUpdate();
        mydb.conn.close();

        UserName.setText("");pass.setText("");email.setText("");fname.setText("");lname.setText("");
        bankNo.setText("");
    }

    @FXML
    protected void onLogClick() throws IOException {
        // take me to log in scene
        sign1.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene sc = new Scene(root);
        login.setScene(sc);
        login.setTitle("ComicDex");
        login.show();
        login.setResizable(false);
    }

    @FXML
    protected void onCustClick(){
        if(publ.isSelected()){
            publ.setSelected(false);
            pubName.setVisible(false);
            pubImage.setVisible(false);
            bankNo.setVisible(false);
        }
        if(cust.isSelected()){
            fname.setVisible(true);
            lname.setVisible(true);
            fnameImage.setVisible(true);
            lnameImage.setVisible(true);
        }
        else{
            fname.setVisible(false);
            lname.setVisible(false);
            fnameImage.setVisible(false);
            lnameImage.setVisible(false);
        }
    }

    @FXML
    protected void onPublClick(){
        if(cust.isSelected()){
            cust.setSelected(false);
            fname.setVisible(false);
            lname.setVisible(false);
            fnameImage.setVisible(false);
            lnameImage.setVisible(false);
        }
        if(publ.isSelected()){
            pubName.setVisible(true);
            pubImage.setVisible(true);
            bankNo.setVisible(true);
        }
        else{
            pubName.setVisible(false);
            pubImage.setVisible(false);
            bankNo.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load.setVisible(false);
    }
}
