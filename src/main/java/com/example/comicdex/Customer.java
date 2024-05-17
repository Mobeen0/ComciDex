package com.example.comicdex;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer extends User{
    String name;
    int bal;
    int U_ID; //for db purposes, easier search

    Customer(){

    }
    Customer(String newName,int Bal, int ID){
        this.name = newName;
        this.bal = Bal;
        this.U_ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    public int getU_ID() {
        return U_ID;
    }

    public void setU_ID(int u_ID) {
        U_ID = u_ID;
    }

    public String buyComic(int cPrice,int pubNum, int comNum,int chapNum) throws ClassNotFoundException, SQLException,
            IOException, InterruptedException{
        if(cPrice > this.bal){
            return("Error: Not enough Funds");
        }
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) FROM OWNERSHIP o" +
                " WHERE o.C_ID = " + this.U_ID + " AND o.P_ID = "+
                pubNum + " AND o.comNum = " + comNum +
                " AND o.chapNum = " + chapNum;
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        if(rs.getInt(1) > 0){
            mydb.closeConnection();
            return("Error: You already own this chapter, Can not Buy");
        }
        this.bal = this.bal - cPrice;

        query = "UPDATE CUSTOMER SET bal = " + this.bal + " WHERE ID1 = " + this.U_ID;
        PreparedStatement ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
        query = "INSERT INTO OWNERSHIP(C_ID,P_ID,comNum,chapNum,DATE_OF_PURCHASE,PRICE)" +
                " VALUES( " + this.U_ID + ", " + pubNum + ", " + comNum + "," + chapNum +
                ", SYSDATE, " + cPrice + ")";
        ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();

        mydb.closeConnection();
        return "";
    }

    public String addWishList(int pubNum, int comNum,int chapNum)
            throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT COUNT(*) FROM WISHLIST w" +
                " WHERE w.C_ID = " + this.U_ID + " AND w.P_ID = "+
                pubNum + " AND w.comNum = " + comNum +
                " AND w.chapNum = " + chapNum;
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        if(rs.getInt(1) > 0){
            mydb.closeConnection();
            return("Error: You already have this item in your wishlist");
        }

        PreparedStatement ps;
        query = "INSERT INTO WISHLIST(C_ID,P_ID,comNum,chapNum)" +
                " VALUES( " + this.U_ID + ", " + pubNum + ", " + comNum + "," + chapNum + ")";
        ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
        mydb.closeConnection();

        return "";
    }


}