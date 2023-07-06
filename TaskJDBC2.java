package coreJava16;

import coreJava10.bankTask.TakeInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TaskJDBC2 {
    public static void main(String[] args)  {
        int pid;
        String pName;
        int qty;
        float price;
        int noOfProducts;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/testDb","root","root");
            if(connection == null){
                System.out.println("Connection Failed");
            }
            else {
                System.out.println("Connection Success!!!");
            }
            noOfProducts = TakeInput.readInteger("How many products you want to add?");
            Statement statement = connection.createStatement();
            for (int ctr = 0; ctr < noOfProducts; ctr++) {
                System.out.println("Enter Details of Product "+(ctr+1));
                pid = TakeInput.nullCheck(TakeInput.readInteger("Enter Product Id"));
                pName = TakeInput.readString("Enter Product Name\n");
                qty = TakeInput.nullCheck(TakeInput.readInteger("Enter Quantity"));
                price = TakeInput.readFloat("Enter Price");
                String sqlStmt = "Insert into Product values(" +pid+ ", '" + pName + "' , '" + qty + "' , '" + price+ "')";
//                System.out.println(sqlStmt);
                int success = statement.executeUpdate(sqlStmt);
                if(success == 1){
                    System.out.println("Success, Product: " + pid + " is inserted successfully");
                }
                else {
                    System.out.println("Failed, Product: " + pid + " is failed to get inserted");
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
