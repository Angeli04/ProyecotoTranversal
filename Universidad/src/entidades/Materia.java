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
public class Materia {
    
    private int idMateria;
    private String nombre;
    private int periodo;
    private int estado; 

    public Materia() {}

    public Materia(String nombre, int periodo, int estado) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
         return "ID: "+this.getIdMateria()+" Materia: "+this.getNombre()+" Periodo: "+this.getPeriodo();
    }
    
}
