
package com.tienda.createmuscle.dominio;

public class Productos {
    private String nombreProducto;
    private int cantidad;
    private int precio;
    private String fecha;
    
    public Productos(){
        
    }
    
    public Productos(String nombreProducto, int cantidad, int precio, String fecha){
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Productos{" + "nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", precio=" + precio + ", fecha=" + fecha + '}';
    }
    
}
  