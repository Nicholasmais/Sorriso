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
   
    
    public void busca_agenda(JPanel jPanel2) throws ClassNotFoundException, SQLException {
            
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/sorriso";
            Class.forName(myDriver);
            java.sql.Connection conn = DriverManager.getConnection(myUrl, "root", "");

            String querytotal = " SELECT count(*) from consulta";
            PreparedStatement preparedStmtTotal = conn.prepareStatement(querytotal);
     

            ResultSet rs0 = preparedStmtTotal.executeQuery();

            if(rs0.next()){
            total = rs0.getInt(1);}


            String queryconteudo = " SELECT operacao, nome_cliente, nome_dentista, consulta FROM consulta order by data_consulta, horario_consulta ";
            PreparedStatement preparedStmtGetPost = conn.prepareStatement(queryconteudo);
           // preparedStmtGetPost.setString(1,username);

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
                jLabels[i] = new JLabel("<html><br> Operação: "+list_operacao.get(i) +" Data: "+list_consulta.get(i) + "<br> Paciente: "+list_nome_cliente.get(i)+ " | Médico: " + list_nome_dentista.get(i)+"<br> ."); 
                jLabels[i].setForeground(Color.WHITE);
                jLabels[i].setSize(700,400);
                jLabels[i].setBorder(border);
                jPanel2.add(jLabels[i]);
                
            }
    }
}
