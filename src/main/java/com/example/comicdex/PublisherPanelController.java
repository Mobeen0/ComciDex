package com.example.comicdex;

import java.lang.System;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.PreparedStatement;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;


public class PublisherPanelController implements Initializable {

    Publisher publisher;

    @FXML
    private Button logOut;
    @FXML
    private Button AddComicButton;
    @FXML
    private Button RemoveComicButton;
    @FXML
    private Button SetSaleButton;
    @FXML
    private Button CheckComicButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane AddComicPane;
    @FXML
    private AnchorPane RemoveComicPane;
    @FXML
    private AnchorPane SetSalePane;
    @FXML
    private AnchorPane CheckComicPane;
    @FXML
    private Button chooseFileButton;
    @FXML
    private ListView ComicFolder;
    @FXML
    private ListView ChapterFolder;

    @FXML
    private Text op1;
    @FXML
    private Text op2;
    @FXML
    private TextField newComicName;
    @FXML
    private TextField newComicPrice;
    @FXML
    private ImageView backIm1;
    @FXML
    private ImageView backIm2;
    @FXML
    private ImageView backIm3;
    @FXML
    private ImageView backIm4;
    @FXML
    private Text errMsg;
    @FXML
    private TableView pubInfo;
    @FXML
    public TableColumn<ComicView,String> Name1;
    @FXML
    public TableColumn<ComicView,Integer> Price1;
    @FXML
    public TableColumn<ComicView,String> ComicPath1;
    @FXML
    public TableColumn<ComicView,String> ChapterPath1;
    @FXML
    public TableColumn<ComicView,Integer> chapterNum1;
    @FXML
    private TextField delComicField;
    @FXML
    private Button delComicButton;
    @FXML
    private TextField setSaleField;
    @FXML
    private Button setSaleButton;
    @FXML
    private TextField setSComicNameField;
    @FXML
    private ComboBox<String> setSaleComboBox;
    @FXML
    private ComboBox<String> removeComicComboBox;


    private int PubUserID;

    public class ComicView{
        private String Cname;
        private Integer price;
        private String comicPath;
        private String chapterPath;
        private Integer ChapterNum;

        ComicView(){

        }
        ComicView(String val1,Integer val2,String val3, String val4,Integer val5){
            this.Cname = val1;
            this.price = val2;
            this.comicPath = val3;
            this.chapterPath = val4;
            this.ChapterNum = val5;
        }

        public String getCname() {
            return Cname;
        }

        public void setCname(String cname) {
            Cname = cname;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getComicPath() {
            return comicPath;
        }

        public void setComicPath(String comicPath) {
            this.comicPath = comicPath;
        }

        public String getChapterPath() {
            return chapterPath;
        }

        public void setChapterPath(String chapterPath) {
            this.chapterPath = chapterPath;
        }

        public int getChapterNum() {
            return ChapterNum;
        }

        public void setChapterNum(int chapterNum) {
            ChapterNum = chapterNum;
        }
    }
    void setPubUserName(String valPassed) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        int count;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT u.ID1 AS ME FROM USER1 u WHERE u.UNAME1 = '"+ valPassed + "'";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        count = rs.getInt("ME");
        mydb.closeConnection();
        this.PubUserID = count;
    }

    @FXML
    protected void PressBackIm(){
        RemoveComicPane.setVisible(false);
        SetSalePane.setVisible(false);
        AddComicPane.setVisible(false);
        CheckComicPane.setVisible(false);
        mainPane.setVisible(true);
    }

