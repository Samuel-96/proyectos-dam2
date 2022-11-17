package org.samuel;

/*
- sobre un ResultSet de el contenido de la tabla realiza al menos una inserción,
todo una modificación y
un borrado utilizando, al menos, 5 métodos de desplazamiento a lo largo
del ResultSet.
 */

import java.sql.*;

public class modificacionesBD {
    public static void main(String[] args)
    {
        String sql = "SELECT * FROM libros";
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/samuel","root","root");
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery())
        {
            //ME MUEVO A LA ÚLTIMA POSICION
            rs.last();
            //REALIZO LA MODIFICACION
            rs.updateDouble("precio",15.99); //cambio el precio
            rs.updateInt("annopubli",2014); //cambio el año de publicación
            rs.updateRow(); //ejecutamos la actualizacion de datos
            System.out.println("Modificacion del libro realizada");

            //ME MUEVO A LA FILA 2 MEDIANTE ABSOLUTE
            rs.absolute(2);
            System.out.println("\nEstamos en la fila " + rs.getRow());
            rs.updateInt("annopubli",1983); //cambiamos el año de publicacion
            rs.updateRow();
            System.out.println("Modificacion del libro realizada");

            //ME MUEVO A OTRO
            rs.first(); //estoy en la primera fila
            System.out.println("\nAhora en la fila " + rs.getRow());
            rs.updateString("editorial","Penguin Random House");
            rs.updateRow();
            System.out.println("Modificacion del libro realizada");

            //MOVIMIENTO MEDIANTE RELATIVE
            System.out.println("\nAntes de moverme mediante relative() estoy en " + rs.getRow());
            rs.relative(2);//me muevo dos posiciones hacia delante respecto a la fila actual
            System.out.println("Después de moverme mediante relative() estoy en " + rs.getRow());
            rs.updateString("titulo","Palabras Radiantes");
            rs.updateRow();
            System.out.println("Modificacion del libro realizada");
            System.out.println("A continuacion se mostraran los libros");

            rs.absolute(2);
            rs.updateDouble("precio",15);
            rs.updateRow();

            rs.beforeFirst();//me muevo al principio para prepararme para mostrar todos los libros
            while(rs.next()) //me lleva a la primera fila, en la siguiente pasada a la segunda...
            {
                System.out.println("\nId: " + rs.getInt(1));
                System.out.println("Titulo: " + rs.getString(2));
                System.out.println("Precio: " + rs.getDouble(3));
                System.out.println("Año publicación: " + rs.getInt(4));
                System.out.println("Autor: " + rs.getString(5));
                System.out.println("Editorial: " + rs.getString(6));
                System.out.println("\n-------------------------------------------");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
