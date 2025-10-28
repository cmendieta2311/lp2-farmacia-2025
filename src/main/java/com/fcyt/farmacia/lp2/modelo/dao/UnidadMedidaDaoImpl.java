/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import com.fcyt.farmacia.lp2.modelo.entidad.UnidadMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cmendieta
 */
public class UnidadMedidaDaoImpl implements CrudInterface<UnidadMedida> {

    Connection conec;
    PreparedStatement sentencia;

    public UnidadMedidaDaoImpl() {
        Conexion conexionDB = new Conexion();
        try {
            conec = conexionDB.conectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crear(UnidadMedida unidadmedida) {
        String sql = "insert into unidad_medida (nombre,abreviatura)values(?,?)";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, unidadmedida.getNombre());
            sentencia.setString(2, unidadmedida.getAbreviatura());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "delete from unidad_medida where id=? ";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public UnidadMedida getById(Integer id) {
        UnidadMedida unidadmedida = null;
        String sql = "select * from unidad_medida where id = ?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                unidadmedida = new UnidadMedida();
                unidadmedida.setId(rs.getInt("id"));
                unidadmedida.setNombre(rs.getString("nombre"));
                unidadmedida.setAbreviatura(rs.getString("abreviatura"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidadmedida;
    }

    @Override
    public List listar(String valor) {
        String sql = "select * from unidad_medida where nombre ilike ? order by id asc";
        ArrayList lista = new ArrayList();
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + valor + "%");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                UnidadMedida unidadmedida = new UnidadMedida();
                unidadmedida.setId(rs.getInt("id"));
                unidadmedida.setNombre(rs.getString("nombre"));
                unidadmedida.setAbreviatura(rs.getString("abreviatura"));
                lista.add(unidadmedida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void actualizar(UnidadMedida obj) {
        String sql = "update unidad_medida set nombre=?, abreviatura=? where id=?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getAbreviatura());
            sentencia.setInt(3, obj.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
