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
    List<Inscripcion> inscripciones = null;
    
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
            System.out.println("6. Eliminar materia");
            System.out.println("7. Crear inscripcion");
            System.out.println("8. Colocar nota final");
            System.out.println("9. Ver alumnos inscriptos a una materia");
            System.out.println("0. Salir");
            System.out.println("Elija opción:");
            opcion = sc.nextInt();
            switch(opcion) {
                case 1:
                    this.listaAlumnos();
                    System.out.println("Que alumno desea cargar");
                    System.out.println("1 - Barrios Juan 32000001 15/01/2000");
                    System.out.println("2 - Lucero Melisa 32000002 25/04/2002");
                    System.out.println("3 - Paez Carlos 32000003 30/09/2003");
                    System.out.println("4 - Zarate Mariana 32000004 18/02/2001");
                    opcion = sc.nextInt();
                    switch( opcion ) {
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
                    this.listaAlumnos();
                    break;
                case 2:
                    System.out.println("Editar el nombre de un alumno");
                    System.out.println("Seleccione un alumno");
                    this.listaAlumnos();
                    alumno = alumnoData.obtenerAlumno(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre");
                    alumno.setNombre(sc.nextLine());
                    alumnoData.actualizarAlumno(alumno);
                    this.listaAlumnos();
                    break;
                case 3:
                    System.out.println("Eliminar un alumno");
                    System.out.println("Seleccione un alumno");
                    this.listaAlumnos();
                    alumnoData.eliminarAlumno(sc.nextInt());
                    this.listaAlumnos();
                    break;
                case 4:
                    this.listaMaterias();
                    System.out.println("Que materia desea cargar");
                    System.out.println("1 - Matemáticas I");
                    System.out.println("2 - Matemáticas II");
                    System.out.println("3 - Móviles");
                    System.out.println("4 - Práctica profesional");
                    opcion = sc.nextInt();
                    switch( opcion ) {
                        case 1:
                            materia = new Materia("Matemáticas I", 1, 1);
                            break;
                        case 2:
                            materia = new Materia("Matemáticas II", 2, 1);
                            break;
                        case 3:
                            materia = new Materia("Móviles", 2, 1);
                            break;
                        case 4:
                            materia = new Materia("Práctica profesional", 3, 1);
                            break;
                    }                    
                    materiaData.guardarMateria(materia);
                    this.listaMaterias();
                    break;
                case 5:
                    System.out.println("Editar el nombre de un materia");
                    System.out.println("Seleccione una materia");
                    this.listaMaterias();
                    materia = materiaData.obtenerMateria(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre");
                    materia.setNombre(sc.nextLine());
                    materiaData.actualizarMateria(materia);
                    this.listaMaterias();
                    break;
                case 6:
                    System.out.println("Eliminar una materia");
                    System.out.println("Seleccione una materia");
                    this.listaMaterias();
                    materiaData.eliminarMateria(sc.nextInt());
                    this.listaMaterias();
                    break;
                case 7:
                    System.out.println("Crear una inscripción");
                    System.out.println("Seleccione un alumno");
                    this.listaAlumnos();
                    alumno = alumnoData.obtenerAlumno(sc.nextInt());
                    sc.nextLine();
                    System.out.println(alumno.toString());
                    System.out.println("Seleccione una materia");
                    this.listaMaterias();
                    materia = materiaData.obtenerMateria(sc.nextInt());
                    sc.nextLine();
                    System.out.println(materia.toString());
                    inscripcion = new Inscripcion();
                    inscripcion.setAlumno(alumno);
                    inscripcion.setMateria(materia);
                    inscripcionData.guardarInscripcion(inscripcion);
                    this.listaInscripcionesAlumno(alumno);
                    break;
                case 8:
                    System.out.println("Colocar nota final");
                    System.out.println("Seleccione un alumno");
                    this.listaAlumnos();
                    alumno = alumnoData.obtenerAlumno(sc.nextInt());
                    sc.nextLine();
                    System.out.println(alumno.toString());
                    System.out.println("Seleccione una materia");
                    this.listaInscripcionesAlumno(alumno);
                    materia = materiaData.obtenerMateria(sc.nextInt());
                    sc.nextLine();
                    float notaFinal = 0;
                    do{
                        System.out.println("Ingrese la nota");
                        notaFinal = sc.nextFloat();                        
                    } while(notaFinal < 0 || notaFinal > 10);
                    sc.nextLine();
                    inscripcionData.actualizarNota(alumno, materia, notaFinal);
                    this.listaInscripcionesAlumno(alumno);
                    break;
                case 9:
                    System.out.println("Ver alumnos inscriptos");
                    System.out.println("Seleccione una materia");
                    this.listaMaterias();
                    materia = materiaData.obtenerMateria(sc.nextInt());
                    sc.nextLine();
                    this.listaInscripcionesMateria(materia);
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }
    
    private void listaAlumnos(){
        System.out.println("<-------------------------------------->");
        System.out.println("Lista de alumnos");
        alumnos = alumnoData.listarAlumnos();
        alumnos.forEach((a) -> {
            System.out.println(a.toString());
        });
        System.out.println("<-------------------------------------->");        
    }
    
    private void listaMaterias(){
        System.out.println("<-------------------------------------->");
        System.out.println("Lista de materias");
        materias = materiaData.listarMaterias();
        materias.forEach((m) -> {
            System.out.println(m.toString());
        });
        System.out.println("<-------------------------------------->");        
    }
    
    private void listaInscripcionesAlumno(Alumno alu){
        System.out.println("<-------------------------------------->");
        System.out.println("Lista de inscripciones del alumno");
        System.out.println(alu.toString());
        System.out.println("<-------------------------------------->");    
        inscripciones = inscripcionData.obtenerMateriasInscriptas(alu);
        inscripciones.forEach((i) -> {
            System.out.println(i.getMateria().toString() +" nota: "+i.getNotaFinal());
        });
        System.out.println("<-------------------------------------->");        
    }

    private void listaInscripcionesMateria(Materia mat){
        System.out.println("<-------------------------------------->");
        System.out.println("Lista de inscripciones de la materia");
        System.out.println(mat.toString());
        System.out.println("<-------------------------------------->");    
        inscripciones = inscripcionData.obtenerAlumnosInscriptos(mat);
        inscripciones.forEach((i) -> {
            System.out.println(i.getAlumno().toString() +" nota: "+i.getNotaFinal());
        });
        System.out.println("<-------------------------------------->");        
    }    
}
