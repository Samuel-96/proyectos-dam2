/*Programa que liste un directorio indicando, para cada entrada, su nombre y si es
fichero regular o subdirectorio.*/

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class listaDirectorio
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio: ");
        String rutaDir = sc.nextLine();

        Path dir = Paths.get(rutaDir);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir))
        {
            for (Path archivo : stream)
            {
                if (Files.isDirectory(archivo))
                    System.out.println(archivo.getFileName() + ": es un subdirectorio");
                else
                    System.out.println(archivo.getFileName() + ": es un fichero regular");
            }
        }

        catch (IOException e)
        {
            System.err.println("No se encuentra la ruta");
        }
    }
}