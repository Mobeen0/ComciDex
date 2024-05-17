package com.example.comicdex;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;

public class Admin extends User{
    public void delete_account(String deleteCustField,char type) throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "DELETE FROM USER1 WHERE USER1.UNAME1 = '" + deleteCustField + "' AND USER1.UTYPE = " +
                "'" + type + "'";
        PreparedStatement ps = mydb.conn.prepareStatement(query);
        ps.executeUpdate();
        mydb.conn.close();
    }
    public void update_Cust(int CustWorkID,String newUser, String newPass, String newEmail,
                            String newFname,String newLname,String newBal) throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        PreparedStatement ps;
        String query="";
        if(!(newUser.isEmpty() && newPass.isEmpty() &&
                newEmail.isEmpty())){
            query= "UPDATE USER1 SET";
            if(!newUser.isEmpty())
                query+= " UNAME1 = '" + newUser + "',";
            if(!newPass.isEmpty())
                query += " PASSWORD1 = '" + newPass+ "',";
            if(!newEmail.isEmpty())
                query+= " EMAIL = '" + newEmail + "',";
            query = query.substring(0,query.length()-1);
            query += " WHERE ID1 = " + CustWorkID;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }

        if(!(newFname.isEmpty() &&
                newLname.isEmpty() && newBal.isEmpty())) {
            query= "UPDATE CUSTOMER SET";
            if (!newFname.isEmpty())
                query += " FNAME = '" + newFname + "',";
            if (!newLname.isEmpty())
                query += " LNAME = '" + newLname + "',";
            if (!newBal.isEmpty())
                query += " BAL = " + newBal + ",";
            query = query.substring(0,query.length()-1);
            query += " WHERE ID1 = " + CustWorkID;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }
        mydb.closeConnection();

    }

    public void update_Pub(int PubWorkID,String newUser2, String newPass2, String newEmail2,
                           String newName2, String newBank2) throws ClassNotFoundException, SQLException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        PreparedStatement ps;
        String query="";
        if(!(newUser2.isEmpty() && newPass2.isEmpty() &&
                newEmail2.isEmpty())){
            query= "UPDATE USER1 SET";
            if(!newUser2.isEmpty())
                query+= " UNAME1 = '" + newUser2 + "',";
            if(!newPass2.isEmpty())
                query += " PASSWORD1 = '" + newPass2+ "',";
            if(!newEmail2.isEmpty())
                query+= " EMAIL = '" + newEmail2 + "',";
            query = query.substring(0,query.length()-1);
            query += " WHERE ID1 = " + PubWorkID;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }

        if(!(newName2.isEmpty() && newBank2.isEmpty())) {
            query= "UPDATE PUBLISHER SET";
            if (!newName2.isEmpty())
                query += " NAME1 = '" + newName2 + "',";
            if (!newBank2.isEmpty())
                query += " BANK_ACCOUNT_NO = " + newBank2 + ",";
            query = query.substring(0,query.length()-1);
            query += " WHERE ID1 = " + PubWorkID;
            ps = mydb.conn.prepareStatement(query);
            ps.executeUpdate();
        }
        mydb.closeConnection();
    }

    public ResultSet getAllCustomers()  throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        dbClass mydb = new dbClass();
        mydb.openConnection();
        String query = "SELECT * FROM USER1 u INNER JOIN CUSTOMER c " +
                "ON u.ID1 = c.ID1";
        Statement st = mydb.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        mydb.closeConnection();
        return rs;
    }

}
