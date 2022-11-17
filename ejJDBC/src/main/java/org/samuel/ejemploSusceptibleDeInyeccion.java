package org.samuel;

import java.sql.*;
import java.util.Scanner;

public class ejemploSusceptibleDeInyeccion {
    public static void main(String[] args) {
        //EN ESTE EJEMPLO VEMOS CODIGO SUSCEPTIBLE DE INYECCION SQL
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/samuel","root","root");
            Statement st = con.createStatement();
            ResultSet rs = null)
        {
            Scanner sc = new Scanner(System.in);

            //IMAGINEMOS QUE HACEMOS LO SIGUIENTE
            System.out.println("Introduce un libro para buscarlo en la BD: ");
            String libro_a_buscar = sc.nextLine(); //le pedimos al usuario que introduzca un libro para buscarlo en la BD

            //AHORA HACEMOS UNA CONSULTA CON LOS DATOS QUE HA INTRODUCIDO EL USUARIO
            String sql = "SELECT * FROM libros WHERE titulo = '" + libro_a_buscar + "';";

            /*APARENTEMENTE PARECE QUE LA CONSULTA DEBERÍA REALIZARSE SIN PROBLEMAS PERO QUÉ PASA SI EL USUARIO
            INTRODUCE LO SIGUIENTE EN LA VARIABLE libro_a_buscar:

                '; DROP TABLE libros --

            Después de la concatenación, esa comilla simple del principio coincidiría con la que ya está en la consulta.
            Los dos guiones del final significan que lo que se encuentre después de ellos se interpretará como
            comentarios. Así que la consulta resultante sería esta:

                SELECT * FROM libros WHERE titulo = ' ';
                DROP TABLE libros;

            Lo que provocaría la eliminación de la tabla libros al completo.
             */

            System.exit(0);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
