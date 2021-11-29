package com.tienda.createmuscle.datos;

import com.tienda.createmuscle.dominio.Productos;
import static com.tienda.createmuscle.datos.Conexion.close;
import static com.tienda.createmuscle.datos.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOJDBC implements ProductoDAO{
    private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos"
            + "(nombreProducto, cantidad, precio, fecha) VALUES "
            + "(?, ?, ?, ?)";
            
     private static final String SQL_UPDATE = "UPDATE productos SET "
             + "cantidad= ?, "
             + "precio= ?, "
             + "fecha= ?, "
             + "WHERE nombreProducto=?";

     private static final String SQL_DELETE = "DELETE FROM productos "
             + "WHERE nombreProducto = ?";
     
     
     private Connection conexionTransaccional;
     
     public ProductoDAOJDBC(){
         
     }
     
     public ProductoDAOJDBC(Connection conexionTransaccional){
         this.conexionTransaccional = conexionTransaccional;
     }
     
    @Override
    public int actualizar(Productos producto) throws SQLException {
          Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; 

        try {
            conn = this.conexionTransaccional !=null? this.conexionTransaccional : getConnection();
            stmt=conn.prepareStatement(SQL_UPDATE);
            

            stmt.setString(1, producto.getNombreProducto()); 
            stmt.setInt(2, producto.getCantidad()); 
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getFecha());
            
            registros= stmt.executeUpdate();
            
        } catch (SQLException ex) {
        }finally{
           close(stmt);
           if (this.conexionTransaccional == null){
                close(conn);
            } 
        }
        return registros;
     
    }
     
    @Override
    public List<Productos> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Productos> productos = new ArrayList<>();

        try {
            conn = this.conexionTransaccional !=null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                String nombreProducto = rs.getString("nombreProducto");
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio");
                String fecha = rs.getString("fecha");

                productos.add(new Productos(nombreProducto, cantidad, precio, fecha));

            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }

        return productos;
    }

    @Override
    public int insertar(Productos producto) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; 
        try {
            conn = this.conexionTransaccional !=null? this.conexionTransaccional : getConnection();

            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, producto.getNombreProducto()); 
            stmt.setInt(2, producto.getCantidad()); 
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getFecha());
            

            registros= stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally { 
           stmt.close();
           conn.close();
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        }
        return registros;
    }
    
    @Override
    public int borrar(Productos producto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = this.conexionTransaccional !=null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setString(1, producto.getNombreProducto());
            
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null){
                close(conn);
            }
        
        }
        return registros;
    }
}
