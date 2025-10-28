/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.tabla;

import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cmendieta
 */
public class ProductoTablaModel extends AbstractTableModel {

    List<Producto> lista;
    private String[] columnas = {"NOMBRE", "DESCRIPCION", "PRECIO COMPRA", "PRECIO PUBLICO", "PRECIO VENTA"};

    public void setLista(List<Producto> lista) {
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

    public Producto getProductoByRow(int fila) {
        return lista.get(fila);
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Producto item = lista.get(fila);
        switch (columna) {
            case 0:
                return item.getNombre();
            case 1:
                return item.getDescripcion();
            case 2:
                return item.getPrecioCompra();
            case 3:
                return item.getPrecioPublico();
            case 4:
                return item.getPrecioVenta();
            default:
                return null;
        }

    }

}
