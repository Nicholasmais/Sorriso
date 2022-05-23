/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorriso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nicoe
 */
public class CheckIfPersonExists {
   
    
    public boolean check(String email, String funcao) throws ClassNotFoundException, SQLException{
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/sorriso";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
        
               String query = "select nome from pessoa WHERE email = ? AND funcao = ?";
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,email);
               preparedStmt.setString (2,funcao);
         
               // execute the preparedstatement
               ResultSet rs = preparedStmt.executeQuery();
               
               String user = "";
               while (rs.next()){
               user = rs.getString(1);
       
               }
               
               if (user == ""){
                   return false;
               }
               else{
               return true;}
    
}
}