    @FXML
    protected void enterDelComicButton(){
        delComicButton.setStyle("-fx-background-color: red");
        setSaleButton.setStyle("-fx-background-color: red");
    }
    @FXML
    protected void leaveDelComicButton(){
        delComicButton.setStyle("-fx-background-color: yellow");
        setSaleButton.setStyle("-fx-background-color: yellow");
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
    protected void enterCheckComicButton(){
        CheckComicButton.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void leaveCheckComicButton(){
        CheckComicButton.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7; -fx-background-radius: 30 30 30 30");
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
    protected void pressAddComic(){
        mainPane.setVisible(false);
        AddComicPane.setVisible(true);
    }
    @FXML
    protected void pressCheckComic() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        mainPane.setVisible(false);
        CheckComicPane.setVisible(true);
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT * FROM PUBLISHER p INNER JOIN COMIC c1 ON p.ID1" +
                " = c1.P_ID INNER JOIN CHAPTERS c2 ON c1.ID1 = c2.C_ID" +
                " WHERE p.ID1 = " + PubUserID;
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ObservableList<ComicView> mylist = FXCollections.observableArrayList();
        if(rs!=null){
            while(rs.next()){
                ComicView rowData = new ComicView(rs.getString(5),rs.getInt(8),
                        rs.getString(10),rs.getString(13),rs.getInt(11));
                mylist.add(rowData);
            }
            pubInfo.setItems(mylist);
        }

        mydb.closeConnection();
    }
    @FXML
    protected void enterAddComicButton(){
        AddComicButton.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void leaveAddComicButton(){
        AddComicButton.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void enterRemoveComicButton(){
        RemoveComicButton.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void leaveRemoveComicButton(){
        RemoveComicButton.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void enterSetSaleButton(){
        SetSaleButton.setStyle("-fx-background-color: #5e87f7; -fx-text-fill: yellow; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void leaveSetSaleButton(){
        SetSaleButton.setStyle("-fx-background-color: yellow; -fx-text-fill: #5e87f7; -fx-background-radius: 30 30 30 30");
    }
    @FXML
    protected void enterChooseFileButton(){
        chooseFileButton.setStyle("-fx-background-color: White;");
    }
    @FXML
    protected void leaveChooseFileButton(){
        chooseFileButton.setStyle("-fx-background-color: Silver;");
    }
    private String ComicPath;
    private File[] subFolders;
    private int chapterCount;
    @FXML
    protected void enterFinalAddComicButton() throws ClassNotFoundException, SQLException, IOException, InterruptedException{

        String retVal;
        retVal  = publisher.addComic(newComicName.getText(),newComicPrice.getText(),
                PubUserID,chapterCount,ComicPath,subFolders);

        if(retVal.isEmpty()){
            newComicName.setText("");
            newComicPrice.setText("");
            ComicFolder.getItems().clear();
            ChapterFolder.getItems().clear();
            errMsg.setVisible(false);
        }
        else{
            errMsg.setText(retVal);
            errMsg.setVisible(true);
        }




    }
    @FXML
    protected void pressRemoveComicButton() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        mainPane.setVisible(false);
        RemoveComicPane.setVisible(true);
        ObservableList<String> myComicList = FXCollections.observableArrayList();
        String query = "SELECT CNAME FROM COMIC c WHERE c.P_ID = " + PubUserID;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            myComicList.add(rs.getString(1));
        }
        mydb.closeConnection();
        removeComicComboBox.setItems(myComicList);
        removeComicComboBox.setStyle(
                "-fx-font-size: 17px; " +
                        "-fx-font-family: 'Segoe UI'; " +
                        "-fx-alignment: center; ");
    }
    @FXML
    protected void pressSetSButton()  throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        mainPane.setVisible(false);
        SetSalePane.setVisible(true);
        ObservableList<String> myComicList = FXCollections.observableArrayList();
        String query = "SELECT CNAME FROM COMIC c WHERE c.P_ID = " + PubUserID;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            myComicList.add(rs.getString(1));
        }
        mydb.closeConnection();
        setSaleComboBox.setItems(myComicList);
        setSaleComboBox.setStyle(
                "-fx-font-size: 17px; " +
                        "-fx-font-family: 'Segoe UI'; " +
                        "-fx-alignment: center; ");
    }

    @FXML
    protected void pressChooseFileButton() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        DirectoryChooser fc = new DirectoryChooser();
        fc.setInitialDirectory(new File("C:\\Users\\User\\IdeaProjects\\ComicDex\\src\\main\\resources\\com\\example\\comicdex\\assets\\ComicBooks"));
        File selectedFile = fc.showDialog(null);
        if(selectedFile !=null){
            chapterCount =0;
            ComicFolder.setVisible(true);
            ChapterFolder.setVisible(true);
            op1.setVisible(true);
            op2.setVisible(true);
            ComicFolder.getItems().add(selectedFile.getAbsolutePath());
            ComicPath = selectedFile.getAbsolutePath();
            subFolders = selectedFile.listFiles(File::isDirectory);
            for(File subFolder : subFolders){
                chapterCount++;
                ChapterFolder.getItems().add(subFolder.getAbsolutePath());
            }

        }
    }
    @FXML
    protected void pressDelComicButton()throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        if(removeComicComboBox.getValue()==null){
            return;
        }
        publisher.delComic(removeComicComboBox.getValue());
        removeComicComboBox.setValue(null);
        pressRemoveComicButton();

    }
    @FXML
    protected void pressSetSaleButton()throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(setSaleField.getText().isEmpty() || setSaleComboBox.getValue()==null){
            return;
        }
        publisher.setSale(setSaleField.getText(),setSaleComboBox.getValue());

        setSaleField.setText("");
        setSaleComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publisher = new Publisher();

        Name1.setCellValueFactory(new PropertyValueFactory<ComicView,String>("Cname"));
        Price1.setCellValueFactory(new PropertyValueFactory<ComicView,Integer>("price"));
        ComicPath1.setCellValueFactory(new PropertyValueFactory<ComicView,String>("comicPath"));
        ChapterPath1.setCellValueFactory(new PropertyValueFactory<ComicView,String>("chapterPath"));
        chapterNum1.setCellValueFactory(new PropertyValueFactory<ComicView,Integer>("ChapterNum"));
    }
}
