/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.tabla;

import com.fcyt.farmacia.lp2.modelo.entidad.UnidadMedida;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cmendieta
 */
public class UnidadMedidaTablaModel extends AbstractTableModel{
    List<UnidadMedida> lista;
    private String[] columnas = {"NOMBRE", "ABREVIATURA"};
    
    public void setLista(List<UnidadMedida> lista){
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
    
    public UnidadMedida getUnidadMedidaByRow(int fila){
        return lista.get(fila);
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        UnidadMedida rol = lista.get(fila);
        switch (columna) {
            case 0: 
                return rol.getNombre();
            case 1:
                return rol.getAbreviatura();
            default:
                return null;
        }
    
    }
    
}
