/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import entidades.Alumno;
import entidades.Materia;
import java.time.LocalDate;
import java.util.List;
import persistencia.AlumnoData;
import persistencia.MiConexion;
import vistas.frmPrincipal;

/**
 *
 * @author PUESTO-A1
 */
public class Universidad {
    private AlumnoData alumnoData;
    private MiConexion conn;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //frmPrincipal framePrincipal = new frmPrincipal();
        //framePrincipal.setLocationRelativeTo(null);
        //framePrincipal.setVisible(true);
        new Universidad().conectar();
    }
    
    private void conectar(){
        conn = new MiConexion("jdbc:mysql://localhost/universidad", "root", "");
        alumnoData = new AlumnoData(conn);

        Alumno a = new Alumno("Estrada", "Lorena", "27822893", LocalDate.parse("1980-12-30"), 1);
        alumnoData.guardarAlumno(a);
        
        Alumno b = alumnoData.obtenerAlumno(2);
        System.out.println(b.toString());
        
        b.setApellido("Perez");
        alumnoData.actualizarAlumno(b);
        Alumno c = alumnoData.obtenerAlumno(2);
        System.out.println(c.toString());
        
        
    }
    
    public List<Alumno> obtenerAlumnos(){
        return null;
    }
    
    public List<Materia> obtenerMaterias(){
        return null;
    }
    
}
