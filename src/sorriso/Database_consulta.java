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
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author nicoe
 */
public class Database_consulta {

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getEmail_dentista() {
        return email_dentista;
    }

    public void setEmail_dentista(String email_dentista) {
        this.email_dentista = email_dentista;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
            public int id_consulta;
            public String email_dentista;
            public String email_cliente;
            public String date;
            public String horario;
            public String operacao;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
            public String response;
            
    public void cadastrar() throws ClassNotFoundException, SQLException {
           
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/sorriso";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
               CheckIfPersonExists check = new CheckIfPersonExists();
               
               if (!check.check(getEmail_dentista())){
                     this.response  = "Médico não cadastrado.";
                     System.out.println(check.check(getEmail_dentista()));
               }
               else {
                   if (!check.check(getEmail_cliente())){
                   this.response = "cliente não cadastrado.";
                   }
                   
               else{
       
               String query = "INSERT INTO consulta(operacao, nome_dentista, nome_cliente, data_consulta, horario_consulta, consulta) VALUES (?,?,?,?,?,?)" ;
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,getOperacao());
               preparedStmt.setString(2,getEmail_dentista());
               preparedStmt.setString(3,getEmail_cliente());
               preparedStmt.setString(4,getDate());
               preparedStmt.setString(5,getHorario());
               preparedStmt.setString(6,getDate() +" " +getHorario());
       
               
               // execute the preparedstatement
               try{
               preparedStmt.executeUpdate();
               this.response = "Cadastrado";
               }
               
               catch (Exception e2){
               System.err.println(e2.getMessage());
                      
               this.response = "Não cadastrado: "+e2.getMessage();
}
               
         
               
                conn.close();
                       
              }
         }}
            
            
            
}
