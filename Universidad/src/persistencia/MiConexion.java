/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author PUESTO-A1
 */
public class MiConexion {
    private String url;
    private String usuario;
    private String pass;
                            
    private Connection conn;

    public MiConexion(String url, String usuario, String pass) {
        this.url = url;
        this.usuario = usuario;
        this.pass = pass;
    }
    
    public Connection buscarConexion(){
        if(conn == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, usuario, pass);
            } catch(SQLException | ClassNotFoundException ex){
                Logger.getLogger(MiConexion.class).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
                                    
}
