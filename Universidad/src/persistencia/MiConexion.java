/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author PUESTO-A1
 */
public class MiConexion {
    private static String url = "jdbc:mysql://localhost/universidad";
    private static String usuario = "root";
    private static String pass = "";
    private static Connection conn = null;

    public MiConexion() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            //Logger.getLogger(MiConexion.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo obtener el Driver.");
        }
        
    }
    
    public static Connection getConexion(){
        Connection conn = null;
        if(conn == null){
            try{
                conn = DriverManager.getConnection(url, usuario, pass);
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base.");
            }
        }
        return conn;
    }
                                    
}
