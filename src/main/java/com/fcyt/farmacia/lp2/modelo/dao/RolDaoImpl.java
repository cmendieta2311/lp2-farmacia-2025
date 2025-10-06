/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import com.fcyt.farmacia.lp2.modelo.entidad.Rol;
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
public class RolDaoImpl implements RolInterface {

    Connection conec;
    PreparedStatement sentencia;

    public RolDaoImpl() {
        Conexion conexionDB = new Conexion();
        try {
            conec = conexionDB.conectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearRol(Rol rol) {
        String sql = "insert into rol (nombre,descripcion)values(?,?)";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, rol.getNombre());
            sentencia.setString(2, rol.getDescripcion());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Rol getRolById(Integer id) {
        Rol rol = null;
        String sql = "select * from rol where id = ?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
    }

    @Override
    public List<Rol> listarRol(String valor) {
        String sql = "select * from rol where nombre ilike ? order by id asc";
        ArrayList lista = new ArrayList();
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + valor + "%");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
                lista.add(rol);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    @Override
    public void actualizar(Rol rol) {
        String sql = "update rol set nombre=?, descripcion=? where id=?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, rol.getNombre());
            sentencia.setString(2, rol.getDescripcion());
            sentencia.setInt(3, rol.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "delete from rol where id=? ";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
