package org.samuel;

/*
- utiliza al menos una función y un procedimiento. Adjunta el código de las funciones y
procedimientos también.
 */

import java.sql.*;

public class funcionYprocedimientoBD {
    public static void main(String[] args) {
        String cuentalibros = "select cuentalibros()"; //funcion cuentalibros
        String borralibros = "call borralibros(?)"; //procedimiento con parámetro

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/samuel","root","root");
            PreparedStatement ps = con.prepareStatement(cuentalibros); //para la funcion
            CallableStatement cs = con.prepareCall(borralibros); //para el procedimiento
            ResultSet rs = ps.executeQuery())
        {
            //EJECUTANDO LA FUNCION
            rs.next(); //me posiciono
            if(rs.wasNull())
                System.out.println("No hay libros");
            else
            {
                int cont = rs.getInt(1); //guardo en cont el numero que me devuelva la funcion
                System.out.println("Número de libros: " + cont);
            }

            //EJECUTANDO EL PROCEDIMIENTO
            cs.setString(1,"Dune");//establezco el parametro del procedimiento
            cs.execute();
            System.out.println("Libro eliminado de la Base de datos");

            /*
                CODIGO DE LA FUNCION

                create function cuentalibros() returns integer
                    language plpgsql
                as
                $$
                declare
                    contador integer;
                BEGIN
                    select count(*) into contador from libros;
                    return contador;
                END;
                $$;
                --------------------------------------------------
                CODIGO DEL PROCEDIMIENTO CON PARÁMETROS

                create procedure borralibros(titul VARCHAR)
                    language plpgsql
                as
                $$
                BEGIN
                    DELETE FROM libros WHERE titulo = titul;
                END;
                $$;
             */
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
