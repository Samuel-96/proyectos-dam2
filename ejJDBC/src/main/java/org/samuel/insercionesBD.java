package org.samuel;

/*
- sobre un ResultSet de todo el contenido de la tabla realiza al menos una inserción,
una modificación y un borrado utilizando, al menos, 5 métodos de desplazamiento a lo largo
del ResultSet.
 */

import java.sql.*;

public class insercionesBD {
    public static void main(String[] args)
    {
         String sql = "SELECT * FROM libros";
         try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/samuel","root","root");
             PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = ps.executeQuery())
         {
            //INSERCION MEDIANTE moveToInserRow()
             rs.moveToInsertRow(); //me posiciono en una fila especial donde puedo insertar nuevas filas
             rs.updateInt("libroid",1);
             rs.updateString("titulo","Dune");
             rs.updateDouble(3,29.99); //identifico el campo mediante el indice (3), es menos legible
             rs.updateInt("annopubli",1965);
             rs.updateString("autor","Frank Herbert");
             rs.updateString("editorial","Nova");
             rs.insertRow(); //para confirmar la insercion
             System.out.println("Registro insertado");

             //realizo dos inserciones más para tener datos con los que trabajar más adelante
             rs.moveToInsertRow();
             rs.updateInt("libroid",2);
             rs.updateString("titulo","El fin de la infancia");
             rs.updateDouble(3,11.99);
             rs.updateInt("annopubli",1953);
             rs.updateString("autor","Arthur C. Clarke");
             rs.updateString("editorial","minotauro");
             rs.insertRow(); //para confirmar la insercion
             System.out.println("Registro insertado");

             rs.moveToInsertRow();
             rs.updateInt("libroid",3);
             rs.updateString("titulo","El camino de los reyes");
             rs.updateDouble(3,29.90);
             rs.updateInt("annopubli",2011);
             rs.updateString("autor","Brandon Sanderson");
             rs.updateString("editorial","Nova");
             rs.insertRow(); //para confirmar la insercion
             System.out.println("Registro insertado");

             //como aun no me he movido en el resultSet de momento sigo en la fila especial
             //del moveToInsertRow()
             //asi que vamos a movernos
             rs.beforeFirst();

             //LOS MUESTRO
             while(rs.next())
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
