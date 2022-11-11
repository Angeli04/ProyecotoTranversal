/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Inscripcion;
import entidades.Materia;
import entidades.Materia;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.AlumnoData;
import persistencia.InscripcionData;
import persistencia.MateriaData;


public class friConsultas extends javax.swing.JInternalFrame {

    private AlumnoData alumnoData;
    private MateriaData materiaData;
    private ArrayList<Materia> materias;
    private InscripcionData inscripcionData;
    private DefaultTableModel modelo;
    private InscripcionData insData;
    
    public friConsultas() {
        
        initComponents();
        insData= new InscripcionData();
        modelo= new DefaultTableModel();
        materiaData= new MateriaData();
        materias= new ArrayList();
        materias=(ArrayList) materiaData.listarMaterias();
        cargarCombo();
        cabecera();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ComMateria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();

        jLabel1.setText("LISTADO DE ALUMNOS POR MATERIA");

        jLabel2.setText("Materias");

        ComMateria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComMateriaItemStateChanged(evt);
            }
        });
        ComMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComMateriaActionPerformed(evt);
            }
        });

        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTabla);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(ComMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ComMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComMateriaActionPerformed
       cargarTabla();
    }//GEN-LAST:event_ComMateriaActionPerformed

    private void ComMateriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComMateriaItemStateChanged
       //cargarTabla();
    }//GEN-LAST:event_ComMateriaItemStateChanged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Materia> ComMateria;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabla;
    // End of variables declaration//GEN-END:variables

    private void cargarCombo() {
        ComMateria.addItem(null);
        materias=(ArrayList) materiaData.listarMaterias();
        for (Materia materia : materias) {
            ComMateria.addItem(materia);
            
        }
    }

    private void cabecera() {
       ArrayList<Object> columnas= new ArrayList();
       columnas.add("ID");
       columnas.add("Nombre");
       columnas.add("Apellido");
       columnas.add("DNI");
       columnas.add("Fecha de nacimiento");
        for (Object ob : columnas) {
            modelo.addColumn(ob);
            
        }
        jTabla.setModel(modelo);
    }

    private void cargarTabla() {
       borrarFilasTabla();
       Materia selec= new Materia();
       selec= (Materia) ComMateria.getSelectedItem();
       if(selec!=null){
       ArrayList <Inscripcion> ins= new ArrayList();
       ins= (ArrayList)insData.obtenerAlumnosInscriptos(selec);
       if(ins.isEmpty()){
           JOptionPane.showMessageDialog(this, "No hay inscriptos en esta materia");
       }
       for (Inscripcion in : ins) {
            modelo.addRow(new Object[]{in.getAlumno().getIdAlumno(),
            in.getAlumno().getNombre(),
            in.getAlumno().getApellido(),
            in.getAlumno().getDni(),
            in.getAlumno().getFechaNacimiento()});
        }
       jTabla.setModel(modelo);
      
    }
    }
    private void borrarFilasTabla() {
        if (modelo != null) {
            int a = modelo.getRowCount() - 1;

            for (int i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
}
}