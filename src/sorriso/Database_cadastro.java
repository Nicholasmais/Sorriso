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
public class Database_cadastro {

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
            
            
               public void cadastrar() throws ClassNotFoundException, SQLException {
           
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/sorriso";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
               String query = "INSERT INTO pessoa(nome, telefone, email, senha, funcao) VALUES (?,?,?,?,?)" ;
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,getNome());
               preparedStmt.setString(2,getTelefone());
               preparedStmt.setString(3,getEmail());
               preparedStmt.setString(4,getSenha());
               preparedStmt.setString(5,getFuncao());
       
         
               // execute the preparedstatement
               try{
               preparedStmt.executeUpdate();}
               
               catch (Exception e2){
                System.err.println(e2.getMessage());
}
               
               
               String response = "Não cadastrado";
               
                if(nome != null){
               response = nome + " Cadastrado.";}
            
                else{
                response = "Erro ao cadastrar: ";}
                
                conn.close();
            
                this.response = response;
         }
}
