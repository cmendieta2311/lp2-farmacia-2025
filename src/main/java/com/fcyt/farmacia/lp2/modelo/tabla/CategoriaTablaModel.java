/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.tabla;

import com.fcyt.farmacia.lp2.modelo.entidad.Categoria;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cmendieta
 */
public class CategoriaTablaModel extends AbstractTableModel{
    List<Categoria> lista;
    private String[] columnas = {"NOMBRE", "DESCRIPCION"};
    
    public void setLista(List<Categoria> lista){
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {

      return columnas.length;
    }
    
    public Categoria getCategoriaByRow(int fila){
        return lista.get(fila);
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Categoria rol = lista.get(fila);
        switch (columna) {
            case 0: 
                return rol.getNombre();
            case 1:
                return rol.getDescripcion();
            default:
                return null;
        }
    
    }
    
}
