package com.example.supplychainmanagement;

import javafx.scene.chart.PieChart;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private byte[] getSHA(String input){
        try{
            //SHA means Salty Hashing Algotithm.
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private String getEncryptedPassword(String password)
    {
        String encryptedPassword = "";
        try {
            BigInteger number = new BigInteger(1, getSHA(password));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return encryptedPassword;
    }
    public boolean customerLogin(String email , String password) throws SQLException {
        String query = String.format("Select * from customer where email = '%s' and password = '%s'" , email , password);
        DatabaseConnection dbCon = new DatabaseConnection();
        ResultSet rs = dbCon.getQueryTabel(query);
        try {
            if (rs != null && rs.next()) return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

//    public static void main(String[] args) throws SQLException {
//        Login login = new Login();
//        System.out.println(login.customerLogin("Hritik@gmail.com" , "abc123"));
//    }

    public static void main(String[] args) {
        Login login = new Login();
        System.out.println(login.getEncryptedPassword("a2bc123"));
        //Encrypted length remain same for every password
        //if password length is small or not the encrypted generated password,
        //length is same for all input.
    }
}
