package com.example.comicdex;

import java.io.File;
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
import javafx.scene.image.Image;

public class CustomerPanelController implements Initializable {

    Customer customer;

    @FXML
    private TextField searchComicField;
    @FXML
    private ImageView searchIm;
    @FXML
    private AnchorPane searchRes1;
    @FXML
    private AnchorPane searchRes2;
    @FXML
    private AnchorPane searchRes3;
    @FXML
    private AnchorPane searchRes4;
    @FXML
    private AnchorPane searchRes5;
    @FXML
    private AnchorPane searchRes6;
    @FXML
    private AnchorPane searchRes7;
    @FXML
    private AnchorPane searchRes8;
    @FXML
    private AnchorPane searchRes9;

    @FXML
    private ImageView thumbnail1;
    @FXML
    private ImageView thumbnail2;
    @FXML
    private ImageView thumbnail3;
    @FXML
    private ImageView thumbnail4;
    @FXML
    private ImageView thumbnail5;
    @FXML
    private ImageView thumbnail6;
    @FXML
    private ImageView thumbnail7;
    @FXML
    private ImageView thumbnail8;
    @FXML
    private ImageView thumbnail9;

    @FXML
    private Text ComicName1;
    @FXML
    private Text ComicName2;
    @FXML
    private Text ComicName3;
    @FXML
    private Text ComicName4;
    @FXML
    private Text ComicName5;
    @FXML
    private Text ComicName6;
    @FXML
    private Text ComicName7;
    @FXML
    private Text ComicName8;
    @FXML
    private Text ComicName9;

    @FXML
    private Text ComicPrice1;
    @FXML
    private Text ComicPrice2;
    @FXML
    private Text ComicPrice3;
    @FXML
    private Text ComicPrice4;
    @FXML
    private Text ComicPrice5;
    @FXML
    private Text ComicPrice6;
    @FXML
    private Text ComicPrice7;
    @FXML
    private Text ComicPrice8;
    @FXML
    private Text ComicPrice9;

    @FXML
    private StackPane buy1;
    @FXML
    private StackPane wish1;
    @FXML
    private StackPane buy2;
    @FXML
    private StackPane wish2;
    @FXML
    private StackPane buy3;
    @FXML
    private StackPane wish3;
    @FXML
    private StackPane buy4;
    @FXML
    private StackPane wish4;
    @FXML
    private StackPane buy5;
    @FXML
    private StackPane wish5;
    @FXML
    private StackPane buy6;
    @FXML
    private StackPane wish6;
    @FXML
    private StackPane buy7;
    @FXML
    private StackPane wish7;
    @FXML
    private StackPane buy8;
    @FXML
    private StackPane wish8;
    @FXML
    private StackPane buy9;
    @FXML
    private StackPane wish9;

    @FXML
    private Text errMsg;
    @FXML
    private Text custDispBal;
    @FXML
    private Button logOut;



    private String CustName;
    private int CustId;
    private int CustBal;

    private comInfo[] coms;

