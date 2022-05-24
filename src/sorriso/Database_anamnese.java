/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorriso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author nicoe
 */
public class Database_anamnese {
               private String email_cliente;
               private String response;
               private boolean problema_mordida;
               private boolean fumante;
               private boolean drogas;
               private boolean carie;
               private boolean gengivite;
               private boolean placa_dental;
               private boolean periodontite;
               private boolean tratamento;
               private String observacao;
             
    
    public void cadastrar() throws ClassNotFoundException, SQLException{
               String myDriver = "com.mysql.jdbc.Driver";
               String myUrl = "jdbc:mysql://localhost:3306/sorriso";
               Class.forName(myDriver);
               Connection conn = DriverManager.getConnection(myUrl, "root", "");
               
               CheckIfPersonExists check = new CheckIfPersonExists();
               
          
                   if (!check.check(getEmail_cliente())){
                   this.response = "cliente não cadastrado.";
                   }
                   
               else{
       
               String query = "INSERT INTO anamnese(nome_cliente,problema_mordida ,periodontite,observacao ,placa_dental,gengivite,drogas,carie,fumante) VALUES (?,?,?,?,?,?,?,?,?)" ;
               
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString (1,getEmail_cliente());
               preparedStmt.setString(2,String.valueOf(isProblema_mordida()));
               preparedStmt.setString(3,String.valueOf(isPeriodontite()));
               preparedStmt.setString(4,getObservacao());
               preparedStmt.setString(5,String.valueOf(isPlaca_dental()));
               preparedStmt.setString(6,String.valueOf(isGengivite()));
               preparedStmt.setString(6,String.valueOf(isDrogas()));
               preparedStmt.setString(6,String.valueOf(isCarie()));
               preparedStmt.setString(6,String.valueOf(isFumante()));
       
               
               // execute the preparedstatement
               try{
               preparedStmt.executeUpdate();
               this.response = "Cadastrado";
               }
               
               catch (Exception e2){
               System.err.println(e2.getMessage());
                      
               this.response = "Não cadastrado: "+e2.getMessage();
}
               
         
               
                conn.close();}
}

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isProblema_mordida() {
        return problema_mordida;
    }

    public void setProblema_mordida(boolean problema_mordida) {
        this.problema_mordida = problema_mordida;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }

    public boolean isCarie() {
        return carie;
    }

    public void setCarie(boolean carie) {
        this.carie = carie;
    }

    public boolean isGengivite() {
        return gengivite;
    }

    public void setGengivite(boolean gengivite) {
        this.gengivite = gengivite;
    }

    public boolean isPlaca_dental() {
        return placa_dental;
    }

    public void setPlaca_dental(boolean placa_dental) {
        this.placa_dental = placa_dental;
    }

    public boolean isPeriodontite() {
        return periodontite;
    }

    public void setPeriodontite(boolean periodontite) {
        this.periodontite = periodontite;
    }

    public boolean isTratamento() {
        return tratamento;
    }

    public void setTratamento(boolean tratamento) {
        this.tratamento = tratamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }}
