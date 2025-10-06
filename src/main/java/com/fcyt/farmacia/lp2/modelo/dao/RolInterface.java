/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import com.fcyt.farmacia.lp2.modelo.entidad.Rol;
import java.util.List;





/**
 *
 * @author cmendieta
 */
public interface RolInterface {
    void crearRol(Rol rol);
    Rol getRolById(Integer id);
    List<Rol>  listarRol(String valor);
    void actualizar(Rol rol);
    void eliminar(Integer id);
}
