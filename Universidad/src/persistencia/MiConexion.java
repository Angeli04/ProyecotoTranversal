/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

//import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PUESTO-A1
 */
public class MiConexion {
    private static String url="jdbc:mysql://localhost/universidad";
    private static String usuario="root";
    private static String pass="";
                            
    private static Connection conn;

    public MiConexion(String url, String usuario, String pass) {
        this.url = url;
        this.usuario = usuario;
        this.pass = pass;
    }
    
    public static Connection buscarConexion(){
        if(conn == null){
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, usuario, pass);
            } catch (ClassNotFoundException|SQLException ex) {
                Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, null, ex);
           
            }
           
              
            }
        
        return conn;
    }
                                    
}
