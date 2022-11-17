package org.samuel;

/*
- sobre un ResultSet de el contenido de la tabla realiza al menos una inserción,
una modificación y
todo un borrado utilizando
al menos, 5 métodos de desplazamiento a lo largo
del ResultSet.
 */

import java.sql.*;

public class borradoBD {
    public static void main(String[] args)
    {
        String sql = "DELETE FROM libros WHERE titulo = ?";
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/samuel","root","root");
            PreparedStatement ps = con.prepareStatement(sql);
            )
        {
            //VAMOS A REALIZAR EL BORRADO DE UN REGISTRO DE LA BASE DE DATOS CON PREPARED STATEMENT
            //LO CUAL PREVIENE DE ATAQUES DE INYECCION SQL
            ps.setString(1,"Palabras Radiantes");
            ps.executeUpdate();
            System.out.println("Registro eliminado");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
