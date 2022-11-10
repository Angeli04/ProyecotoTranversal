/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author PUESTO-A1
 */
public class Inscripcion {
    
    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private float notaFinal; 

    public Inscripcion() {
    }
    
    

    public Inscripcion(Alumno alumno, Materia materia) {
        this.alumno= alumno;
        this.materia= materia;  
    }
    
    public Inscripcion(Alumno alumno, Materia materia, float notaFinal) {
        this.alumno = alumno;
        this.materia = materia;
        this.notaFinal = notaFinal;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(float notaFinal) {
        this.notaFinal = notaFinal;
    }
    
    
}
