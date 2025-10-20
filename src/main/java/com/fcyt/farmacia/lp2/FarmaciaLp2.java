/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.fcyt.farmacia.lp2;

import com.fcyt.farmacia.lp2.controlador.CategoriaController;
import com.fcyt.farmacia.lp2.controlador.RolController;
import com.fcyt.farmacia.lp2.modelo.dao.CategoriaDaoImpl;
import com.fcyt.farmacia.lp2.modelo.dao.RolDaoImpl;
import com.fcyt.farmacia.lp2.vista.GUICategoria;
import com.fcyt.farmacia.lp2.vista.GUIRol;

/**
 *
 * @author cmendieta
 */
public class FarmaciaLp2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        RolDaoImpl rolDao = new RolDaoImpl();
//        GUIRol gui = new GUIRol(null, true);
//        RolController ctrl = new RolController(gui, rolDao);
        CategoriaDaoImpl rolDao = new CategoriaDaoImpl();
        GUICategoria gui = new GUICategoria(null, true);
        CategoriaController ctrl = new CategoriaController(gui, rolDao);

    }
}
