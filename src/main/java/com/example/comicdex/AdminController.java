package com.example.comicdex;

import java.lang.System;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import javafx.scene.text.Text;

public class AdminController implements Initializable {

    private Admin admin;

    @FXML
    private ImageView backIm1;
    @FXML
    private ImageView backIm2;
    @FXML
    private ImageView backIm3;
    @FXML
    private ImageView backIm4;
    @FXML
    private ImageView backIm5;
    @FXML
    private ImageView backIm6;

    @FXML
    private Button checkCust;
    @FXML
    private Button deleteCust;
    @FXML
    private Button updateCust;
    @FXML
    private Button checkPub;
    @FXML
    private Button deletePub;
    @FXML
    private Button updatePub;
    @FXML
    private AnchorPane menu;
    @FXML
    private AnchorPane menu1;
    @FXML
    private AnchorPane menu2;
    @FXML
    private AnchorPane menu3;
    @FXML
    private AnchorPane menu4;
    @FXML
    private AnchorPane menu5;
    @FXML
    private AnchorPane menu6;
    @FXML
    private TableView checkCustomerView;
    @FXML
    private TableView checkPublisherView;

    @FXML
    private Text deleteCustText;
    @FXML
    private Text deletePubText;


    @FXML
    public TableColumn<CustView,Integer> ID1;
    @FXML
    public  TableColumn<CustView,String> UserName;
    @FXML
    public  TableColumn<CustView,String> Password;
    @FXML
    public  TableColumn<CustView,String> Email;
    @FXML
    public  TableColumn<CustView,String> Date;
    @FXML
    public TableColumn<CustView,String> Type;
    @FXML
    public  TableColumn<CustView,Integer> ID2;
    @FXML
    public  TableColumn<CustView,String> FirstName;
    @FXML
    public  TableColumn<CustView,String> LastName;
    @FXML
    public TableColumn<CustView,Integer> bal;

    @FXML
    public TableColumn<PubView,Integer> ID12;
    @FXML
    public  TableColumn<PubView,String> UserName2;
    @FXML
    public  TableColumn<PubView,String> Password2;
    @FXML
    public  TableColumn<PubView,String> Email2;
    @FXML
    public  TableColumn<PubView,String> Date2;
    @FXML
    public TableColumn<PubView,String> Type2;
    @FXML
    public  TableColumn<PubView,Integer> ID22;
    @FXML
    public TableColumn<PubView,String> Name2;
    @FXML
    public TableColumn<PubView,Integer> Bankno;

    @FXML
    private TextField deleteCustField;
    @FXML
    private TextField deletePubField;
    @FXML
    private Button deleteCustButton;
    @FXML
    private Button deletePubButton;
    @FXML
    private TextField searchbar;
    @FXML
    private TextField searchbar2;
    @FXML
    private StackPane searchIm;
    @FXML
    private StackPane searchIm2;

    @FXML
    private TextField newUser;
    @FXML
    private TextField newUser2;

    @FXML
    private TextField newPass;
    @FXML
    private TextField newPass2;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newEmail2;

    @FXML
    private TextField newFname;
    @FXML
    private TextField newName2;
    @FXML
    private TextField newLname;
    @FXML
    private TextField newBal;
    @FXML
    private TextField newBank2;
    @FXML
    private Text oldUser;
    @FXML
    private Text oldUser2;
    @FXML
    private Text oldPass;
    @FXML
    private Text oldPass2;
    @FXML
    private Text oldEmail;
    @FXML
    private Text oldEmail2;
    @FXML
    private Text oldFname;
    @FXML
    private Text oldName2;
    @FXML
    private Text oldLname;
    @FXML
    private Text oldBal;
    @FXML
    private Text oldBank2;

    @FXML
    private AnchorPane custUpdatePane;
    @FXML
    private AnchorPane pubUpdatePane;
    @FXML
    private StackPane custUpdateFinalButton;
    @FXML
    private StackPane pubUpdateFinalButton;
    @FXML
    private Button logOut;


    private int CustWorkID;
    private int PubWorkID;

    public class CustView {
        private Integer ID1;
        private String UserName;
        private String Password;
        private String Email;
        private String date;
        private String Utype;
        private Integer ID1_1;
        private String FirstName;
        private String LastName;
        private Integer bal;

        CustView(){


        }
        CustView(int val1,String val2,String val3,String val4,String val5,String val6,Integer
                val7,String val8,String val9,Integer val10){
            ID1 = (val1);
            UserName = (val2);
            Password = (val3);
            Email = (val4);
            date = (val5);
            Utype =(val6);
            ID1_1 =(val7);
            FirstName =(val8);
            LastName = (val9);
            bal=(val10);

        }

        public Integer getID1() {
            return ID1;
        }

