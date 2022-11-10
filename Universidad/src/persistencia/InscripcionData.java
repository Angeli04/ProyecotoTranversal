/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author PUESTO-A1
 */
public class InscripcionData {
    private Connection conn = null;

    public InscripcionData() {
        this.conn = MiConexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion i){
        String q = "INSERT INTO inscripciones(idAlumno, idMateria) VALUES (?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, i.getAlumno().getIdAlumno());
            ps.setInt(2, i.getMateria().getIdMateria());
            if( ps.executeUpdate() > 0 ){
                JOptionPane.showMessageDialog(null, "Inscripción agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La inscripción no pudo ser agregada.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                i.setIdInscripcion(rs.getInt(1));
            } else {
                System.out.println("No se puede obtener un ID");
            }
            ps.close();
            
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en guardarInscripcion.");
        }
    } // Realiza una inscripcion.
        
    public Inscripcion obtenerInscripcion(Alumno a, Materia m){
        Inscripcion i = null;
        try{
            String q = "SELECT * FROM inscripciones WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ps.setInt(2, m.getIdMateria());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i = new Inscripcion();
                i.setAlumno(a);
                i.setMateria(m);
                i.setNotaFinal(rs.getFloat("notaFinal"));
                i.setIdInscripcion(rs.getInt("idInscripcion"));
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerInscripcion.");
        }
        return i;
    }
    
    public void eliminarInscripcion(Alumno a, Materia m){
        try{
            String q = "DELETE FROM inscripciones WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ps.setInt(2, m.getIdMateria());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en eliminarInscripcion.");
        }
    }
    
    public void actualizarNota(Alumno a, Materia m, Float nota){
        try{
            String q = "UPDATE inscripciones SET notaFinal = ? WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setFloat(1, nota);
            ps.setInt(2, a.getIdAlumno());
            ps.setInt(3, m.getIdMateria());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en actualizarNota.");
        }
    }
    
    public List<Materia> obtenerMateriasInscriptas(Alumno a){
       // Inscripcion i = null;
        Materia m = null;
        List<Materia> lista = new ArrayList<>();
        try{
            String q = "SELECT * FROM inscripciones WHERE idAlumno= ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               // i = new Inscripcion();
              //  i.setAlumno(a);
                m = new Materia();
                m.setNombre(rs.getString("nombre"));
                m.setPeriodo(rs.getInt("periodo"));
                m.setEstado(rs.getInt("estado"));
                m.setIdMateria(rs.getInt("idMateria"));                
                lista.add(m);
            }
            rs.close();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerMateriasInscriptas.");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasNoInscriptas(Alumno a){
        Materia m = null;
        List<Materia> lista = new ArrayList<>();
        try{
            String q = "SELECT * "
                    + "FROM materias "
                    + "WHERE idMateria NOT IN ( "
                    + "SELECT idMateria "
                    + "FROM inscripciones "
                    + "WHERE inscripciones.idAlumno = ?) "
                    + "AND estado = 1";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = new Materia();
                m.setNombre(rs.getString("nombre"));
                m.setPeriodo(rs.getInt("periodo"));
                m.setEstado(rs.getInt("estado"));
                m.setIdMateria(rs.getInt("idMateria"));                
                lista.add(m);
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerMateriasNoInscriptas.");
        }
        return lista;
    }
    
    public List<Inscripcion> obtenerAlumnosInscriptos(Materia m){
        Inscripcion i = null;
        Alumno a = null;
        List<Inscripcion> lista = new ArrayList<>();
        try{
            String q = "SELECT alumnos.*, inscripciones.notaFinal "
                    + "FROM inscripciones "
                    + "JOIN alumnos ON alumnos.idAlumno= inscripciones.idAlumno "
                    + "WHERE inscripciones.idMateria= ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, m.getIdMateria());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i = new Inscripcion();
                i.setMateria(m);
                a = new Alumno();
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setDni(rs.getString("dni"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getInt("estado"));
                a.setIdAlumno(rs.getInt("idAlumno"));                
                i.setAlumno(a);
                i.setNotaFinal(rs.getFloat("notaFinal"));
                lista.add(i);
            }
            rs.close();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerAlumnosInscriptos.");
        }
        return lista;
    }
}
