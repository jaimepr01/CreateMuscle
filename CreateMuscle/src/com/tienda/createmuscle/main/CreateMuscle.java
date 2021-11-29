package com.tienda.createmuscle.main;

import com.tienda.createmuscle.dominio.*;
import com.tienda.createmuscle.datos.*;
import com.tienda.createmuscle.negocio.CatalogoProductosImpl;
import com.tienda.createmuscle.negocio.ICatalogoProductos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CreateMuscle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
       ICatalogoProductos catalogo = new CatalogoProductosImpl();
        Scanner sn = new Scanner(System.in);
        String nombreCatalogo = "CreateMuscle.txt";
                   
       boolean salir = false;
       int opcion; 
        
       while(!salir){
            
           System.out.println("1.Iniciar catalogo de CreateMuscle");
           System.out.println("2.Agregar un producto");
           System.out.println("3.Listar Productos");
           System.out.println("4.Buscar un Producto");
           System.out.println("5.Cual tiene mayor cantidad");
           System.out.println("6.Cantidad de productos");
           System.out.println("7. Borrar archivo");  
           System.out.println("8. Borrar producto"); 
           System.out.println("9. Salir");           
           System.out.println("Escribe una de las opciones");
           opcion = sn.nextInt();
            
           switch(opcion){
               case 1:
                   System.out.println("");
                   catalogo.iniciarArchivo(nombreCatalogo);
                   System.out.println("Su nombre es " + nombreCatalogo);
                   System.out.println("");  
                   break;
               case 2:
                   System.out.println("");
                   System.out.println("Introcuce el nombre del producto,\n el precio,\nla cantidad \ny la fecha\n(Recuerda que cada vez que introduzcas un dato debes darle al intro)");
                   catalogo.agregarProducto(sn.next(),sn.nextInt(),sn.nextInt(),sn.next(), nombreCatalogo);
                   System.out.println("");
                   break;
                case 3:
                    System.out.println("");
                    catalogo.listarProductos(nombreCatalogo);
                    System.out.println("");
                            
                   break;
                case 4:
                    System.out.println("");
                   System.out.println("Introduce 0 si quieres buscar por nombre.\nIntroduce 1 si quieres buscar por cantidad.\nIntroduce 2 si quieres buscar por precio .\nIntroduce 3 si quieres buscar por fecha.\n");
                   var filtro = sn.nextInt();
                    System.out.println("Introduce tu busqueda elegida: ");
                   catalogo.buscarProducto(nombreCatalogo, sn.next(),filtro);
                   System.out.println("");
                   break;
                case 5:
                    System.out.println("");
                    catalogo.mayorCantidad(nombreCatalogo);
                    System.out.println("");
                   break;
                case 6:
                    System.out.println("");
                    catalogo.cantidadProductos(nombreCatalogo);
                    System.out.println("");
                   break;   
                case 7 :
                    System.out.println("");
                    catalogo.borrarArchivo(nombreCatalogo);
                    System.out.println("Se ha borrado el archivo correctamente");
                    System.out.println("");
                   break;   
                case 8 :
                    System.out.println("");
                    System.out.println("Introduce el nombre del producto que quieres borrar: ");
                    catalogo.borrarProducto(nombreCatalogo,sn.next());
                    
                    System.out.println("");
                   break;
                case 9:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 9");
           }
            
       } 
}
}
