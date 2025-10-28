/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcyt.farmacia.lp2.modelo.entidad;

/**
 *
 * @author cmendieta
 */
public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private UnidadMedida unidadMedida;
    private Boolean esMedicamento;
    private Boolean requiereReceta;
    private Integer precioCompra;
    private Integer precioPublico;
    private Integer precioVenta;

    public Producto() {
    }
    
    

    public Producto(Integer id, String nombre, String descripcion, Categoria categoria, UnidadMedida unidadMedida, Boolean esMedicamento, Boolean requiereReceta, Integer precioCompra, Integer precioPublico, Integer precioVenta) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
        this.esMedicamento = esMedicamento;
        this.requiereReceta = requiereReceta;
        this.precioCompra = precioCompra;
        this.precioPublico = precioPublico;
        this.precioVenta = precioVenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getEsMedicamento() {
        return esMedicamento;
    }

    public void setEsMedicamento(Boolean esMedicamento) {
        this.esMedicamento = esMedicamento;
    }

    public Boolean getRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(Boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getPrecioPublico() {
        return precioPublico;
    }

    public void setPrecioPublico(Integer precioPublico) {
        this.precioPublico = precioPublico;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
    
    
}
