package org.samuel;

import java.sql.*;
import java.util.Scanner;

public class arregloSusceptibleInyeccion {
    public static void main(String[] args) {
        String sql = "SELECT * FROM libros WHERE titulo = ?;";
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/samuel","root","root");
            PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el titulo de un libro para mostrar sus datos: ");
            String libroUsuario = sc.nextLine();
            ps.setString(1, libroUsuario);
            ResultSet rs = ps.executeQuery(); //ejecuto la consulta

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
