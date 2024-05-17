package com.example.comicdex;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Publisher extends User {
    String name;
    int registered_bank_no;
    int P_ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegistered_bank_no() {
        return registered_bank_no;
    }

    public void setRegistered_bank_no(int registered_bank_no) {
        this.registered_bank_no = registered_bank_no;
    }

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public String addComic(String newComicName, String newComicPrice,
                           int PubUserID, int chapterCount, String ComicPath,
                           File[] subFolders)throws ClassNotFoundException, SQLException{
        if(newComicName.isEmpty() || newComicPrice.isEmpty()){
            return ("ERROR: Please Fill all fields");
        }
        int IDval = 1;
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query= "SELECT COUNT(*) FROM COMIC c WHERE c.CNAME = '"+ newComicName + "'";
        Statement st= mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int myCount = rs.getInt(1);
        if(myCount==1){
            mydb.closeConnection();
            return("Error: Comic Book with that name already exists");
        }
        query = "SELECT COUNT(*) AS ME FROM COMIC";
        st = mydb.conn.createStatement();
        rs = st.executeQuery(query);
        rs.next();
        IDval = rs.getInt("ME");
        PreparedStatement ps;
        if(IDval ==0){
            IDval++;
            query = "INSERT INTO COMIC(ID1,CNAME,P_ID,CHAPS,PRICE,DATE_ADDED,FILE_PATH) VALUES (" +
                    IDval + ",'" + newComicName + "'," + PubUserID + "," + chapterCount + "," +
                    newComicPrice+ ",SYSDATE,'" +ComicPath+"')" ;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }
        else{
            query = "SELECT MAX(ID1) from COMIC";
            st = mydb.conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            IDval = rs.getInt(1);
            IDval++;
            query = "INSERT INTO COMIC(ID1,CNAME,P_ID,CHAPS,PRICE,DATE_ADDED,FILE_PATH) VALUES (" +
                    IDval + ",'" + newComicName + "'," + PubUserID + "," + chapterCount + "," +
                    newComicPrice + ",SYSDATE,'" +ComicPath+"')" ;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }

        for(int i =1;i<=chapterCount;i++){
            query = "INSERT INTO CHAPTERS(CHAP_NO,C_ID,File_Path) VALUES ("
                    + i + "," + IDval+",'" + subFolders[i-1].getAbsolutePath() +"')";
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }
        mydb.closeConnection();
        return "";
    }

    public void delComic(String removeComicComboBox) throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "DELETE FROM COMIC WHERE CNAME = '" + removeComicComboBox+"'";
        PreparedStatement ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
    }

    public void setSale(String setSaleField,String setSaleComboBox) throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "UPDATE COMIC SET PRICE = " + setSaleField
                + " WHERE CNAME ='" + setSaleComboBox + "'";
        PreparedStatement ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
        mydb.closeConnection();

    }


}
