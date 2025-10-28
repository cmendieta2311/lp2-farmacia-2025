/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.fcyt.farmacia.lp2;

import com.fcyt.farmacia.lp2.controlador.CategoriaController;
import com.fcyt.farmacia.lp2.controlador.RolController;
import com.fcyt.farmacia.lp2.controlador.UnidadMedidaController;
import com.fcyt.farmacia.lp2.modelo.dao.CategoriaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.dao.RolDaoImpl;
import com.fcyt.farmacia.lp2.modelo.dao.UnidadMedidaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
import com.fcyt.farmacia.lp2.vista.GUICategoria;
import com.fcyt.farmacia.lp2.vista.GUIRol;
import com.fcyt.farmacia.lp2.vista.GUIUnidadMedida;
import com.fcyt.farmacia.lp2.vista.VentanaPrincipal;

/**
 *
 * @author cmendieta
 */
public class FarmaciaLp2 {

    public static void main(String[] args) {
        VentanaPrincipal main = new VentanaPrincipal();
        main.setVisible(true);
        

    }
}
