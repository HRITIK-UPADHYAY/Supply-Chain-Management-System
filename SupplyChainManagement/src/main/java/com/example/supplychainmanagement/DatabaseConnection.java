package com.example.supplychainmanagement;
import java.sql.*;

public class DatabaseConnection {
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/SupplyChainManagementSystem";
    private static final String userName = "root";
    private static final String password = "HRITIK1501";

    public Statement getStatement(){
        Statement  statement = null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(databaseUrl , userName , password);
            statement = conn.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return statement;
    }

    public ResultSet getQueryTabel(String query){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int executeUpdateQuery(String query){
        Statement statement = getStatement();
        try{
            return statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args)
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet rs = databaseConnection.getQueryTabel("Select email ,first_name from customer");
        try{
            while(rs.next()){
                System.out.println(rs.getString("email") + " " + rs.getString("first_name"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
