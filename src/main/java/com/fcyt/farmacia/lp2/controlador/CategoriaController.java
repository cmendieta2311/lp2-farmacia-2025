/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.controlador;

import com.fcyt.farmacia.lp2.modelo.entidad.Categoria;
import com.fcyt.farmacia.lp2.modelo.dao.CategoriaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.tabla.CategoriaTablaModel;
import com.fcyt.farmacia.lp2.vista.GUICategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author cmendieta
 */
public class CategoriaController implements ActionListener {

    private GUICategoria gui;
    private CategoriaDaoImpl crud;
    private char operacion; // N, M, E

    private Categoria rol = new Categoria();

    CategoriaTablaModel modelo = new CategoriaTablaModel();

    public CategoriaController(GUICategoria gui, CategoriaDaoImpl crud) {
        this.gui = gui;
        this.crud = crud;
        this.gui.btnGuardar.addActionListener(this);
        this.gui.btnCancelar.addActionListener(this);
        this.gui.btnNuevo.addActionListener(this);
        this.gui.btnEditar.addActionListener(this);
        this.gui.btnEliminar.addActionListener(this);
        this.gui.txtBuscar.addActionListener(this);
        this.gui.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("click en la tabla");
                JTable tabla = (JTable) e.getSource();
                int row = tabla.rowAtPoint(e.getPoint());
                CategoriaTablaModel model = (CategoriaTablaModel) tabla.getModel();
                rol = model.getCategoriaByRow(row);
                System.out.println(rol.getNombre());
                setCategoriaForm(rol);
            }
        });
        listar("");
        habilitarBotones(false);
        this.gui.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == gui.txtBuscar) {
            System.out.print("buscar");
            String valor = gui.txtBuscar.getText();
            listar(valor);
        }

        if (ae.getSource() == gui.btnNuevo) {
            System.out.println("click en el boton NUEVO");
            operacion = 'N';
            habilitarCampos(true);
            habilitarBotones(true);
            limpiar();

        }

        if (ae.getSource() == gui.btnCancelar) {
            habilitarCampos(false);
            habilitarBotones(false);
            limpiar();
        }

        if (ae.getSource() == gui.btnEditar) {
            System.out.println("click en el boton EDITAR");
            operacion = 'M';
            habilitarCampos(true);
            habilitarBotones(true);

        }

        if (ae.getSource() == gui.btnEliminar) {
            System.out.println("click en el boton ELIMINAR");
            int fila = gui.tabla.getSelectedRow();
            if (fila > 0) {
                int ok = JOptionPane.showConfirmDialog(gui,
                        "Realmente desea eliminar el registro?", "Atención",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (ok == 0) {
                    crud.eliminar(modelo.getCategoriaByRow(fila).getId());
                    listar("");
                }
            }

        }

        if (ae.getSource() == gui.btnGuardar) {
            boolean v_control = validarDatos();
            if(v_control ==  true){
                JOptionPane.showMessageDialog(gui, "Favor completar los datos obligatorio");
                return;
            }
            System.out.println("click en el boton Guardar");
            if (operacion == 'N') {
                System.out.println("ACCION DE INSERT");
                crud.crear(getCategoriaForm());
            }

            if (operacion == 'M') {
                System.out.println("ACCION DE MODIFICAR");
                // LOGICA PARA MODIFICAR
                crud.actualizar(getCategoriaForm());
            }
            limpiar();
            listar("");

        }

    }

    private void listar(String valor) {
        List<Categoria> lista = crud.listar(valor);
        modelo.setLista(lista);
        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();
    }

    // recuperar los datos del formulario
    private Categoria getCategoriaForm() {
        System.out.println("rol" + rol.getId());
        rol.setNombre(this.gui.txtNombre.getText());
        rol.setDescripcion(this.gui.txtDescripcion.getText());
        return rol;
    }

    // mostrar datos en el formulario
    private void setCategoriaForm(Categoria rol) {
        gui.txtNombre.setText(rol.getNombre());
        gui.txtDescripcion.setText(rol.getDescripcion());
    }

    //Funcion encargado de limpiar el formulario
    private void limpiar() {
        gui.txtNombre.setText("");
        gui.txtDescripcion.setText("");
    }

    private void habilitarCampos(Boolean estado) {
        gui.txtNombre.setEnabled(estado);
        gui.txtDescripcion.setEnabled(estado);
    }

    private void habilitarBotones(Boolean estado) {
        gui.btnGuardar.setEnabled(estado);
        gui.btnCancelar.setEnabled(estado);
    }
    
    private boolean validarDatos(){
        boolean vacio =  false;
        if (gui.txtNombre.getText().isEmpty()) {
            vacio = true;
            gui.txtNombre.requestFocus();
        }
        return vacio;
    }

}
