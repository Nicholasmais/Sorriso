/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorriso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author nicoe
 */
public class Database_login {

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
            public String user;
            public String password;
    
            
            
               public void actionPerformed(ActionEvent e) {
            try{
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/provaprog";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
               String query = "SELECT u.id_usuario,u.username, p.nome FROM usuario u INNER JOIN perfil p on p.id_perfil = u.id_usuario where u.username = ? and u.senha = ?" ;
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,usuario.getText());
               preparedStmt.setString(2,senha.getText());
       
         
               // execute the preparedstatement
               ResultSet rs = preparedStmt.executeQuery();
               String id_usuario = null;
               String nome_ = null;
               String username = null;
               if(rs.next()){
               id_usuario = rs.getString(1);
               username = rs.getString(2);
               nome_ = rs.getString(3);}

               
                if(id_usuario != null){
               status.setText("<html>Login com sucesso!\n Olá "+nome_+", seu id de usuario: " + id_usuario+"</html>");
               String queryperfil = " insert into ultimo_login(username) VALUES(?)";
               PreparedStatement preparedStmtPerfil = conn.prepareStatement(queryperfil);
               preparedStmtPerfil.setString (1,username);
               preparedStmtPerfil.executeUpdate();
               
               String querycont = " select cont from contador";
               PreparedStatement preparedStmtCont = conn.prepareStatement(querycont);
               ResultSet rs_cont = preparedStmtCont.executeQuery();
               int cont = 0;
               if(rs_cont.next()){
               cont = rs_cont.getInt(1);}
               cont++;

               String querycontador = " update  contador set cont = ?  where cont >=0  limit 1";
               PreparedStatement preparedStmtContador = conn.prepareStatement(querycontador);
               preparedStmtContador.setInt(1,cont);
               preparedStmtContador.executeUpdate();
            
            }
            
                else{
                status.setText("usuario ou senha invalidos");
                }
                conn.close();
               }
               catch (Exception e2)
               {
                 System.err.println("Got an exception!");
                 System.err.println(e2.getMessage());
                 e2.printStackTrace();
               }    
         }
      }
}
