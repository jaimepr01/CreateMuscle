package com.tienda.createmuscle.negocio;

import com.tienda.createmuscle.dominio.Productos;

public interface ICatalogoProductos {
    public void agregarProducto(String nombreProducto ,int cantidad,int precio, String fecha,String nombreArchivo);
    
    public void listarProductos(String nombreArchivo);
    
    public void buscarProducto(String nombreArchivo, String buscar, int tipo);
    
    public void iniciarArchivo(String nombreArchivo);
    
    public void borrarArchivo(String nombreArchivo);
    
    public void mayorCantidad(String nombreArchivo);
    
    public void cantidadProductos(String nombreArchivo);
    
    public void borrarProducto(String nombreArchivo,String nombre);
    
}
