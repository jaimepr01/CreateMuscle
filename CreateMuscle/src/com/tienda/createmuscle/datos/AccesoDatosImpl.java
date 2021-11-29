
package com.tienda.createmuscle.datos;

import com.tienda.createmuscle.dominio.Productos;
import com.tienda.createmuscle.excepciones.AccesoDatosEx;
import com.tienda.createmuscle.excepciones.EscrituraDatosEx;
import com.tienda.createmuscle.excepciones.LecturaDatosEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements IAccesoDatos{

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var salida = new FileWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el catálogo");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new AccesoDatosEx("Hay un error al crear el carálogo");
        } catch(IOException ex){
            throw new AccesoDatosEx("Hay un error al crear el catálogo");
        }
    }

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Productos> listar(String nombreArchivo) throws LecturaDatosEx {
         var archivo = new File(nombreArchivo);
         Productos producto = null;
         List<Productos> productos = new ArrayList<>();
         String[] arrayProductos = new String[4];
         
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            while (lectura != null) {
                arrayProductos = lectura.split(";");
                producto = new Productos(arrayProductos[0], Integer.parseInt(arrayProductos[1]),Integer.parseInt(arrayProductos[2]),arrayProductos[3]);
                productos.add(producto);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Hay un error al leer el catalogo");
        } catch (IOException ex) {
            throw new  LecturaDatosEx("Hay un error al leer el catalogo");
        }
        return productos;
    }

    @Override
    public void escribir(Productos producto, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
         var archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(producto.getNombreProducto()+";"+producto.getPrecio()+";"+producto.getCantidad()+";"+producto.getFecha());
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new EscrituraDatosEx("Hay un error al escribir el catalogo");
        } catch (IOException ex) {
            throw new  EscrituraDatosEx("Hay un error al escribir el catalogo");
        }
    }

    @Override
    public void buscar(String nombreArchivo, String buscar, int tipo) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            var cont = 0;
            
            String[] arrayProductos = new String[4];
            
            arrayProductos = lectura.split(";");
            
            while (!buscar.equalsIgnoreCase(arrayProductos[tipo])) {
                lectura = entrada.readLine();
                arrayProductos = lectura.split(";");
                cont++;
                
            }
            System.out.println("El producto es: " + lectura + "en la línea: " + (cont+1));
            entrada.close();
            System.out.println("Proceso acabado");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx ("Hay un error al buscar el producto");
        } catch(IOException e){
            e.printStackTrace(System.out);
            throw new LecturaDatosEx ("Hay un error al buscar el producto");
        } catch(NullPointerException e){
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Hay un error al buscar el producto");
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File (nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }else{
         System.out.println("No se ha borrado el archivo");
        }
        System.out.println("Se ha borrado el archivo");
    }

    @Override
    public void borrarProducto(String nombreArchivo,String buscar) throws LecturaDatosEx {
        var archivoP = new File(nombreArchivo);
        var archivoS = new File("archivoS.txt");
        String [] arrayProductos = new String[4];

        try {
            var entrada = new BufferedReader(new FileReader(archivoP)); 
            var salida = new PrintWriter (new FileWriter(archivoS));
            String lectura = null;
            while ((lectura = entrada.readLine()) !=null) {
                arrayProductos = lectura.split(";");
                if (arrayProductos[0] !=buscar) {
                    salida.println(arrayProductos);
                }
                
            }
    }catch(FileNotFoundException e){
        e.printStackTrace(System.out);
            throw new LecturaDatosEx ("Hay un error al buscar el producto");
    }catch(IOException e){
            e.printStackTrace(System.out);
            throw new LecturaDatosEx ("Hay un error al buscar el producto");
    } catch(NullPointerException e){
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Hay un error al buscar el nombre");
        } 

    
    
}
}
