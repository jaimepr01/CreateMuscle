package com.tienda.createmuscle.negocio;

import com.tienda.createmuscle.datos.AccesoDatosImpl;
import com.tienda.createmuscle.datos.IAccesoDatos;
import com.tienda.createmuscle.dominio.Productos;
import com.tienda.createmuscle.excepciones.AccesoDatosEx;
import com.tienda.createmuscle.excepciones.EscrituraDatosEx;
import com.tienda.createmuscle.excepciones.LecturaDatosEx;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CatalogoProductosImpl implements ICatalogoProductos{
      private final IAccesoDatos datos;
      public CatalogoProductosImpl(){
          this.datos = new AccesoDatosImpl();
      }
      
    @Override
    public void agregarProducto(String nombreProducto, int cantidad, int precio, String fecha, String nombreArchivo) {
        Productos producto = new Productos (nombreProducto,cantidad,precio,fecha);
        try {
            datos.escribir(producto, nombreArchivo, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar producto");
        }
    }

    @Override
    public void listarProductos(String nombreArchivo) {
        List<Productos> producto = new ArrayList<>();
        try {
            producto = datos.listar(nombreArchivo);
            producto.forEach(productos -> { 
                System.out.println("Productos : " + productos);
        });
        } catch (Exception e) {
            System.out.println("Error leyendo el catálogo");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarProducto(String nombreArchivo, String buscar, int tipo) {
        try {
            datos.buscar(nombreArchivo, buscar, tipo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al buscar el producto");
        }
  
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        try {
            if (datos.existe(nombreArchivo)) {
                datos.borrar(nombreArchivo);
                datos.crear(nombreArchivo);
            }else{
                datos.crear(nombreArchivo);
            }
            
        } catch (AccesoDatosEx e) {
            System.out.println("Error al iniciar el archivo");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void borrarArchivo(String nombreArchivo) {
        try {
             datos.borrar(nombreArchivo);
         } catch (AccesoDatosEx ex) {
             ex.printStackTrace();
             System.out.println("Error al borrar el archivo");
         }
    }

    @Override
    public void mayorCantidad(String nombreArchivo) {
        try {     
            List<Productos> producto = new ArrayList<>();
            producto.addAll(datos.listar(nombreArchivo));
            
            var nombre = "";
            var puntuacion=0;
            for (int i = 0; i < producto.size(); i++) {
                
                if(puntuacion < producto.get(i).getCantidad()){
                    puntuacion = producto.get(i).getCantidad();
                    nombre = producto.get(i).getNombreProducto();
                }              
            }
            System.out.println("El producto con mayor cantidad tiene "+puntuacion+" y es el "+ nombre);
         
        } catch (LecturaDatosEx e) {
            e.printStackTrace();
            System.out.println("Error al mostra cantidad");
        }
    }

    @Override
    public void cantidadProductos(String nombreArchivo) {
      try {
             List<Productos> producto = new ArrayList<>();
             producto.addAll(datos.listar(nombreArchivo));
             System.out.println("La cantidad de productos que hay en CreateMuscle es de : "+producto.size());
             
             
         } catch (LecturaDatosEx e) {
            e.printStackTrace();
            System.out.println("Error al mostrar la cantidad de productos");
         }  
    }

    @Override
    public void borrarProducto(String nombreArchivo, String nombre) {
       try {
             datos.borrarProducto(nombreArchivo, nombre);
             System.out.println("Se ha borrado el producto elegido");
             
         } catch (LecturaDatosEx e) {
            e.printStackTrace();
            System.out.println("Error al mostrar la cantidad de productos");
         } 
    }
    
    
    
}
