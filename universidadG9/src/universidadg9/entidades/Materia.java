
package universidadg9.entidades;

/**
 *
 * @author FRANCISCO
 */
public class Materia {
    private int idMateria;
    private String nombre;
    private int periodo;
    private boolean estado;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, int periodo, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.periodo = periodo;
        this.estado = estado;
    }

    public Materia(String nombre, int periodo, boolean estado) {
        this.nombre = nombre;
        this.periodo = periodo;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", periodo=" + periodo + ", estado=" + estado + '}';
    }
    
}
