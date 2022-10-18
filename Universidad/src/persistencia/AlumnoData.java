/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.sun.istack.internal.logging.Logger;
import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author PUESTO-A1
 */
public class AlumnoData {
    private Connection conn = null;

    public AlumnoData(MiConexion conn) {
        this.conn = conn.buscarConexion();
    }
    
    public void guardarAlumno(Alumno a){
        String q = "INSERT INTO alumnos(apellido, nombre, dni, fechaNacimiento) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                a.setIdAlumno(rs.getInt(1));
            } else {
                System.out.println("No se puede obtener un ID");
            }
            ps.close();
            
        } catch(SQLException ex) {
            Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno a = null;
        String q = "SELECT * FROM alumnos WHERE alumnos.idAlumno = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a = new Alumno();
                rs.getString("apellido");
                rs.getString("nombre");
                rs.getDate("fechaNacimiento").toLocalDate();
                rs.getInt("estado");
                a.setIdAlumno(rs.getInt("id"));
             
            }
            ps.close();
        } catch(SQLException ex) {
            Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public List<Alumno> listarAlumnos(){
        Alumno a = null;
        List<Alumno> alumnos = new ArrayList<>();
        String q = "SELECT * FROM alumnos";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a = new Alumno();
                rs.getString("apellido");
                rs.getString("nombre");
                rs.getDate("fechaNacimiento").toLocalDate();
                rs.getInt("estado");
                a.setIdAlumno(rs.getInt("id"));
                alumnos.add(a);
            }
            ps.close();
        } catch(SQLException ex) {
            Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }
    
    public void actualizarAlumno(Alumno a){
        String q = "UPDATE SET apellido = ?, nombre = ?, dni = ?, fechaNacimiento = ? WHERE idAlumno = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(5, a.getIdAlumno());
            ps.executeUpdate();
            ps.close();
            
        } catch(SQLException ex) {
            Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarAlumno(int id){
        String q = "DELETE FROM alumnos WHERE idAlumno = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            
        } catch(SQLException ex) {
            Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
        }
    }
}