        public void setID1(Integer ID1) {
            this.ID1 = ID1;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUtype() {
            return Utype;
        }

        public void setUtype(String utype) {
            Utype = utype;
        }

        public Integer getID1_1() {
            return ID1_1;
        }

        public void setID1_1(Integer ID1_1) {
            this.ID1_1 = ID1_1;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public Integer getBal() {
            return bal;
        }

        public void setBal(Integer bal) {
            this.bal = bal;
        }
    }

    public class PubView{
        private Integer ID1;
        private String UserName;
        private String Password;
        private String Email;
        private String date;
        private String Utype;
        private Integer ID2;
        private String Name;
        private Integer bankNo;

        PubView(){

        }
        PubView(int val1,String val2,String val3,String val4,String val5,String val6,Integer
                val7,String val8,Integer val9){
            this.ID1 = val1;
            this.UserName = val2;
            this.Password = val3;
            this.Email = val4;
            this.date =val5;
            this.Utype= val6;
            this.ID2 = val7;
            this.Name =val8;
            this.bankNo= val9;
        }


        public Integer getID1() {
            return ID1;
        }

        public void setID1(Integer ID1) {
            this.ID1 = ID1;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUtype() {
            return Utype;
        }

        public void setUtype(String utype) {
            Utype = utype;
        }

        public Integer getID2() {
            return ID2;
        }

        public void setID2(Integer ID2) {
            this.ID2 = ID2;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Integer getBankNo() {
            return bankNo;
        }

        public void setBankNo(Integer bankNo) {
            this.bankNo = bankNo;
        }


    }


    @FXML
    protected void enterLogoutButton(){
        logOut.setStyle("-fx-background-color: #90ee90; -fx-text-fill: Red;");

    }
    @FXML
    protected void leaveLogoutButton(){
        logOut.setStyle("-fx-background-color: Red; -fx-text-fill: #90ee90;");
    }
    @FXML
    protected void pressLogoutButton() throws IOException {
        logOut.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene sc = new Scene(root);
        login.setScene(sc);
        login.setTitle("ComicDex");
        login.show();
        login.setResizable(false);
    }

    @FXML
    protected void enterSearchIm(){
        searchIm.setStyle("-fx-background-color: white;");
        searchIm2.setStyle("-fx-background-color: white;");
    }

    @FXML
    protected void exitSearchIM(){
        searchIm.setStyle("");
        searchIm2.setStyle("");
    }

    @FXML
    protected void enterCustUpdateIm(){
        custUpdateFinalButton.setStyle("-fx-background-color: yellow;");
        pubUpdateFinalButton.setStyle("-fx-background-color: yellow");
    }
    @FXML
    protected void exitCustUpdateIm(){
        custUpdateFinalButton.setStyle("");
        pubUpdateFinalButton.setStyle("");
    }
    @FXML
    protected void pressCustUpdateIm() throws ClassNotFoundException, SQLException, IOException, InterruptedException{

        if(newUser.getText().isEmpty() && newPass.getText().isEmpty() &&
            newEmail.getText().isEmpty() && newFname.getText().isEmpty() &&
            newLname.getText().isEmpty() && newBal.getText().isEmpty()){
            return;
        }
        admin.update_Cust(CustWorkID,newUser.getText(),newPass.getText(),newEmail.getText(),
                newFname.getText(),newLname.getText(),newBal.getText());
    }

    @FXML
    protected void pressPubUpdateIm() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        if(newUser2.getText().isEmpty() && newPass2.getText().isEmpty() &&
                newEmail2.getText().isEmpty() && newName2.getText().isEmpty()
                && newBank2.getText().isEmpty()){
            return;
        }
        admin.update_Pub(PubWorkID,newUser2.getText(),newPass2.getText(),newEmail2.getText(),newName2.getText(),newBank2.getText());
    }


    @FXML
    protected void pressSearchIm() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        int count;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ searchbar.getText() +
                "' AND u.UTYPE = 'C'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt("ME");
        if(count==1){
            custUpdatePane.setVisible(true);
            query = "SELECT * FROM USER1 u INNER JOIN CUSTOMER c ON u.ID1 = c.ID1" +
                    " WHERE u.UNAME1 = '" + searchbar.getText() + "'";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            oldUser.setText(rs.getString(2));
            oldPass.setText(rs.getString(3));
            oldEmail.setText(rs.getString(4));
            oldFname.setText(rs.getString(8));
            oldLname.setText(rs.getString(9));
            oldBal.setText(rs.getString(10));
            CustWorkID = rs.getInt(1);
        }

        mydb.closeConnection();

    }

    @FXML
    protected void pressSearchIm2() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        int count;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) AS ME FROM USER1 u WHERE u.UNAME1 = '"+ searchbar2.getText() +
                "' AND u.UTYPE = 'P'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt("ME");
        if(count==1){
            pubUpdatePane.setVisible(true);
            query = "SELECT * FROM USER1 u INNER JOIN PUBLISHER p ON u.ID1 = p.ID1" +
                    " WHERE u.UNAME1 = '" + searchbar2.getText() + "'";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            oldUser2.setText(rs.getString(2));
            oldPass2.setText(rs.getString(3));
            oldEmail2.setText(rs.getString(4));
            oldName2.setText(rs.getString(8));
            oldBank2.setText(rs.getString(9));
            PubWorkID = rs.getInt(1);
        }

