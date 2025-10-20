/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import com.fcyt.farmacia.lp2.modelo.entidad.Categoria;
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
public class CategoriaDaoImpl implements CrudInterface<Categoria> {

    Connection conec;
    PreparedStatement sentencia;

    public CategoriaDaoImpl() {
        Conexion conexionDB = new Conexion();
        try {
            conec = conexionDB.conectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crear(Categoria categoria) {
        String sql = "insert into categoria (nombre,descripcion)values(?,?)";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, categoria.getNombre());
            sentencia.setString(2, categoria.getDescripcion());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "delete from categoria where id=? ";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Categoria getById(Integer id) {
        Categoria categoria = null;
        String sql = "select * from categoria where id = ?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }

    @Override
    public List listar(String valor) {
        String sql = "select * from categoria where nombre ilike ? order by id asc";
        ArrayList lista = new ArrayList();
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + valor + "%");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                lista.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void actualizar(Categoria obj) {
        String sql = "update categoria set nombre=?, descripcion=? where id=?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getDescripcion());
            sentencia.setInt(3, obj.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
