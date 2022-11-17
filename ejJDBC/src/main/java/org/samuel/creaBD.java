/*
Crea un proyecto JDBC que genere una base de datos con tu nombre y, al menos, una tabla para el
elemento con el que estés trabajando (discos, libros, videojuegos, etc).

ORDEN DE EJECUCION DE LOS ARCHIVOS
1. creaDB
2. insercionesBD
3. modificacionesBD
4. ejemploSusceptibleDeInyeccion
5. arregloSusceptibleInyeccion
6. funcionYpreocedimientoBD
7. transaccionBD
 */

package org.samuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class creaBD
{
    public static void main( String[] args )
    {
        String sql;
        try(Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "root", "root");
            Statement sentencia = conexion.createStatement()
            )
        {
            //CREACION BASE DE DATOS
            System.out.println("Conexión establecida con la Base de Datos");
            sql = "DROP DATABASE IF EXISTS samuel; CREATE DATABASE samuel"; //para crear la base de datos desde jdbc hay que conectarse a
                                                    //localhost/postgres
            sentencia.executeUpdate(sql); //ejecuto la sentencia y creo la BD
            System.out.println("Base de Datos 'samuel' creada");

            //AHORA HAY QUE CONECTARSE A LA BASE DE DATOS CREADA, libros
            try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/samuel", "root", "root");
                Statement st = conn.createStatement()
                )
            {
                //CREACION TABLAS
                System.out.println("Conexion establecida con la Base de Datos samuel");
                sql =   "CREATE TABLE libros(" +
                            "LIBROID int NOT NULL, " +
                            "TITULO char(40) NOT NULL, "+
                            "PRECIO numeric NOT NULL, "+
                            "ANNOPUBLI int NOT NULL, "+
                            "AUTOR char(40) NOT NULL, "+
                            "EDITORIAL char(20) NOT NULL, "+
                            "CONSTRAINT PK_LIBROID PRIMARY KEY (LIBROID));";
                st.executeUpdate(sql);
                System.out.println("Tabla libros creada");

                sql =   "CREATE TABLE autores(" +
                          "AUTORID int NOT NULL,"+
                          "NOMBRE char(40) NOT NULL,"+
                          "PAIS char (40) NOT NULL,"+
                          "CONSTRAINT PK_AUTOR PRIMARY KEY (AUTORID));";

                st.executeUpdate(sql);
                System.out.println("Tabla autores creada");

                sql =   "CREATE TABLE libro_autor("+
                            "AUTOR_ID int NOT NULL,"+
                            "LIBRO_ID int NOT NULL,"+
                            "CONSTRAINT AUTOR_LIBRO FOREIGN KEY (AUTOR_ID) REFERENCES autores(AUTORID),"+
                            "CONSTRAINT LIBRO_AUTOR FOREIGN KEY (LIBRO_ID) REFERENCES libros(LIBROID));";


                st.executeUpdate(sql);
                System.out.println("Tabla libro_autor creada");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