    public class comInfo{
        public int comicNum;
        public int chapNum;
        public int pubID;
        comInfo(int val1,int val2,int val3){
            this.comicNum = val1;
            this.chapNum = val2;
            this.pubID = val3;
        }
        comInfo(){

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

    public void setCustName(String val) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        this.CustName = val;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT u.ID1,c.bal FROM USER1 u" +
                " INNER JOIN Customer c" +
                " ON u.ID1 = c.ID1" +
                " WHERE u.UNAME1 = '"+ val + "'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        this.CustId = rs.getInt(1);
        this.CustBal = rs.getInt(2);
        mydb.closeConnection();
        customer = new Customer(this.CustName,this.CustBal,this.CustId);
        custDispBal.setText("$" + Integer.toString(customer.getBal()));

    }
    @FXML
    protected void pressSearchIm() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        searchRes1.setVisible(false);searchRes2.setVisible(false);searchRes3.setVisible(false);
        searchRes4.setVisible(false);searchRes5.setVisible(false);searchRes6.setVisible(false);
        searchRes7.setVisible(false);searchRes8.setVisible(false);searchRes9.setVisible(false);
        dbClass mydb = new dbClass();
        mydb.openConnection();
        Statement st = mydb.conn.createStatement();
        String query = "SELECT c1.PRICE,c2.File_PATH,c1.CNAME,c1.ID1,c2.CHAP_NO,c1.P_ID" +
                " FROM COMIC c1" +
                " INNER JOIN CHAPTERS c2" +
                " ON c1.ID1 = c2.C_ID" +
                " WHERE LOWER(c1.CNAME) LIKE '%" + searchComicField.getText().toLowerCase()+"%'" +
                " FETCH FIRST 9 ROWS ONLY";
        ResultSet rs = st.executeQuery(query);
        int count =0;
        coms = new comInfo[9];
        while(rs.next()){

            File directory = new File(rs.getString(2));
            Image im = new Image("file:" +directory.listFiles()[0].getAbsolutePath());
            coms[count] = new comInfo(rs.getInt(4),rs.getInt(5),rs.getInt(6));
            switch(count){
                case 0:
                    thumbnail1.setImage(im);
                    searchRes1.setVisible(true);
                    ComicName1.setText(rs.getString(3));
                    ComicPrice1.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 1:
                    thumbnail2.setImage(im);
                    searchRes2.setVisible(true);
                    ComicName2.setText(rs.getString(3));
                    ComicPrice2.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 2:
                    thumbnail3.setImage(im);
                    searchRes3.setVisible(true);
                    ComicName3.setText(rs.getString(3));
                    ComicPrice3.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 3:
                    thumbnail4.setImage(im);
                    searchRes4.setVisible(true);
                    ComicName4.setText(rs.getString(3));
                    ComicPrice4.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 4:
                    thumbnail5.setImage(im);
                    searchRes5.setVisible(true);
                    ComicName5.setText(rs.getString(3));
                    ComicPrice5.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 5:
                    thumbnail6.setImage(im);
                    searchRes6.setVisible(true);
                    ComicName6.setText(rs.getString(3));
                    ComicPrice6.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 6:
                    thumbnail7.setImage(im);
                    searchRes7.setVisible(true);
                    ComicName7.setText(rs.getString(3));
                    ComicPrice7.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 7:
                    thumbnail8.setImage(im);
                    searchRes8.setVisible(true);
                    ComicName8.setText(rs.getString(3));
                    ComicPrice8.setText(Integer.toString(rs.getInt(1)));
                    break;
                case 8:
                    thumbnail9.setImage(im);
                    searchRes9.setVisible(true);
                    ComicName9.setText(rs.getString(3));
                    ComicPrice9.setText(Integer.toString(rs.getInt(1)));
                    break;

            }
            count++;
        }
        mydb.closeConnection();
    }
    @FXML
    protected void customerBuyQuery(int cPrice,int comNum, int chapNum, int pubNum)throws ClassNotFoundException, SQLException, IOException, InterruptedException{

        String retVal;
        retVal = customer.buyComic(cPrice,pubNum,comNum,chapNum);

        if(retVal.isEmpty()){
            custDispBal.setText("$" + Integer.toString(customer.getBal())); // updating the viewable customer bal
            errMsg.setVisible(false);
        }
        else{
            errMsg.setText(retVal);
            errMsg.setVisible(true);
        }

    }

    @FXML
    protected void customerWishQuery(int comNum, int chapNum, int pubNum)throws ClassNotFoundException, SQLException, IOException, InterruptedException{

        String retVal;
        retVal = customer.addWishList(pubNum,comNum,chapNum);
        if(retVal.isEmpty()){
            errMsg.setVisible(false);
        }
        else{
            errMsg.setText(retVal);
            errMsg.setVisible(true);
        }

    }
    @FXML
    protected void clickBuy1() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice1.getText()),coms[0].comicNum,coms[0].chapNum,coms[0].pubID);
    }
    @FXML
    protected void clickWish1() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[0].comicNum,coms[0].chapNum,coms[0].pubID);
    }
    @FXML
    protected void clickBuy2()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice2.getText()),coms[1].comicNum,coms[1].chapNum,coms[1].pubID);
    }
    @FXML
    protected void clickWish2()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[1].comicNum,coms[1].chapNum,coms[1].pubID);
    }

    @FXML
    protected void clickBuy3()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice3.getText()),coms[2].comicNum,coms[2].chapNum,coms[2].pubID);
    }
    @FXML
    protected void clickWish3()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[2].comicNum,coms[2].chapNum,coms[2].pubID);
    }

    @FXML
    protected void clickBuy4()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice4.getText()),coms[3].comicNum,coms[3].chapNum,coms[3].pubID);
    }
    @FXML
    protected void clickWish4()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[3].comicNum,coms[3].chapNum,coms[3].pubID);
    }

    @FXML
    protected void clickBuy5()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice5.getText()),coms[4].comicNum,coms[4].chapNum,coms[4].pubID);
    }
    @FXML
    protected void clickWish5()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[4].comicNum,coms[4].chapNum,coms[4].pubID);
    }

    @FXML
    protected void clickBuy6()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice6.getText()),coms[5].comicNum,coms[5].chapNum,coms[5].pubID);
    }
    @FXML
    protected void clickWish6()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[5].comicNum,coms[5].chapNum,coms[5].pubID);
    }

    @FXML
    protected void clickBuy7()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice7.getText()),coms[6].comicNum,coms[6].chapNum,coms[6].pubID);
    }
    @FXML
    protected void clickWish7()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[6].comicNum,coms[6].chapNum,coms[6].pubID);
    }
    @FXML
    protected void clickBuy8()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice8.getText()),coms[7].comicNum,coms[7].chapNum,coms[7].pubID);
    }
    @FXML
    protected void clickWish8()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[7].comicNum,coms[7].chapNum,coms[7].pubID);
    }

    @FXML
    protected void clickBuy9()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerBuyQuery(Integer.parseInt(ComicPrice9.getText()),coms[8].comicNum,coms[8].chapNum,coms[8].pubID);
    }
    @FXML
    protected void clickWish9()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        customerWishQuery(coms[8].comicNum,coms[8].chapNum,coms[8].pubID);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
