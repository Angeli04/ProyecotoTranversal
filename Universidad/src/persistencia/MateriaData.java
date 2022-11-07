/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

//import com.sun.istack.internal.logging.Logger;
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
public class MateriaData {
    private Connection conn = null;

    public MateriaData(MiConexion conn) {
        this.conn = conn.buscarConexion();
    }
    public MateriaData(){
        conn= MiConexion.buscarConexion();
    }
    
    public void guardarMateria(Materia m){
        String q = "INSERT INTO materias(nombre, periodo) VALUES (?,?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getPeriodo());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                m.setIdMateria(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el ID.");
            }
            ps.close();
            
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo obtener el ID.");
        }
    }
    
    public Materia obtenerMateria(int id){
        Materia m = null;

        try{
            String q = "SELECT * FROM materias WHERE materias.idMateria= ?";
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
    }
    
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
    }
    
    public void actualizarMateria(Materia m){
        String q = "UPDATE materias SET nombre = ?, periodo = ? WHERE idMateria= ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getPeriodo());
            ps.setInt(3, m.getIdMateria());
            ps.executeUpdate();
            ps.close();
            
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en actualizarMateria.");
        }
    }
    
    public void eliminarMateria(int id){
        String q = "DELETE FROM materias WHERE idMateria= ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch(SQLException ex) {
            //Logger.getLogger(MateriaData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en eliminarMateria.");
        }
    }
}
