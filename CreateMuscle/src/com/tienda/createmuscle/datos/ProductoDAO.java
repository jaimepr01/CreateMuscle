package com.tienda.createmuscle.datos;

import com.tienda.createmuscle.dominio.Productos;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {
    List<Productos> seleccionar() throws SQLException;
    
    int insertar (Productos producto) throws SQLException;
    
    int actualizar (Productos producto) throws SQLException;
    
    int borrar (Productos producto) throws SQLException;
}
