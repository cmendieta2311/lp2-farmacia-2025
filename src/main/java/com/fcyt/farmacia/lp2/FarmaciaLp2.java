/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.fcyt.farmacia.lp2;

import com.fcyt.farmacia.lp2.controlador.RolController;
import com.fcyt.farmacia.lp2.modelo.dao.RolDaoImpl;
import com.fcyt.farmacia.lp2.vista.GUIRol;

/**
 *
 * @author cmendieta
 */
public class FarmaciaLp2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        RolDaoImpl rolDao = new RolDaoImpl();
        GUIRol gui = new GUIRol(null, true);
        RolController ctrl = new RolController(gui, rolDao);
        //Rol rol = rolDao.getRolById(1);
//        System.out.println(rol.getNombre() +", " +rol.getDescripcion());
    }
}
