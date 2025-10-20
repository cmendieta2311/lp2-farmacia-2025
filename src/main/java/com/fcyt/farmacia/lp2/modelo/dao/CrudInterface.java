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
 * Interface: especifica que metodos debe estar presentes
 */
public interface CrudInterface<T> {
    void crear(T obj);
    T getById(Integer id);
    List<T>  listar(String valor);
    void actualizar(T obj);
    void eliminar(Integer id);
}
