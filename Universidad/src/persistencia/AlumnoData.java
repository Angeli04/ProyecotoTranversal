/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
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
public class AlumnoData {

    private Connection conn = null;

    public AlumnoData() {
        this.conn = MiConexion.getConexion();
    }

    public void guardarAlumno(Alumno a) {
        String q = "INSERT INTO alumnos(apellido, nombre, dni, fechaNacimiento, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(5, 1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no pudo ser agregado.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAlumno(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el ID.");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en guardarAlumno.");
        }
    }

    public Alumno obtenerAlumno(int id) {
        Alumno a = null;
        try {
            String q = "SELECT * FROM alumnos WHERE alumnos.idAlumno = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setDni(rs.getString("dni"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getInt("estado"));
                a.setIdAlumno(rs.getInt("idAlumno"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerAlumno.");
        }
        return a;
    }

    public Alumno obtenerAlumnoXDni(String dni) {
        Alumno a = null;
        try {
            String q = "SELECT * FROM alumnos WHERE alumnos.dni= ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setDni(rs.getString("dni"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getInt("estado"));
                a.setIdAlumno(rs.getInt("idAlumno"));
            }
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en obtenerAlumnoXDni.");
        }
        return a;
    }

    public List<Alumno> listarAlumnos() {
        Alumno a;
        List<Alumno> alumnos = new ArrayList<>();
        String q = "SELECT * FROM alumnos WHERE estado = 1 ORDER BY apellido DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setDni(rs.getString("dni"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getInt("estado"));
                a.setIdAlumno(rs.getInt("idAlumno"));
                alumnos.add(a);
            }
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en listarAlumnos.");
        }
        return alumnos;
    }

    public void actualizarAlumno(Alumno a) {
        String q = "UPDATE alumnos SET apellido = ?, nombre = ?, dni = ?, fechaNacimiento = ? WHERE idAlumno = ? AND estado = 1";

        try {
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(5, a.getIdAlumno());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no pudo ser actualizado.");
            }
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en actualizarAlumno.");
        }
    }

    public void eliminarAlumno(int id) {
        //String q = "DELETE FROM alumnos WHERE idAlumno = ?";
        String q = "UPDATE alumnos SET estado = 0 WHERE idAlumno = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no pudo ser eliminado.");
            }            
            ps.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error SQL en eliminarAlumno.");
        }
    }
}
