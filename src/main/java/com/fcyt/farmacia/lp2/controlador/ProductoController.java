/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fcyt.farmacia.lp2.controlador;

import com.fcyt.farmacia.lp2.modelo.dao.CategoriaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
import com.fcyt.farmacia.lp2.modelo.dao.ProductoDaoImpl;
import com.fcyt.farmacia.lp2.modelo.dao.ProductoDaoImpl;
import com.fcyt.farmacia.lp2.modelo.dao.UnidadMedidaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.entidad.Categoria;
import com.fcyt.farmacia.lp2.modelo.entidad.UnidadMedida;
import com.fcyt.farmacia.lp2.modelo.tabla.ProductoTablaModel;
import com.fcyt.farmacia.lp2.vista.GUIProducto;
import com.fcyt.farmacia.lp2.vista.GUIProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author cmendieta
 */
public class ProductoController implements ActionListener {

    private GUIProducto gui;
    private ProductoDaoImpl crud;
    private char operacion; // N, M, E

    private Producto producto = new Producto();
    
    CategoriaDaoImpl crudCategoria = new CategoriaDaoImpl();
    UnidadMedidaDaoImpl crudUnidad =  new UnidadMedidaDaoImpl();

    ProductoTablaModel modelo = new ProductoTablaModel();

    public ProductoController(GUIProducto gui, ProductoDaoImpl crud) {
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
                ProductoTablaModel model = (ProductoTablaModel) tabla.getModel();
                producto = model.getProductoByRow(row);
                System.out.println(producto.getNombre());
                setProductoForm(producto);
            }
        });
        llenarComboCategoria(gui.jcboCategoria);
        llenarComboUnidadMedida(gui.jcboUnidadMedida);
        listar("");
        habilitarBotones(false);
        this.gui.setLocationRelativeTo(gui);
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
                    crud.eliminar(modelo.getProductoByRow(fila).getId());
                    listar("");
                }
            }

        }

        if (ae.getSource() == gui.btnGuardar) {
            boolean v_contproducto = validarDatos();
            if(v_contproducto ==  true){
                JOptionPane.showMessageDialog(gui, "Favor completar los datos obligatorio");
                return;
            }
            System.out.println("click en el boton Guardar");
            if (operacion == 'N') {
                System.out.println("ACCION DE INSERT");
                crud.crear(getProductoForm());
            }

            if (operacion == 'M') {
                System.out.println("ACCION DE MODIFICAR");
                // LOGICA PARA MODIFICAR
                crud.actualizar(getProductoForm());
            }
            limpiar();
            listar("");

        }

    }

    private void listar(String valor) {
        List<Producto> lista = crud.listar(valor);
        modelo.setLista(lista);
        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();
    }

    // recuperar los datos del formulario
    private Producto getProductoForm() {
        System.out.println("producto" + producto.getId());
        producto.setNombre(this.gui.txtNombre.getText());
        producto.setDescripcion(this.gui.txtDescripcion.getText());
        return producto;
    }

    // mostrar datos en el formulario
    private void setProductoForm(Producto producto) {
        gui.txtNombre.setText(producto.getNombre());
        gui.txtDescripcion.setText(producto.getDescripcion());
        if(producto.getEsMedicamento()){
            gui.btnEsMedSi.setSelected(true);
        }else{
            gui.btnEsMedNo.setSelected(true);
        }
        gui.jcboCategoria.setSelectedItem(producto.getCategoria());
        gui.jcboUnidadMedida.setSelectedItem(producto.getUnidadMedida());
        
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
    
    private void llenarComboCategoria (JComboBox cbo){
        DefaultComboBoxModel<Categoria> model =  new DefaultComboBoxModel();
        
        List<Categoria> lista = crudCategoria.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Categoria categoria = lista.get(i);
            model.addElement(categoria);
        }
        
        cbo.setModel(model); 
    }
    
     private void llenarComboUnidadMedida (JComboBox cbo){
        DefaultComboBoxModel<UnidadMedida> model =  new DefaultComboBoxModel();
        
        List<UnidadMedida> lista = crudUnidad.listar("");
        for (int i = 0; i < lista.size(); i++) {
            UnidadMedida unidad = lista.get(i);
            model.addElement(unidad);
        }
        
        cbo.setModel(model); 
    }

}
