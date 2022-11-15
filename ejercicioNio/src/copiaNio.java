import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class copiaNio {

    public static void main(String[] args) {
        try(RandomAccessFile raf = new RandomAccessFile("RandomAccessFileACopiar.java","r"))
        {
            int fin;
            FileChannel canal = raf.getChannel(); //lo primero es crear el canal
            ByteBuffer bf = ByteBuffer.allocate(256); //despues el buffer

            Set<StandardOpenOption> opciones = new HashSet<>();
            opciones.add(StandardOpenOption.CREATE);
            opciones.add(StandardOpenOption.APPEND);
            Path path = Paths.get("copia.txt");
            FileChannel canal2 = FileChannel.open(path, opciones);

            while ( (fin = canal.read(bf)) > 0) //read() devuelve -1 cuando termina de leer
            {
                canal.read(bf);   //leo (empiezo en modo lectura)
                bf.flip();        //flip para cambiar al modo escritura
                canal2.write(bf); //escribo en copia.txt
                bf.clear();       //limpia el buffer y me devuelve al modo lectura, no hace falta hacer flip despues
            }
            System.out.println("Archivo copiado");

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
