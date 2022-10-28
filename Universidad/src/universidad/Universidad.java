/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import persistencia.AlumnoData;
import persistencia.InscripcionData;
import persistencia.MateriaData;
import persistencia.MiConexion;
import vistas.frmPrincipal;

/**
 *
 * @author PUESTO-A1
 */
public class Universidad {
    private AlumnoData alumnoData;
    private MateriaData materiaData;
    private InscripcionData inscripcionData;
    
    Alumno alumno = null;
    Materia materia = null;
    Inscripcion inscripcion = null;
    List<Alumno> alumnos = null;
    List<Materia> materias = null;
    
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
        materiaData = new MateriaData(conn);
        inscripcionData = new InscripcionData(conn);
        
        //Alumno a = new Alumno("Medina", "Mariano", "30577891", LocalDate.parse("1980-12-30"), 1);
        //alumnoData.guardarAlumno(a);
        
        /*
        Alumno b = alumnoData.obtenerAlumno(1);
        System.out.println(b.toString());
        b.setNombre("Fabricio");
        b.setApellido("Perez");
        alumnoData.actualizarAlumno(b);
        System.out.println(b.toString());
        
        Alumno c = alumnoData.obtenerAlumnoXDni("27822898");
        System.out.println(c.toString());
        
        alumnoData.eliminarAlumno(3);
        */
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean exit = false;
       
        do {
            System.out.println("1. Crear alumno");
            System.out.println("2. Actualizar alumno");
            System.out.println("3. Eliminar alumno");
            System.out.println("4. Crear materia");
            System.out.println("5. Actualizar materia");
            System.out.println("6. Crear inscripcion");
            System.out.println("7. Colocar nota final");
            System.out.println("0. Salir");
            System.out.println("Elija opción:");
            opcion = sc.nextInt();
            switch(opcion) {
                case 1:
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    System.out.println("Que alumno desea cargar");
                    System.out.println("1 - Barrios Juan 32000001 15/01/2000");
                    System.out.println("2 - Lucero Melisa 32000002 25/04/2002");
                    System.out.println("3 - Paez Carlos 32000003 30/09/2003");
                    System.out.println("4 - Zarate Mariana 32000004 18/02/2001");
                    switch(sc.nextInt()) {
                        case 1:
                            alumno = new Alumno("Barrios", "Juan", "32000001", LocalDate.parse("2000-01-15"), 1);
                            break;
                        case 2:
                            alumno = new Alumno("Lucero", "Melisa", "32000002", LocalDate.parse("2002-04-25"), 1);
                            break;
                        case 3:
                            alumno = new Alumno("Paez", "Carlos", "32000003", LocalDate.parse("2003-09-30"), 1);
                            break;
                        case 4:
                            alumno = new Alumno("Zarate", "Mariana", "32000004", LocalDate.parse("2001-02-18"), 1);
                            break;
                    }                    
                    alumnoData.guardarAlumno(alumno);
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    break;
                case 2:
                    System.out.println("Editar el nombre de un alumno");
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    alumno = alumnos.get(sc.nextInt());
                    alumno.setNombre(sc.nextLine());
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    break;
                case 3:
                    System.out.println("Eliminar un alumno");
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    alumnoData.eliminarAlumno(sc.nextInt());
                    System.out.println("<-------------------------------------->");
                    System.out.println("Lista de alumnos");
                    alumnos = alumnoData.listarAlumnos();
                    alumnos.forEach((a) -> {
                        System.out.println(alumnos.indexOf(a) + " : "  + a.getApellido() + " " + a.getNombre() + " DNI: " +a.getDni() );
                    });
                    System.out.println("<-------------------------------------->");
                    break;
                case 4:
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
