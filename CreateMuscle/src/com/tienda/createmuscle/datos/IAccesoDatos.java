
package com.tienda.createmuscle.datos;

import com.tienda.createmuscle.dominio.Productos;
import com.tienda.createmuscle.excepciones.AccesoDatosEx;
import com.tienda.createmuscle.excepciones.EscrituraDatosEx;
import com.tienda.createmuscle.excepciones.LecturaDatosEx;
import java.util.List;

public interface IAccesoDatos {
    public void crear(String nombreArchivo) throws AccesoDatosEx;
    boolean existe(String nombreArchivo) throws AccesoDatosEx;
    List<Productos> listar(String nombreArchivo) throws LecturaDatosEx;
    public void escribir(Productos producto, String nombreArchivo, boolean anexar)throws EscrituraDatosEx;
    public void buscar(String nombreArchivo, String buscar, int tipo) throws LecturaDatosEx;
    public void borrar(String nombreArchivo) throws AccesoDatosEx; 
    public void borrarProducto (String nombreArchivo,String buscar)throws LecturaDatosEx;
    
}
