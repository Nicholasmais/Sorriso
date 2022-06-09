/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorriso;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author nicoe
 */
public class Database_agenda extends Agenda{
    
    int total = 0;
    String operacao = null;
    String nome_cliente = null;
    String nome_dentista = null;
    String consulta = null;
    
    ArrayList<String> list_operacao = new ArrayList<String>();
    ArrayList<String> list_nome_cliente = new ArrayList<String>();
    ArrayList<String> list_nome_dentista = new ArrayList<String>();
    ArrayList<String> list_consulta = new ArrayList<String>();
   
    
    public void busca_agenda(JPanel jPanel2, String email, LocalDate data) throws ClassNotFoundException, SQLException {
            
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/sorriso";
        Class.forName(myDriver);
        java.sql.Connection conn = DriverManager.getConnection(myUrl, "root", "");
        
        String queryconteudo;
        
        PreparedStatement preparedStmtGetPost = null;
               
        String querytotal;
        PreparedStatement preparedStmtTotal = null ;
        
        if (email == "" && data == null){
            queryconteudo = " SELECT operacao, nome_cliente, nome_dentista, consulta FROM consulta order by data_consulta, horario_consulta ";
            preparedStmtGetPost = conn.prepareStatement(queryconteudo);
            querytotal = " SELECT count(*) from consulta";
            preparedStmtTotal = conn.prepareStatement(querytotal);}
        
          
        if (data==null && email != ""){
                queryconteudo = " SELECT operacao, nome_cliente, nome_dentista, consulta FROM consulta WHERE nome_cliente = ? OR nome_dentista = ? order by data_consulta, horario_consulta ";
                preparedStmtGetPost = conn.prepareStatement(queryconteudo);
                preparedStmtGetPost.setString(1,email);
                preparedStmtGetPost.setString(2,email);
                
                querytotal = " SELECT count(*) from consulta WHERE nome_cliente = ? OR nome_dentista = ?";
                preparedStmtTotal = conn.prepareStatement(querytotal);
                preparedStmtTotal.setString(1,email);
                preparedStmtTotal.setString(2,email);    
        }
            
            
        if (data != null && email==""){
                queryconteudo = " SELECT operacao, nome_cliente, nome_dentista, consulta FROM consulta WHERE data_consulta = ? order by data_consulta, horario_consulta ";
                preparedStmtGetPost = conn.prepareStatement(queryconteudo);
                preparedStmtGetPost.setString(1,data.toString());
                
                
                querytotal = " SELECT count(*) from consulta WHERE data_consulta = ?";
                preparedStmtTotal = conn.prepareStatement(querytotal);
                preparedStmtTotal.setString(1,data.toString());
             
            
            }
        
        if (data != null && email != ""){
                queryconteudo = " SELECT operacao, nome_cliente, nome_dentista, consulta FROM consulta WHERE data_consulta = ? AND ( nome_cliente = ? OR nome_dentista = ?) order by data_consulta, horario_consulta ";
                preparedStmtGetPost = conn.prepareStatement(queryconteudo);
                preparedStmtGetPost.setString(1,data.toString());
                preparedStmtGetPost.setString(2,email);
                preparedStmtGetPost.setString(3,email);
                
                querytotal = " SELECT count(*) from consulta WHERE data_consulta = ? AND ( nome_cliente = ? OR nome_dentista = ?)";
                preparedStmtTotal = conn.prepareStatement(querytotal);
                preparedStmtTotal.setString(1,data.toString());
                preparedStmtTotal.setString(2,email);
                preparedStmtTotal.setString(3,email);
                
        }
        
     

            ResultSet rs0 = preparedStmtTotal.executeQuery();
            if(rs0.next()){
                total = rs0.getInt(1);}
            

        ResultSet rs3 = preparedStmtGetPost.executeQuery();

            while (rs3.next()) {
                operacao = rs3.getString(1); 
                list_operacao.add(operacao);
                
                nome_cliente = rs3.getString(2); 
                list_nome_cliente.add(nome_cliente);
                
                nome_dentista = rs3.getString(3);
                list_nome_dentista.add(nome_dentista);
                
                consulta = rs3.getString(4);
                list_consulta.add(consulta);
                
            }
            Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

            JLabel[] jLabels = new JLabel[total];
            for (int i = 0; i < total;i++){
                jLabels[i] = new JLabel("<html><br> Operação: "+list_operacao.get(i) +" | Data: "+list_consulta.get(i) + "<br> Paciente: "+list_nome_cliente.get(i)+ " | Médico: " + list_nome_dentista.get(i)+"<br> ."); 
                jLabels[i].setForeground(Color.WHITE);
                jLabels[i].setSize(200,100);
                jLabels[i].setBorder(border);
                jPanel2.add(jLabels[i]);
                
            }
    }
}
