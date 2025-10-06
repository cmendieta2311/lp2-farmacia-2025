/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cmendieta
 */
public class Conexion {

    //jdbc:postgresql://<host>:<puerto>/<nombre_base_datos>
    String url = "jdbc:postgresql://localhost:5432/farmacia";
    String usuario = "postgres";
    String contraseña = "12345";

    // Conectar a BD
    public Connection conectarBD() throws SQLException {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection(url, usuario, contraseña);

        } catch (Exception e) {
            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, url);
            System.out.println("Error al conectar a la BD");
        }
        return conectar;
    }
}
