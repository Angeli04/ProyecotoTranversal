/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PUESTO-A1
 */
public class MateriaData {
    private Connection conn = null;

    public MateriaData() {
        this.conn = MiConexion.getConexion();
    }
    
    public void guardarMateria(Materia m){
        String q = "INSERT INTO materias(nombre, periodo, estado) VALUES (?,?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getPeriodo());
            ps.setInt(3, m.getEstado());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Materia agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no pudo ser agregada.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                m.setIdMateria(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el ID.");
            }
            rs.close();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex);
        }
    } // guarda una materia nueva.
    
    public Materia obtenerMateria(int id){
        Materia m = null;

        try{
            String q = "SELECT * FROM materias WHERE materias.idMateria= ? AND estado=1";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = new Materia();
                m.setNombre(rs.getString("nombre"));
                m.setPeriodo(rs.getInt("periodo"));
                m.setEstado(rs.getInt("estado"));
                m.setIdMateria(rs.getInt("idMateria"));
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerMateria.");
        }
        return m;
    } // retorna una materia por id
    
    public List<Materia> listarMaterias(){
        Materia m = null;
        List<Materia> materias = new ArrayList<>();
        String q = "SELECT * FROM materias";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = new Materia();
                m.setNombre(rs.getString("nombre"));
                m.setPeriodo(rs.getInt("periodo"));
                m.setEstado(rs.getInt("estado"));
                m.setIdMateria(rs.getInt("idMateria"));
                materias.add(m);
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en listarMaterias.");
        }
        return materias;
    } //retorna todas las materias
    
    public void actualizarMateria(Materia m){
        String q = "UPDATE materias SET nombre = ?, periodo = ? WHERE idMateria= ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getPeriodo());
            ps.setInt(3, m.getIdMateria());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Materia actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no pudo ser actualizada.");
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en actualizarMateria.");
        }
    } // update de materia
    
    public void eliminarMateria(int id){
        String q = "UPDATE materias SET estado = 0 WHERE idMateria = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Materia eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no pudo ser eliminada.");
            }
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en eliminarMateria.");
        }
    } // borra la materia.
}
