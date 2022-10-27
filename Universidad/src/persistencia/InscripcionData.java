/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.sun.istack.internal.logging.Logger;
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
    private MiConexion mCon = null;

    public InscripcionData(MiConexion conn) {
        this.mCon = conn;
        this.conn = conn.buscarConexion();
    }
    
    public void guardarInscripcion(Inscripcion i){
        String q = "INSERT INTO inscripciones(alumnoID, materiaID) VALUES (?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, i.getAlumno().getIdAlumno());
            ps.setInt(2, i.getMateria().getIdMateria());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                i.setIdInscripcion(rs.getInt(1));
            } else {
                System.out.println("No se puede obtener un ID");
            }
            ps.close();
            
        } catch(SQLException ex) {
            Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
        }
    }
        
    public Inscripcion obtenerInscripcion(Alumno a, Materia m){
        Inscripcion i = null;
        AlumnoData aData = new AlumnoData(mCon);
        MateriaData mData = new MateriaData(mCon);
        try{
            String q = "SELECT * FROM inscripciones WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ps.setInt(2, m.getIdMateria());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i = new Inscripcion();
                i.setAlumno( aData.obtenerAlumno(rs.getInt("idAlumno")) );
                i.setMateria( mData.obtenerMateria(rs.getInt("idMateria")) );
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
            ps.executeQuery();
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
            ps.executeQuery();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en actualizarNota.");
        }
    }
    
    public List<Materia> obtenerMateriasInscriptas(Alumno a){
        Materia m = null;
        List<Materia> lista = new ArrayList<>();
        MateriaData mData = new MateriaData(mCon);
        try{
            String q = "SELECT * FROM inscripciones WHERE idAlumno = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = mData.obtenerMateria(rs.getInt("idMateria"));
                lista.add(m);
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerMateriasInscriptas.");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasNoInscriptos(Alumno a){
        Materia m = null;
        List<Materia> lista = new ArrayList<>();
        MateriaData mData = new MateriaData(mCon);
        try{
            String q = "SELECT * FROM inscripciones WHERE idAlumno = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, a.getIdAlumno());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = mData.obtenerMateria(rs.getInt("idMateria"));
                lista.add(m);
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerMateriasNoInscriptos.");
        }
        return lista;
    }
    
    public List<Alumno> obtenerAlumnosInscriptos(Materia m){
        Alumno a = null;
        List<Alumno> lista = new ArrayList<>();
        AlumnoData aData = new AlumnoData(mCon);
        try{
            String q = "SELECT * FROM inscripciones WHERE idMateria = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, m.getIdMateria());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a = aData.obtenerAlumno(rs.getInt("idAlumno"));
                lista.add(a);
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerAlumnosInscriptos.");
        }
        return lista;
    }
}
