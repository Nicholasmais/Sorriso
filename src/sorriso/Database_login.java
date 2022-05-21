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
import java.sql.SQLException;
import javax.swing.JButton;
/**
 *
 * @author nicoe
 */
public class Database_login extends StartPage {

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

            public int id_pessoa;
            public String nome;
            public String telefone;
            public String email;
            public String senha;
            public String funcao;
            public int id_agenda;
            public String response;
            public boolean login;
            
               public void logado(JButton button){
                button.setEnabled(login);
               }
            
               public void login() throws ClassNotFoundException, SQLException {
           
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/sorriso";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
               String query = "SELECT p.nome,p.id_pessoa FROM pessoa p WHERE p.email = ? and p.senha = ?" ;
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,getEmail());
               preparedStmt.setString(2,getSenha());
       
         
               // execute the preparedstatement
               ResultSet rs = preparedStmt.executeQuery();
               String nome = null;
               String telefone = null;
               
               if(rs.next()){
               nome = rs.getString(1);
               telefone = rs.getString(2);}
               
               String response = "Faça o login corretamente";
               
                if(nome != null){
               response = "Login com sucesso!\n Olá "+nome+", seu id de usuario: " + telefone;
                this.login = true;
                
                }
               
                else{
                response = "usuario ou senha invalidos";}
                
                conn.close();
            
                this.response = response;
         }
}

