
package universidadg9.entidades;

import java.time.LocalDate;

/**
 *
 * @author FRANCISCO
 */
public class Alumno {
    private int idAlumno;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private boolean estado;

    public Alumno() {
    }

    public Alumno(int idAlumno, String apellido, String dni, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = idAlumno;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Alumno(String apellido, String dni, LocalDate fechaNacimiento, boolean estado) {
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", apellido=" + apellido + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + '}';
    }
  
    
}
