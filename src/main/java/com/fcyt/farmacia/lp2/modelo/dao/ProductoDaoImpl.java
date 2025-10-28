/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.dao;

import com.fcyt.farmacia.lp2.modelo.entidad.Categoria;
import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
import com.fcyt.farmacia.lp2.modelo.entidad.Producto;
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
public class ProductoDaoImpl implements CrudInterface<Producto> {

    Connection conec;
    PreparedStatement sentencia;

    public ProductoDaoImpl() {
        Conexion conexionDB = new Conexion();
        try {
            conec = conexionDB.conectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crear(Producto item) {
        String sql = "insert into producto (nombre, descripcion, categoria_id, unidad_base_id,es_medicamento,requiere_receta,precio_compra,precio_sugerido,precio_venta) values(?,?,?,?,?,?,?,?,?)";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, item.getNombre());
            sentencia.setString(2, item.getDescripcion());
            sentencia.setInt(3, item.getCategoria().getId());
            sentencia.setInt(4, item.getUnidadMedida().getId());
            sentencia.setBoolean(5, item.getEsMedicamento());
            sentencia.setBoolean(6, item.getRequiereReceta());
            sentencia.setInt(7, item.getPrecioCompra());
            sentencia.setInt(8, item.getPrecioPublico());
            sentencia.setInt(9, item.getPrecioVenta());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "delete from producto where id=? ";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Producto getById(Integer id) {
        Producto item = null;
        String sql = "select * from producto where id = ?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                item = new Producto();
                Categoria categoria = new Categoria();
                UnidadMedida unidadMedida = new UnidadMedida();
                
                item.setId(rs.getInt("id"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));
                
                // Se crear un objeto de tipo categoria
                categoria.setId(rs.getInt("categoria_id"));
//                categoria.setNombre("");
//                categoria.setDescripcion("");
                item.setCategoria(categoria);
                
                
                // Se crear un objeto de tipo unidad de medida
                unidadMedida.setId(rs.getInt("unidad_base_id"));
                item.setUnidadMedida(unidadMedida);
                
                item.setEsMedicamento(rs.getBoolean("es_medicamento"));
                item.setRequiereReceta(rs.getBoolean("requiere_receta"));
                
                item.setPrecioCompra(rs.getInt("precio_compra"));
                item.setPrecioCompra(rs.getInt("precio_sugerido"));
                item.setPrecioCompra(rs.getInt("precio_venta"));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @Override
    public List listar(String valor) {
        String sql = "select * from producto where nombre ilike ? order by id asc";
        ArrayList lista = new ArrayList();
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + valor + "%");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Producto item = new Producto();
                Categoria categoria = new Categoria();
                UnidadMedida unidadMedida = new UnidadMedida();
                
                item.setId(rs.getInt("id"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));
                
                // Se crear un objeto de tipo categoria
                categoria.setId(rs.getInt("categoria_id"));
//                categoria.setNombre("");
//                categoria.setDescripcion("");
                item.setCategoria(categoria);
                
                
                // Se crear un objeto de tipo unidad de medida
                unidadMedida.setId(rs.getInt("unidad_base_id"));
                item.setUnidadMedida(unidadMedida);
                
                item.setEsMedicamento(rs.getBoolean("es_medicamento"));
                item.setRequiereReceta(rs.getBoolean("requiere_receta"));
                
                item.setPrecioCompra(rs.getInt("precio_compra"));
                item.setPrecioPublico(rs.getInt("precio_sugerido"));
                item.setPrecioVenta(rs.getInt("precio_venta"));
                
                // se agrega el objeto a la lista
                lista.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void actualizar(Producto obj) {
        String sql = "update producto set nombre=?, descripcion=? where id=?";
        try {
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getDescripcion());
            sentencia.setInt(3, obj.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