        mydb.closeConnection();
    }

    @FXML
    protected void enterCustCheck(){
        checkCust.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leaveCustCheck(){
        checkCust.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");

    }
    @FXML
    protected void enterCustDel(){
        deleteCust.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leaveCustDel(){
        deleteCust.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }@FXML
    protected void enterCustUpdate(){
        updateCust.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leaveCustUpdate(){
        updateCust.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }@FXML
    protected void enterPubCheck(){
        checkPub.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leavePubCheck(){
        checkPub.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }@FXML
    protected void enterPubDel(){
        deletePub.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leavePubDel(){
        deletePub.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }@FXML
    protected void enterPubUpdate(){
        updatePub.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow;");
    }
    @FXML
    protected void leavePubUpdate(){
        updatePub.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7;");
    }
    @FXML
    protected void clickCustCheck() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        menu.setVisible(false);
        menu1.setVisible(true);
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT * FROM USER1 u INNER JOIN CUSTOMER c " +
                "ON u.ID1 = c.ID1";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ObservableList<CustView> mylist = FXCollections.observableArrayList();

        if(rs!=null){

            while(rs.next()){
                CustView rowData = new CustView(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getInt(7),rs.getString(8),
                        rs.getString(9),rs.getInt(10));
                mylist.add(rowData);

            }

            checkCustomerView.setItems(mylist);

        }
        mydb.closeConnection();

    }



    @FXML
    protected void clickCustDelete(){
        menu.setVisible(false);
        menu2.setVisible(true);

    }
    @FXML
    protected void clickCustUpdate(){
        menu.setVisible(false);
        menu3.setVisible(true);
    }
    @FXML
    protected void clickPubCheck() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        menu.setVisible(false);
        menu4.setVisible(true);
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT * FROM USER1 u INNER JOIN PUBLISHER p " +
                "ON u.ID1 = p.ID1";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ObservableList<PubView> mylist = FXCollections.observableArrayList();

        if(rs!=null){

            while(rs.next()){
                PubView rowData = new PubView(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getInt(7),rs.getString(8),
                        rs.getInt(9));
                mylist.add(rowData);

            }

            checkPublisherView.setItems(mylist);

        }
        mydb.closeConnection();
    }
    @FXML
    protected void clickPubDelete(){
        menu.setVisible(false);
        menu5.setVisible(true);
    }
    @FXML
    protected void clickPubUpdate(){
        menu.setVisible(false);
        menu6.setVisible(true);
    }

    @FXML
    protected void clickBack(){
        menu1.setVisible(false);
        menu2.setVisible(false);
        menu3.setVisible(false);
        menu4.setVisible(false);
        menu5.setVisible(false);
        menu6.setVisible(false);
        menu.setVisible(true);
    }

    @FXML
    protected void onCustDeleteButtonClick() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        admin.delete_account(deleteCustField.getText(),'C');
        deleteCustText.setVisible(true);
    }

    @FXML
    protected void onPubDeleteButtonClick() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        admin.delete_account(deleteCustField.getText(),'P');
        deletePubText.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = new Admin();

        ID1.setCellValueFactory(new PropertyValueFactory<CustView,Integer>("ID1"));
        UserName.setCellValueFactory(new PropertyValueFactory<CustView,String>("UserName"));
        Password.setCellValueFactory(new PropertyValueFactory<CustView,String>("Password"));
        Email.setCellValueFactory(new PropertyValueFactory<CustView,String>("Email"));
        Date.setCellValueFactory(new PropertyValueFactory<CustView,String>("date"));
        Type.setCellValueFactory(new PropertyValueFactory<CustView,String>("Utype"));
        ID2.setCellValueFactory(new PropertyValueFactory<CustView,Integer>("ID1_1"));
        FirstName.setCellValueFactory(new PropertyValueFactory<CustView,String>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<CustView,String>("LastName"));
        bal.setCellValueFactory(new PropertyValueFactory<CustView,Integer>("bal"));


        ID12.setCellValueFactory(new PropertyValueFactory<PubView,Integer>("ID1"));
        UserName2.setCellValueFactory(new PropertyValueFactory<PubView,String>("UserName"));
        Password2.setCellValueFactory(new PropertyValueFactory<PubView,String>("Password"));
        Email2.setCellValueFactory(new PropertyValueFactory<PubView,String>("Email"));
        Date2.setCellValueFactory(new PropertyValueFactory<PubView,String>("date"));
        Type2.setCellValueFactory(new PropertyValueFactory<PubView,String>("Utype"));
        ID22.setCellValueFactory(new PropertyValueFactory<PubView,Integer>("ID2"));
        Name2.setCellValueFactory(new PropertyValueFactory<PubView,String>("Name"));
        Bankno.setCellValueFactory(new PropertyValueFactory<PubView,Integer>("bankNo"));

        deletePubText.setVisible(false);

    }


}
