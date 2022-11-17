// realiza una transacción que no se complete al provocar un rollback.
package org.samuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class transaccionBD {
    public static void main(String[] args) {
        //sql
        String altaLibro = "INSERT INTO libros (libroid, titulo, precio, annopubli, autor, editorial) VALUES (?,?,?,?,?,?)";
        String altaAutor = "INSERT INTO autores (autorid, nombre, pais) VALUES (?,?,?)";
        String libros_autor = "INSERT INTO libro_autor (autor_id, libro_id) VALUES (?,?)";

        //declaro los objetos fuera del try-with-resources
        Connection con = null;
        PreparedStatement ps = null;


        try
        {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/samuel","root","root");
            con.setAutoCommit(false); //lo primero poner autoCommit en false, por defecto está en true

            //DOY VALORES AL PRIMER INSERT
            ps = con.prepareStatement(altaLibro);
            ps.setInt(1,4); //id del nuevo libro 4, más adelante pondré 5 para provocar fallo en la transaccion
            ps.setString(2,"Juego de tronos");
            ps.setDouble(3,29.95);
            ps.setInt(4,1999);
            ps.setString(5,"George R. R. Martin");
            ps.setString(6,"Gigamesh");
            ps.executeUpdate(); //ejecuto la sentencia
            ps.close();

            //DOY VALORES AL SEGUNDO INSERT
            ps = con.prepareStatement(altaAutor);
            ps.setInt(1,1);
            ps.setString(2,"George R. R. Martin");
            ps.setString(3,"Estados Unidos");
            ps.executeUpdate();
            ps.close();

            //DOY VALORES AL TERCER INSERT
            ps = con.prepareStatement(libros_autor);
            ps.setInt(1,1);
            ps.setInt(2,5); //fallo a propósito poniendo id de libro 5, el cual no existe
            ps.executeUpdate();
            ps.close();

            con.commit(); //despues de todas las inserciones hago el commit

            //si va bien se ejecuta el try
        }
        catch (SQLException e) //si no va bien vamos al catch
        {
            try {
                System.out.println("rollback");
                con.rollback();//se ejecuta el rollback para deshacer el commit
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
