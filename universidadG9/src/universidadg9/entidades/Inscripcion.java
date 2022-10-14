
package universidadg9.entidades;

/**
 *
 * @author FRANCISCO
 */
public class Inscripcion {
private int idInscripcion;
private Alumno alumno;
private Materia materia;
private double notaFinal;

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Materia materia, double notaFinal) {
        this.alumno = alumno;
        this.materia = materia;
        this.notaFinal = notaFinal;
    }

    public Inscripcion(int idInscripcion, Alumno alumno, Materia materia, double notaFinal) {
        this.idInscripcion = idInscripcion;
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

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripcion=" + idInscripcion + ", alumno=" + alumno + ", materia=" + materia + ", notaFinal=" + notaFinal + '}';
    }

}
